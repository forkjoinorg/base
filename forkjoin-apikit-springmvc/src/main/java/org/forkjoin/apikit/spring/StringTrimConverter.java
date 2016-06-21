package org.forkjoin.apikit.spring;

import org.springframework.core.convert.converter.Converter;

/**
 * @author zuoge85 on 15/4/20.
 */
public class StringTrimConverter implements Converter<String,String> {

    @Override
    public String convert(String source) {
        if(source == null){
            return null;
        }
        return source.trim();
    }
}
