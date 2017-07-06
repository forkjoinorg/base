package org.forkjoin.apikit.spring;

import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.spring.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zuoge85 on 15/4/25.
 */
public abstract class AccountHandlerInterceptor<T> extends HandlerInterceptorAdapter {
    public static final String ACCOUNT_REQUEST_ATTRIBUTE = AccountParamArgumentResolver.class.getName() + "ACCOUNT_REQUEST_ATTRIBUTE";
    private static final Logger log = LoggerFactory.getLogger(AccountHandlerInterceptor.class);
    public static final String ACCOUNT_TOKEN_HEADER_NAME = "authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //默认都需要权限
            Account methodAnnotation = handlerMethod.getMethodAnnotation(Account.class);
            if (methodAnnotation == null || methodAnnotation.value()) {
                T accountObject = getAccountObject(request);
                if (accountObject == null) {
                    throw new AccountRuntimeException("未登录:" + request.getRequestURI() + ",ParameterMap:" + request.getParameterMap());
                }
                if (check(handlerMethod, accountObject)) {
                    request.setAttribute(ACCOUNT_REQUEST_ATTRIBUTE, accountObject);
                } else {
                    throw new AccountRuntimeException("没有权限:" + request.getRequestURI() + ",ParameterMap:" + RequestUtils.dump(request));
                }
            }
            return super.preHandle(request, response, handler);
        }
        return super.preHandle(request, response, handler);
    }

    protected boolean check(HandlerMethod handlerMethod, T t) throws Exception {
        return true;
    }

    protected abstract T getAccountObject(HttpServletRequest request) throws Exception;
}
