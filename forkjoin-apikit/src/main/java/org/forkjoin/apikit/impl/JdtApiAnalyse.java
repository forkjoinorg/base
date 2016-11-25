package org.forkjoin.apikit.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.*;
import org.forkjoin.apikit.AnalyseException;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.ActionType;
import org.forkjoin.apikit.core.ApiMethod;
import org.forkjoin.apikit.info.*;
import org.forkjoin.apikit.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
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

        MethodDeclaration[] methods = jdtInfo.getType().getMethods();
        for (MethodDeclaration method : methods) {
            List modifiers = method.modifiers();
            for (Object o : modifiers) {
                if (o instanceof Annotation) {
                    Annotation annotation = (Annotation) o;
                    if (equalsType(annotation.getTypeName(), ApiMethod.class)) {
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
                    case "type":
                        ActionType actionType = ActionType.valueOf(((QualifiedName) value).getName().getFullyQualifiedName());
                        apiMethodInfo.setType(actionType);
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
                                case "param":
                                    if (StringUtils.isNotEmpty(value)) {
                                        apiMethodInfo.setAccountParam(value.substring(1, value.length() - 1));
                                    }
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
        if(resultType == null){
            throw new AnalyseException("返回类型不能为空!" + method.getReturnType2());
        }
        if(!Result.class.getName().equals(resultType.getFullName())){
            throw new AnalyseException("现在的版本返回类型必须是!" + Result.class.getName());
        }
        if(CollectionUtils.isEmpty(resultType.getTypeArguments())){
            throw new AnalyseException("反正类型不存在！!" + resultType);
        }
        /**
         * 真真正正的返回类型
         */
        TypeInfo realResultType = resultType.getTypeArguments().get(0);
        apiMethodInfo.setResultType(realResultType);
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

            for(AnnotationInfo annotationInfo: fieldInfo.getAnnotations()){
                String annotationFullName = annotationInfo.getTypeInfo().getFullName();
                if (annotationFullName.equals(PathVariable.class.getName())) {
                    fieldInfo.setPathVariable(true);
                } else if (annotationFullName.equals(Valid.class.getName())) {
                    fieldInfo.setFormParam(true);
                }
            }

            if(fieldInfo.isFormParam() && fieldInfo.isPathVariable()){
                throw new AnalyseException("参数不能同时是路径参数和form" + fieldInfo);
            }
            if(fieldInfo.isFormParam()){
                if(fieldInfo.getTypeInfo().isArray()){
                    throw new AnalyseException("表单对象不支持数组!" + fieldInfo);
                }
            }
            if(fieldInfo.getTypeInfo().getType() == TypeInfo.Type.VOID){
                throw new AnalyseException("void 类型只能用于返回值");
            }
            apiMethodInfo.addParam(fieldInfo);
        }
    }


}
