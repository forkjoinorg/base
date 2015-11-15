package org.forkjoin.apikit.impi;

import org.eclipse.jdt.core.dom.*;
import org.forkjoin.api.Api;
import org.forkjoin.apikit.AbstractAnalyse;
import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.info.ImportsInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.ModuleType;
import org.forkjoin.apikit.oldmodel.AttributeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于jdt的分析器
 *
 * @author zuoge85 on 15/11/8.
 */
public class JdtAnalyse extends AbstractAnalyse {
    private CompilationUnit node;
    private TypeDeclaration type;

    @Override
    public ModuleInfo analyse(String code, String pack) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(code.toCharArray());
        node = (CompilationUnit) parser.createAST(null);

        analyse();

        List types = node.types();
        if (types.size() == 1) {
            type = (TypeDeclaration) types.get(0);
            name = type.getName().getFullyQualifiedName();
        } else {
            throw new RuntimeException("错误的类型，现在只支持一个文件单个类:");
        }

        ModuleType moduleType = analyseType(type);

        return null;
    }

    private void analyse() {
        List imports = node.imports();
        for (Object importItem : imports) {
            ImportDeclaration importDeclaration = (ImportDeclaration) importItem;
            if (importDeclaration.isStatic()) {
                throw new RuntimeException("不支持静态导入");
            }

            Name name = importDeclaration.getName();
            if (name instanceof QualifiedName) {
                QualifiedName qName = (QualifiedName) name;
                importsInfo.add(
                        qName.getQualifier().getFullyQualifiedName(),
                        qName.getName().getFullyQualifiedName()
                );
            } else {
                throw new RuntimeException("不支持的导入名称:" + name);
            }
        }
    }

    private ModuleType analyseType(TypeDeclaration type) {
        List modifiers = type.modifiers();
        for (Object o : modifiers) {
            if (o instanceof Annotation) {
                Annotation annotation = (Annotation) o;
                QualifiedName typeName = getTypeName(annotation.getTypeName());
                String fullyQualifiedName = typeName.getFullyQualifiedName();

                if (fullyQualifiedName.equals(Api.class.getName())) {
                    return ModuleType.API;
                } else if (typeName.getFullyQualifiedName().equals(org.forkjoin.api.Message.class.getName())) {
                    return ModuleType.MESSAGE;
                } else if (typeName.getFullyQualifiedName().equals(org.forkjoin.api.Enum.class.getName())) {
                    return ModuleType.ENUM;
                }
            }
        }
        return null;
    }

    //
//    private boolean isMessage(TypeDeclaration type) {
//        List modifiers = type.modifiers();
//        for (Object o : modifiers) {
//            if (o instanceof Annotation) {
//                Annotation annotation = (Annotation) o;
//                QualifiedName typeName = getTypeName(annotation.getTypeName());
//                if (typeName.getFullyQualifiedName().equals(org.forkjoin.api.Message.class.getName())) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
    public QualifiedName getTypeName(Name typeName) {
        if (typeName instanceof QualifiedName) {
            return (QualifiedName) typeName;
        } else {
            String fullyQualifiedName = typeName.getFullyQualifiedName();
            QualifiedName qualifiedName = nameMaps.get(fullyQualifiedName);
            if (qualifiedName == null) {
                if (typeName.isSimpleName()) {
                    AST ast = typeName.getAST();
                    qualifiedName = formJavaLang((SimpleName) typeName);
                    if (qualifiedName == null) {
                        qualifiedName = ast.newQualifiedName(ast.newName(getPackageName()), ast.newSimpleName(fullyQualifiedName));
                    }
                }
            }
            return qualifiedName;
        }
    }

    private String getPackageName() {
        return node.getPackage().getName().getFullyQualifiedName();
    }


    public QualifiedName formJavaLang(SimpleName typeName) {
        String fullyQualifiedName = typeName.getFullyQualifiedName();
        AttributeType attributeType = AttributeType.formWrap(fullyQualifiedName);
        if (attributeType != AttributeType.OTHER) {
            AST ast = typeName.getAST();
            return ast.newQualifiedName(ast.newName("java.lang"), ast.newSimpleName(fullyQualifiedName));
        }
        return null;
    }
}
