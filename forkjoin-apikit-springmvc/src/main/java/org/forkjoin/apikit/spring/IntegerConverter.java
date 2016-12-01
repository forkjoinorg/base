package org.forkjoin.apikit.spring;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.convert.converter.Converter;

/**
 *
 */
public class IntegerConverter implements Converter<String, Integer> {
    public IntegerConverter() {
    }

    public Integer convert(String source) {
        return NumberUtils.toInt(StringUtils.trim(source));
    }
}
