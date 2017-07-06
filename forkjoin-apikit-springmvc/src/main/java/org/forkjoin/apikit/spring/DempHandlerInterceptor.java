package org.forkjoin.apikit.spring;

import org.forkjoin.apikit.spring.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zuoge85 on 15/4/25.
 */
public class DempHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(DempHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("request:{}", RequestUtils.dump(request));
        }
        return true;
    }
}
