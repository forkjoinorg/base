package org.forkjoin.apikit.spring;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * @author zuoge85 on 15/4/25.
 */
public class NoCacheInterceptor extends HandlerInterceptorAdapter {

    private Charset charset;

    public NoCacheInterceptor() {

    }

    public NoCacheInterceptor(Charset charset) {
        this.charset = charset;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Cache-Control", "no-cache,no-store");
        response.setIntHeader("Expires", -1);
        response.setHeader("Pragma", "no-cache");
        if (charset != null) {
            response.setCharacterEncoding(charset.name());
        }

        return super.preHandle(request, response, handler);
    }
}
