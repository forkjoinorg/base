package org.forkjoin.apikit.example;

import org.apache.commons.lang3.ArrayUtils;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.form.TestForm;
import org.forkjoin.apikit.example.model.TestObject;
import org.forkjoin.apikit.example.model.TestObjectList;
import org.forkjoin.apikit.example.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 测试一些复杂的情况
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class ReactorApiController {

    @RequestMapping(value = "reactor/testFlux", method = {RequestMethod.POST})
    @Account(false)
    public Flux<User> testFlux(@Valid TestForm testForm) throws Exception {
        return Flux.empty();
    }

    @RequestMapping(value = "reactor/testMono", method = {RequestMethod.POST})
    @Account(false)
    public Mono<LocalDateTime> testMono() throws Exception {
        return Mono.just(LocalDateTime.now());
    }
}