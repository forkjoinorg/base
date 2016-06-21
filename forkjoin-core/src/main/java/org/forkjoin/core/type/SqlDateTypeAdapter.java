package org.forkjoin.core.type;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author zuoge85 on 15/7/16.
 */
public final class SqlDateTypeAdapter implements JsonSerializer<java.sql.Date>, JsonDeserializer<java.sql.Date> {
    public SqlDateTypeAdapter() {

    }

    public JsonElement serialize(java.sql.Date data, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(data.toString());
    }

    public java.sql.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        return java.sql.Date.valueOf(json.getAsString());
    }
}
