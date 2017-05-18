package org.forkjoin.apikit.example.services;

import org.forkjoin.apikit.example.ExampleAccount;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zuoge85@gmail.com on 2017/5/17.
 */
@Service
public class AccountService {
    private ConcurrentHashMap<String, ExampleAccount> map = new ConcurrentHashMap<>();

    public String login() {
        String token = UUID.randomUUID().toString();
        map.put(token, new ExampleAccount());
        return token;
    }

    public ExampleAccount getByToken(String token) {
        if(token == null){
            return null;
        }
        return map.get(token);
    }
}
