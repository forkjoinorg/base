package org.forkjoin.apikit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author zuoge85@gmail.com on 2017/5/17.
 */
public class JacksonJsonConvert implements JsonConvert {
    private ObjectMapper mapper = new ObjectMapper();

    public JacksonJsonConvert(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T deserialize(String json, final Type type) {
        try {
            return mapper.readValue(json, new TypeReference<Object>(){
                public Type getType() {
                    return type;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
