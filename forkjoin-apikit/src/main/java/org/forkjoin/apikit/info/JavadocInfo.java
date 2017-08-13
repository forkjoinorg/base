package org.forkjoin.apikit.info;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;

import java.util.*;
import java.util.stream.Collectors;

/**
 * javadoc 注释信息
 *
 * @author zuoge85 on 15/11/16.
 */
public class JavadocInfo {

    //<code>List<Map.Entry<String, List<String>>> 结构是 tagName -> fragments list</code>
    private ListMultimap<String, List<String>> tags = Multimaps.newListMultimap(
            new LinkedHashMap<String, Collection<List<String>>>(), ArrayList::new
    );

    public void add(String tagName, List<String> fragmentsInfo) {
        tags.put(tagName, fragmentsInfo);
    }

    public List<List<String>> get(String tagName) {
        return tags.get(tagName);
    }

    public String getToString(String tagName, CharSequence delimiter) {
        return getToString(tagName, delimiter, "", "");
    }

    public String getToString(String tagName) {
        return getToString(tagName, " ", "", "");
    }

    public String getToString(String tagName, CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return tags.get(tagName).stream().flatMap(Collection::stream).collect(Collectors.joining(delimiter, prefix, suffix));
    }

    public String getByParamToString(String param, CharSequence delimiter) {
        return getByParamToString(param, delimiter, "", "");
    }

    public String getByParamToString(String param, CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return tags.get("@param").stream()
                .filter(r -> !r.isEmpty() && r.get(0).equals(param))
                .flatMap(item -> item.stream().skip(1))
                .collect(Collectors.joining(delimiter, prefix, suffix));
    }

    public ListMultimap<String, List<String>> getTags() {
        return tags;
    }

    public Map.Entry<String, Collection<List<String>>> getTags(int index) {
        int i = 0;
        for (Map.Entry<String, Collection<List<String>>> entry : tags.asMap().entrySet()) {
            if (i == index) {
                return entry;
            }
            i++;
        }
        return null;
    }
}
