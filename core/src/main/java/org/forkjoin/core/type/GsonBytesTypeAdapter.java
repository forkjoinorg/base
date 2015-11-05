package org.forkjoin.core.type;

import com.google.gson.*;
import org.apache.commons.codec.binary.Base64;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author zuoge85 on 15/7/16.
 */
public final class GsonBytesTypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
    public GsonBytesTypeAdapter() {

    }

    public JsonElement serialize(byte[] data, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(Base64.encodeBase64URLSafeString(data));
    }

    public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        return Base64.decodeBase64(json.getAsString());
    }
}
