package org.forkjoin.apikit.impi;

import org.eclipse.jdt.core.dom.*;
import org.forkjoin.apikit.info.ImportsInfo;
import org.forkjoin.apikit.info.JavadocInfo;
import org.forkjoin.apikit.info.ModuleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoge85 on 15/11/16.
 */
public abstract class JdtAbstractModuleAnalyse {
    protected CompilationUnit node;
    protected TypeDeclaration type;
    protected String name;
    protected String packageName;
    protected ImportsInfo importsInfo;

    public JdtAbstractModuleAnalyse(CompilationUnit node, TypeDeclaration type, String name, String packageName, ImportsInfo importsInfo) {
        this.node = node;
        this.type = type;
        this.name = name;
        this.packageName = packageName;
        this.importsInfo = importsInfo;
    }

    protected void initModuleInfo(ModuleInfo moduleInfo) {
        Javadoc javadoc = type.getJavadoc();

        moduleInfo.setPackageName(packageName);
        moduleInfo.setName(name);
        moduleInfo.setComment(transform(javadoc));

        moduleInfo.setPackageName(packageName);
    }

    protected static JavadocInfo transform(Javadoc javadoc) {
        if (javadoc == null) {
            return null;
        }
        JavadocInfo javadocInfo = new JavadocInfo();
        List tags = javadoc.tags();
        for (Object tag : tags) {
            TagElement tagElement = (TagElement) tag;
            String tagName = tagElement.getTagName();

            List fragments = tagElement.fragments();
            ArrayList<String> fragmentsInfo = new ArrayList<>();
            for (Object fragment : fragments) {
                if (fragment instanceof TextElement) {
                    TextElement textElement = (TextElement) fragment;
                    fragmentsInfo.add(textElement.getText());
                } else {
                    fragmentsInfo.add(fragment.toString());
                }
            }
            javadocInfo.add(tagName, fragmentsInfo);
        }
        return javadocInfo;
    }

    public abstract ModuleInfo analyse();

    public QualifiedName getTypeName(Name typeName) {
        if (typeName instanceof QualifiedName) {
            return (QualifiedName) typeName;
        } else {
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
            return null;
        }
    }


    public  boolean equalsType(Name typeName, Class<?> apiMethodClass) {
        QualifiedName qualifiedName = getTypeName(typeName);
        return apiMethodClass.getName().equals(qualifiedName.toString());
    }
}
