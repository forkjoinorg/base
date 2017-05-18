package org.forkjoin.apikit.spring;


import org.apache.commons.codec.binary.Base64;
import org.springframework.core.convert.converter.Converter;


/**
 *
 */
public class BytesToStringConverter implements Converter<byte[],String> {
    @Override
    public String convert(byte[] source) {
        return Base64.encodeBase64URLSafeString(source);
    }
}