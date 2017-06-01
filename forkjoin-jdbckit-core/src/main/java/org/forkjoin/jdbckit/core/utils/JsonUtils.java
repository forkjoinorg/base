package org.forkjoin.jdbckit.core.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class JsonUtils {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_PATTERN);
        dateFormat.setLenient(false);
        mapper.setDateFormat(dateFormat);
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static final JsonFactory jsonFactory = new JsonFactory();

    /**
     * 不能处理复杂情况,和继承情况
     */
    public static String serialize(Object o) {
        try {
            if (o instanceof List) {
                return mapper.writeValueAsString(((List<?>) o).toArray());
            }
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 不能处理复杂情况,和继承情况
     */
    public static <T> T deserialize(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T deserialize(String json, Class<?> parentClass, Class<?>... elementClasses) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(
                    parentClass, parentClass, elementClasses
            );
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * 不能处理复杂情况,和继承情况
     * 对付一般的List&lt;MyClass&gt; 方式足够了
     */
    public static <T> T deserialize(String json, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static JsonNode deserialize(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
