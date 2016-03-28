package org.forkjoin.apikit.generator.jdt;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.utils.HttlUtils;
import org.forkjoin.apikit.utils.JavaFileFormat;
import org.forkjoin.apikit.generator.jdt.JdtUtils;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/17.
 */
public class JavaCodeUpdate {
    public static final String ENCODING = "utf8";

    private File file;
    private BuilderWrapper utils;
    private ApiInfo moduleInfo;


    public JavaCodeUpdate(File file, BuilderWrapper utils, ApiInfo moduleInfo) {
        this.file = file;
        this.utils = utils;
        this.moduleInfo = moduleInfo;
    }

    public void update(String targetSource,String name) {
        try {
            String source = FileUtils.readFileToString(file, ENCODING);
            CompilationUnit sourceNode = createCompilationUnit(source);
            TypeDeclaration sourceType = getType(name, sourceNode);
            if (sourceType == null) {
                throw new RuntimeException("未找到需要更新文件里面有所需类型！");
            }

            CompilationUnit targetSourceNode = createCompilationUnit(targetSource);
            TypeDeclaration targetSourceType = getType(name, targetSourceNode);
            if (targetSourceType == null) {
                throw new RuntimeException("需要更新到的代码没找到指定类型！检查模板生成代码\n" + targetSource);
            }
            updateType(sourceType, targetSourceType);

            String formatCode = JavaFileFormat.formatCode(sourceNode.toString());
            HttlUtils.writeStringToFile(file, formatCode, ENCODING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TypeDeclaration getType(String name, CompilationUnit sourceNode) throws IOException {
        for (Object type : sourceNode.types()) {
            TypeDeclaration typeDec = (TypeDeclaration) type;
            if ((!typeDec.isInterface()) && name.equals(typeDec.getName().getFullyQualifiedName())) {
                return typeDec;
            }
        }
        return null;
    }

    private CompilationUnit createCompilationUnit(String source) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);

        Map options = JavaCore.getOptions();
        JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);

        parser.setCompilerOptions(options);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setStatementsRecovery(true);
        parser.setSource(source.toCharArray());
        return (CompilationUnit) parser.createAST(null);
    }

    /**
     * @param sourceType       需要修改的类型
     * @param targetSourceType 需要参考的类型
     */
    private void updateType(TypeDeclaration sourceType, TypeDeclaration targetSourceType) {
        updateTypeAnnotation(sourceType, targetSourceType);

        MethodDeclaration[] methods = sourceType.getMethods();
        Map<String, MethodDeclaration> sourceMap = new HashMap<>();
        for (MethodDeclaration method : methods) {
            String name = JdtUtils.getApiMethodSign(method);
            if (name != null) {
                sourceMap.put(name, method);
            }
        }

        AST sourceTypeAST = sourceType.getAST();
        MethodDeclaration[] targetMethods = targetSourceType.getMethods();
        for (MethodDeclaration targetMethod : targetMethods) {
            String methodSign = JdtUtils.getApiMethodSign(targetMethod);
            if (methodSign != null) {
                MethodDeclaration sourceMethod = sourceMap.get(methodSign);
                if (sourceMethod == null) {
                    //没找到函数，那需要加入函数
                    ASTNode copyNode = MethodDeclaration.copySubtree(sourceTypeAST, targetMethod);
                    @SuppressWarnings("unchecked")
                    List<Object> bodyDeclarations = sourceType.bodyDeclarations();
                    bodyDeclarations.add(bodyDeclarations.size(), copyNode);
                } else {
                    //修改函数
                    updateMethod(sourceMethod, targetMethod);
                }
            }
        }

    }

    /**
     * @param sourceType       需要修改的type
     * @param targetSourceType 需要参考的方法type
     */
    private void updateTypeAnnotation(TypeDeclaration sourceType, TypeDeclaration targetSourceType) {
        //类型现在只更新 RequestMapping
        @SuppressWarnings("unchecked")
        List<Object> sourceModifiers = sourceType.modifiers();

        Annotation annotation = getAnnotation(sourceModifiers, RequestMapping.class);
        Annotation targetAnnotation = getAnnotation(targetSourceType.modifiers(), RequestMapping.class);
        if (targetAnnotation == null) {
            throw new RuntimeException("生成控制器居然没有 RequestMapping注解");
        }
        Annotation copyAnnotation = (Annotation) Annotation.copySubtree(sourceType.getAST(), targetAnnotation);
        if (annotation != null) {
            int index = sourceModifiers.indexOf(annotation);
            annotation.delete();
            sourceModifiers.add(index, copyAnnotation);
        } else {
            int index = 0;
            for (Object sourceModifier : sourceModifiers) {
                if (sourceModifier instanceof Annotation) {
                    index++;
                } else {
                    break;
                }
            }
            sourceModifiers.add(index, copyAnnotation);
        }
    }

    private Annotation getAnnotation(List modifiers, Class<? extends java.lang.annotation.Annotation> anCls) {
        for (Object o : modifiers) {
            if (o instanceof Annotation) {
                Annotation annotation = (Annotation) o;
                String name = annotation.getTypeName().getFullyQualifiedName();
                if (name.equals(anCls.getSimpleName())) {
                    return annotation;
                }
            }
        }
        return null;
    }

    /**
     * @param sourceMethod 需要修改的方法
     * @param targetMethod 需要参考的方法
     */
    private void updateMethod(MethodDeclaration sourceMethod, MethodDeclaration targetMethod) {
        //只保留正文
        MethodDeclaration copyMethod = (MethodDeclaration) MethodDeclaration.copySubtree(sourceMethod.getAST(), targetMethod);
        copyMethod.getBody().delete();

        Block sourceBlock = (Block) MethodDeclaration.copySubtree(sourceMethod.getAST(), sourceMethod.getBody());
        copyMethod.setBody(sourceBlock);
        //


        TypeDeclaration sourceType = (TypeDeclaration) sourceMethod.getParent();

        @SuppressWarnings("unchecked")
        List<Object> bodyDeclarations = sourceType.bodyDeclarations();
        bodyDeclarations.add(bodyDeclarations.indexOf(sourceMethod), copyMethod);
        sourceMethod.delete();
    }

}
