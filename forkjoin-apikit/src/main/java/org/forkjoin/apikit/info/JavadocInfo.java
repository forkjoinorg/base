package org.forkjoin.apikit.info;

import com.google.common.base.Supplier;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * javadoc 注释信息
 *
 *
 *
 * @author zuoge85 on 15/11/16.
 */
public class JavadocInfo {

    //<code>List<Map.Entry<String, List<String>>> 结构是 tagName -> fragments list</code>
    private ListMultimap<String, String> tags = Multimaps.newListMultimap(
            new LinkedHashMap<String, Collection<String>>(), new Supplier<List<String>>() {
                @Override
                public List<String> get() {
                    return new CopyOnWriteArrayList<>();
                }
            }
    );

    public void add(String tagName, List<String> fragmentsInfo) {
        tags.putAll(tagName, fragmentsInfo);
    }

    public ListMultimap<String, String> getTags() {
        return tags;
    }

    public Map.Entry<String, Collection<String>> getTags(int index) {
        int i = 0;
        for (Map.Entry<String, Collection<String>> entry : tags.asMap().entrySet()) {
            if (i == index) {
                return entry;
            }
            i++;
        }
        return null;
    }
}
