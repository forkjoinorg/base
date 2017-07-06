package org.forkjoin.apikit.client.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author zuoge85@gmail.com on 2017/5/17.
 */
public class JacksonJsonSerialize implements JsonSerialize {
    private ObjectMapper mapper = new ObjectMapper();

    public JacksonJsonSerialize(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String serialize(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
