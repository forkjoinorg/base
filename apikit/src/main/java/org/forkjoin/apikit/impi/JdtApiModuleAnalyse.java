package org.forkjoin.apikit.impi;

import org.eclipse.jdt.core.dom.*;
import org.forkjoin.api.ApiMethod;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.ImportsInfo;
import org.forkjoin.apikit.info.ModuleInfo;

import java.util.List;

/**
 * @author zuoge85 on 15/11/16.
 */
public class JdtApiModuleAnalyse extends JdtAbstractModuleAnalyse {

    public JdtApiModuleAnalyse(
            CompilationUnit node, TypeDeclaration type, String name, String packageName, ImportsInfo importsInfo
    ) {
        super(node, type, name, packageName, importsInfo);
    }

    @Override
    public ModuleInfo analyse() {
        ApiInfo apiInfo = new ApiInfo();
        initModuleInfo(apiInfo);

        MethodDeclaration[] methods = type.getMethods();
        for (MethodDeclaration method : methods) {
            List modifiers = method.modifiers();
            for (Object o : modifiers) {
                if (o instanceof Annotation) {
                    Annotation annotation = (Annotation) o;
                    if (equalsType(annotation.getTypeName(), ApiMethod.class)) {
//                        ApiMethodInfo apiMethodInfo = analyseMethodInfo(method, annotation);
//                        apiInfo.addApiMethod(apiMethodInfo);
                    }
                }
            }
        }
        return apiInfo;
    }

}
