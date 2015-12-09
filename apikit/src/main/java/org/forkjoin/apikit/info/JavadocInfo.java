package org.forkjoin.apikit.info;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * javadoc 注释信息
 * List<Map.Entry<String, List<String>>> 结构是 tagName -> fragments list
 * @author zuoge85 on 15/11/16.
 */
public class JavadocInfo {
    private List<Map.Entry<String, List<String>>> tags = new ArrayList<>();

    public void add(String tagName, List<String> fragmentsInfo) {
        tags.add(new AbstractMap.SimpleImmutableEntry<String, List<String>>(tagName, fragmentsInfo));
    }

    public List<Map.Entry<String, List<String>>> getTags() {
        return tags;
    }
}
