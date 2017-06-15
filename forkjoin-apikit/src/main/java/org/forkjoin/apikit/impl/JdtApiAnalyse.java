package org.forkjoin.apikit.impl;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.jdt.core.dom.*;
import org.forkjoin.apikit.AnalyseException;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.ActionType;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.info.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * @author zuoge85 on 15/11/16.
 */
public class JdtApiAnalyse extends JdtAbstractModuleAnalyse {
    private static final Logger log = LoggerFactory.getLogger(JdtApiAnalyse.class);

    public JdtApiAnalyse(JdtInfo jdtInfo) {
        super(jdtInfo);
    }

    @Override
    public ModuleInfo analyse() {
        ApiInfo apiInfo = new ApiInfo();
        initModuleInfo(apiInfo);
        apiInfo.setName(apiInfo.getName().replace("Controller", ""));

        MethodDeclaration[] methods = jdtInfo.getType().getMethods();
        for (MethodDeclaration method : methods) {
            List modifiers = method.modifiers();
            for (Object o : modifiers) {
                if (o instanceof Annotation) {
                    Annotation annotation = (Annotation) o;
                    if (equalsType(annotation.getTypeName(), RequestMapping.class)) {
                        //分析Method
                        ApiMethodInfo apiMethodInfo = analyseMethodInfo(method, annotation);
                        apiInfo.addApiMethod(apiMethodInfo);

                        log.debug("ApiMethodInfo: {}:{}", method.getName(), apiMethodInfo);
                    }
                }
            }
        }
        return apiInfo;
    }


    private ApiMethodInfo analyseMethodInfo(MethodDeclaration method, Annotation annotation) {
        ApiMethodInfo apiMethodInfo = new ApiMethodInfo();
        apiMethodInfo.setName(method.getName().getFullyQualifiedName());

        //  @ApiMethod(value = "test", type = ActionType.GET)
//        SupportType returnType = SupportType.from(this, method.getReturnType2());
        analyseReturnInfo(method, apiMethodInfo);

        apiMethodInfo.setComment(transform(method.getJavadoc()));
//        getTypeName(returnType2.ge)

        if (annotation instanceof NormalAnnotation) {
            @SuppressWarnings("unchecked")
            List<MemberValuePair> values = ((NormalAnnotation) annotation).values();
            for (MemberValuePair pair : values) {
                String pairName = pair.getName().getFullyQualifiedName();
                Expression value = pair.getValue();
                switch (pairName) {
                    case "value":
                        StringLiteral stringLiteral = (StringLiteral) value;
                        apiMethodInfo.setUrl(stringLiteral.getLiteralValue());
                        break;
                    case "method":
                        if (value instanceof ArrayInitializer) {
                            ArrayInitializer valueArrayInitializer = (ArrayInitializer) value;
                            @SuppressWarnings("unchecked")
                            List<Expression> expressions = valueArrayInitializer.expressions();

                            ActionType[] actionTypes = new ActionType[expressions.size()];
                            for (int i = 0; i < expressions.size(); i++) {
                                Expression valueItem = expressions.get(i);
                                actionTypes[i] = ActionType.valueOf(((QualifiedName) valueItem).getName().getFullyQualifiedName());
                            }
                            apiMethodInfo.setTypes(actionTypes);
                        } else {
                            ActionType actionType = ActionType.valueOf(((QualifiedName) value).getName().getFullyQualifiedName());
                            apiMethodInfo.setTypes(new ActionType[]{actionType});
                        }
                        break;
                }
            }
        } else if (annotation instanceof SingleMemberAnnotation) {
            SingleMemberAnnotation singleMemberAnnotation = (SingleMemberAnnotation) annotation;

            StringLiteral stringLiteral = (StringLiteral) singleMemberAnnotation.getValue();
            apiMethodInfo.setUrl(stringLiteral.getLiteralValue());
        }

        //处理注解
        List modifiers = method.modifiers();
        for (Object o : modifiers) {
            if (o instanceof Annotation && o != annotation) {
                Annotation methodAnnotation = (Annotation) o;
                AnnotationInfo annotationInfo = transform(methodAnnotation);
                apiMethodInfo.addAnnotation(annotationInfo);
                /**
                 * 处理Account授权
                 */
                if (annotationInfo.getTypeInfo().getFullName().equals(Account.class.getName())) {
                    if (methodAnnotation instanceof SingleMemberAnnotation) {
                        String value = ((SingleMemberAnnotation) methodAnnotation).getValue().toString();
                        apiMethodInfo.setAccount(Boolean.valueOf(value));
                    } else if (methodAnnotation instanceof NormalAnnotation) {
                        @SuppressWarnings("unchecked")
                        List<MemberValuePair> values = ((NormalAnnotation) methodAnnotation).values();
                        for (MemberValuePair pair : values) {
                            String pairName = pair.getName().getFullyQualifiedName();
                            String value = pair.getValue().toString();
                            switch (pairName) {
                                case "value":
                                    apiMethodInfo.setAccount(Boolean.valueOf(value));
                                    break;
                            }
                        }
                    }
                }
            }
        }
//        if (apiMethodInfo.getAccountParam() != null) {
//            if (!apiMethodInfo.isAccount()) {
//                throw new AnalyseException("不对！需要登录参数的怎么能不需要登录？" + jdtInfo);
//            }
//        }
        analyseMethodParamsInfo(apiMethodInfo, method);
        return apiMethodInfo;
    }

