package org.forkjoin.apikit.example.api;

import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.example.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 账户api
 *
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class SysApiController {

    @Autowired
    private AccountService accountService;

    /**
     * @return 返回授权token
     */
    @RequestMapping(value = "status", method = RequestMethod.GET)
    @Account(false)
    public String status() {
        return "success";
    }

    /**
     * @return 返回服务器当前时间
     */
    @RequestMapping(value = "now", method = RequestMethod.GET)
    @Account(false)
    public Date login() {
        return new Date();
    }
}