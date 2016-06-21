package org.forkjoin.apikit.spring;


import org.apache.commons.codec.binary.Base64;
import org.springframework.core.convert.converter.Converter;


/**
 *
 */
public class BytesConverter implements Converter<String, byte[]> {
    @Override
    public byte[] convert(String source) {
        return Base64.decodeBase64(source);
    }
}