package org.forkjoin.apikit.core;

import java.util.List;
import java.util.Map;

/**
 * api message
 */
public interface ApiMessage {
    List<Map.Entry<String, Object>> encode(String parent, List<Map.Entry<String, Object>> $list);
}