    private void analyseReturnInfo(MethodDeclaration method, ApiMethodInfo apiMethodInfo) {
        TypeInfo resultType = jdtInfo.analyseType(method.getReturnType2());
        if (resultType == null) {
            throw new AnalyseException("返回类型不能为空!" + method.getReturnType2());
        }


        /**
         * 真真正正的返回类型
         */
        Class<?> cls = null;
        try {
            cls = resultType.toClass();
        } catch (ClassNotFoundException ignored) {
        }
        if (cls == null || !Result.class.isAssignableFrom(cls)) {
            apiMethodInfo.setResultType(resultType);
            TypeInfo resultWrappedType = new TypeInfo(
                    TypeInfo.Type.OTHER,
                    Result.class.getPackage().getName(),
                    Result.class.getSimpleName(),
                    false,
                    Collections.singletonList(resultType),
                    false, true
            );

            apiMethodInfo.setResultWrappedType(resultWrappedType);
            apiMethodInfo.setResultDataType(resultType);
        } else {
            if (CollectionUtils.isEmpty(resultType.getTypeArguments())) {
                throw new AnalyseException("类型不存在！!" + resultType);
            }
            if (resultType.getTypeArguments().size() != 1) {
                throw new AnalyseException("返回参数的类型变量数只能是1！!" + resultType);
            }
            TypeInfo realResultType = resultType.getTypeArguments().get(0);
            apiMethodInfo.setResultType(realResultType);
            apiMethodInfo.setResultWrappedType(resultType);

            if (cls.equals(Result.class)) {
                apiMethodInfo.setResultDataType(realResultType);
            } else {
                java.lang.reflect.Type type = getResultGenericSuperclass(cls);
                TypeInfo typeInfo = jdtInfo.form(type);
                if (typeInfo.getTypeArguments().size() != 1) {
                    throw new AnalyseException("Result子类 类型变量数只能是1！!" + resultType);
                }


                TypeInfo resultDataType = typeInfo.getTypeArguments().get(0).replaceGeneric(realResultType);
                apiMethodInfo.setResultDataType(
                        resultDataType
                );
            }
        }
    }

    private static java.lang.reflect.Type getResultGenericSuperclass(Class<?> cls) {
        Class<?> superclass = cls.getSuperclass();
        if (superclass.equals(Result.class)) {
            java.lang.reflect.Type genericSuperclass = cls.getGenericSuperclass();
            return genericSuperclass;
        } else {
            return getResultGenericSuperclass(superclass);
        }
    }

    /**
     * 处理函数参数
     */
    private void analyseMethodParamsInfo(ApiMethodInfo apiMethodInfo, MethodDeclaration method) {
        List parameters = method.parameters();

        for (Object parameter : parameters) {
            SingleVariableDeclaration paramDeclaration = (SingleVariableDeclaration) parameter;
            if (paramDeclaration.isVarargs()) {
                throw new AnalyseException("暂时不支持可变参数 varargs");
            }
            String paramName = paramDeclaration.getName().getFullyQualifiedName();
            ApiMethodParamInfo fieldInfo = new ApiMethodParamInfo(paramName, jdtInfo.analyseType(paramDeclaration.getType()));
            List modifiers = paramDeclaration.modifiers();

            transformAnnotations(fieldInfo, modifiers);

            for (AnnotationInfo annotationInfo : fieldInfo.getAnnotations()) {
                String annotationFullName = annotationInfo.getTypeInfo().getFullName();
                if (annotationFullName.equals(PathVariable.class.getName())) {
                    fieldInfo.setPathVariable(true);
                } else if (annotationFullName.equals(Valid.class.getName())) {
                    fieldInfo.setFormParam(true);
                }
            }

            if (fieldInfo.isFormParam() && fieldInfo.isPathVariable()) {
                throw new AnalyseException("参数不能同时是路径参数和form" + fieldInfo);
            }
            if (fieldInfo.isFormParam()) {
                if (fieldInfo.getTypeInfo().isArray()) {
                    throw new AnalyseException("表单对象不支持数组!" + fieldInfo);
                }
            }
            if (fieldInfo.getTypeInfo().getType() == TypeInfo.Type.VOID) {
                throw new AnalyseException("void 类型只能用于返回值");
            }
            apiMethodInfo.addParam(fieldInfo);
        }
    }


}
