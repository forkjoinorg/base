package org.forkjoin.spring;

import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author zuoge85 on 15/4/25.
 */
public class AccountParamArgumentResolver implements HandlerMethodArgumentResolver,Ordered {

//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        AccountParam parameterAnnotation = parameter.getParameterAnnotation(AccountParam.class);
//        return parameterAnnotation != null;
//    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute(AccountHandlerInterceptor.ACCOUNT_REQUEST_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
