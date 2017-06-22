package org.forkjoin.apikit.client.core;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author zuoge85@gmail.com on 2017/5/17.
 */
public interface JsonConvert {
    <T> T deserialize(String json, final Type type);
}
