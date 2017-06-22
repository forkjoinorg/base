package org.forkjoin.apikit.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.forkjoin.apikit.spring.*;
import org.forkjoin.apikit.spring.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author zuoge85@gmail.com on 2017/5/16.
 */
@Configuration
public class ExampleContext extends WebMvcConfigurerAdapter {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:messages/message",
                "classpath:messages/form"
        );
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean
    public ResultMappingJackson2JsonView view() {
        ResultMappingJackson2JsonView resultMappingJackson2JsonView = new ResultMappingJackson2JsonView();
        resultMappingJackson2JsonView.setEncoding(JsonEncoding.UTF8);
        resultMappingJackson2JsonView.setObjectMapper(objectMapper());
        return resultMappingJackson2JsonView;
    }


    @Bean
    public ObjectMapper objectMapper() {
        /**
         * 第一个参数为true ，表示生成的json不生成是java 默认值的字段，这样可能和一些语言客户端冲突，请小心
         */
        ObjectMapper mapper = JsonUtils.create(false, DATE_FORMAT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    @Bean
    public ResultExceptionResolver exceptionResolver() {
        return new ResultExceptionResolver();
    }

    @Bean
    public AccountHandlerInterceptor accountHandlerInterceptor() {
        return new ExampleAccountHandlerInterceptor();
    }

    @Bean
    public I18nResultJacksonHttpMessageConverter converter() {
        I18nResultJacksonHttpMessageConverter converter = new I18nResultJacksonHttpMessageConverter();
        converter.setEncoding(JsonEncoding.UTF8);
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public I18NResultResponseBodyAdvice responseBodyAdvice() {
        return new I18NResultResponseBodyAdvice();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(
                view()
        );
    }


    public void addFormatters(FormatterRegistry registry) {
        //使用yyyy-MM-dd HH:mm:ss 样式的日期转换器
        registry.addConverter(new DateConverter(DATE_FORMAT));
        registry.addConverter(new DateToStringConverter(DATE_FORMAT));
        //基于base64的转换器
        registry.addConverter(new BytesConverter());
        registry.addConverter(new BytesToStringConverter());
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(exceptionResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NoCacheInterceptor(Charset.forName("UTF-8")) {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "*");
                response.setHeader("Access-Control-Allow-Headers", AccountHandlerInterceptor.ACCOUNT_TOKEN_HEADER_NAME);
                return super.preHandle(request, response, handler);
            }
        });
        registry.addInterceptor(accountHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AccountParamArgumentResolver(ExampleAccount.class));
    }
}
