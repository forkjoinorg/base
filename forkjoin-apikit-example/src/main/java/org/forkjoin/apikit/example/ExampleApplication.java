package org.forkjoin.apikit.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author zuoge85@gmail.com on 2017/5/11.
 */

@SpringBootApplication
@ComponentScan
@Import({ExampleContext.class})
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
