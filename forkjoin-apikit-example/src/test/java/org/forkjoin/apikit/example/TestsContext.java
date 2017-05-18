package org.forkjoin.apikit.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.forkjoin.apikit.JacksonJsonConvert;
import org.forkjoin.apikit.JsonConvert;
import org.forkjoin.apikit.client.HttpClientAdapter;
import org.forkjoin.apikit.example.client.ApiManager;
import org.forkjoin.apikit.spring.client.ApacheHttpClientAdapter;
import org.forkjoin.apikit.spring.client.MockHttpClientAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionService;

/**
 * @author zuoge85@gmail.com on 2017/5/15.
 */


@Configuration
@ComponentScan
public class TestsContext {

    @Bean
    public ApiManager apiManager(HttpClientAdapter httpClientAdapter) {
        return new ApiManager(httpClientAdapter);
    }

    @Profile("mock")
    @Bean(destroyMethod = "close")
    public HttpClientAdapter mockHttpClientAdapter(ConversionService conversionService, JsonConvert jsonConvert) {
        return new MockHttpClientAdapter(
                "http://127.0.0.1:8080/v1/", conversionService, jsonConvert
        );
    }

    @Profile("remote")
    @Bean(destroyMethod = "close")
    public HttpClientAdapter remoteHttpClientAdapter(ConversionService conversionService, JsonConvert jsonConvert) {
        return new ApacheHttpClientAdapter(
                "http://127.0.0.1:8080/v1/", conversionService, jsonConvert
        );
    }

    @Bean
    public JsonConvert jsonConvert(ObjectMapper mapper) {
        return new JacksonJsonConvert(mapper);
    }
}
