package org.forkjoin.apikit.example;

import org.forkjoin.apikit.example.services.AccountService;
import org.forkjoin.apikit.spring.AccountHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zuoge85@gmail.com on 2017/5/16.
 */
public class ExampleAccountHandlerInterceptor extends AccountHandlerInterceptor<ExampleAccount> {
    @Autowired
    private AccountService accountService;

    @Override
    protected ExampleAccount getAccountObject(HttpServletRequest request) {
        String token = request.getHeader(ACCOUNT_TOKEN_HEADER_NAME);
        return accountService.getByToken(token);
    }
}
