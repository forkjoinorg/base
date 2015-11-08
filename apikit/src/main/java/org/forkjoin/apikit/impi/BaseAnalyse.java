package org.forkjoin.apikit.impi;

import org.eclipse.jdt.core.dom.*;
import org.forkjoin.api.Api;
import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.info.ModuleInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/11/8.
 */
public class BaseAnalyse implements Analyse {
    private CompilationUnit node;
    private TypeDeclaration type;
    private String name;
    private Map<String, QualifiedName> nameMaps = new HashMap<>();

    @Override
    public ModuleInfo analyse(String code, String pack) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(code.toCharArray());
        node = (CompilationUnit) parser.createAST(null);

        List imports = node.imports();
        for (Object importItem : imports) {
            ImportDeclaration importDeclaration = (ImportDeclaration) importItem;

            Name name = importDeclaration.getName();
            if (name instanceof QualifiedName) {
                QualifiedName qName = (QualifiedName) name;
                nameMaps.put(qName.getName().getIdentifier(), qName);
            }
        }
        List types = node.types();
        if (types.size() == 1) {
            type = (TypeDeclaration) types.get(0);
            name = type.getName().getFullyQualifiedName();
        } else {
            throw new RuntimeException("错误的类型，现在只支持一个文件单个类:");
        }

        return null;
    }

//    public ModuleInfo analyse() {
//        if (isApi(type)) {
//            log.debug("isApi:{}", type.getName());
//            return analyseApi();
//        } else if (isMessage(type)) {
//            log.debug("isMessage:{}", type.getName());
//            return analyseMessage();
//        } else {
//            log.debug("不支持的java文件:{}", file);
//        }
//        return null;
//    }
//
//    private boolean isApi(TypeDeclaration type) {
//        List modifiers = type.modifiers();
//        for (Object o : modifiers) {
//            if (o instanceof Annotation) {
//                Annotation annotation = (Annotation) o;
//                QualifiedName typeName = getTypeName(annotation.getTypeName());
//                if (typeName.getFullyQualifiedName().equals(Api.class.getName())) {
//                    return type.isInterface();
//                }
//            }
//        }
//        return false;
//    }
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
//    public QualifiedName getTypeName(Name typeName) {
//        if (typeName instanceof QualifiedName) {
//            return (QualifiedName) typeName;
//        } else {
//            String fullyQualifiedName = typeName.getFullyQualifiedName();
//            QualifiedName qualifiedName = nameMaps.get(fullyQualifiedName);
//            if (qualifiedName == null) {
//                if (typeName.isSimpleName()) {
//                    AST ast = typeName.getAST();
//                    qualifiedName = formJavaLang((SimpleName) typeName);
//                    if (qualifiedName == null) {
//                        qualifiedName = ast.newQualifiedName(ast.newName(getPackageName()), ast.newSimpleName(fullyQualifiedName));
//                    }
//                }
//            }
//            return qualifiedName;
//        }
//    }

}
