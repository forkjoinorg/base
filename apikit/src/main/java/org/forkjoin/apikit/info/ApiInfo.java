package org.forkjoin.apikit.info;

import org.forkjoin.apikit.AnalyseException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * api 信
 *
 * @author zuoge85 on 15/6/11.
 */
public class ApiInfo extends ModuleInfo {
    private Map<String, ApiMethodInfo> methodInfosMap = new LinkedHashMap<>();

//    private Set<String> idSet = new HashSet<>();

    public void addApiMethod(ApiMethodInfo apiMethodInfo) {
        if (methodInfosMap.put(apiMethodInfo.getUrl(), apiMethodInfo) != null) {
            throw new AnalyseException("严重错误,url 重复:" + apiMethodInfo.getUrl());
        }
    }

    public Map<String, ApiMethodInfo> getMethodInfosMap() {
        return methodInfosMap;
    }

    public ApiMethodInfo get(String url){
        return methodInfosMap.get(url);
    }

    @Override
    public String toString() {
        return "ApiInfo{" +
                "methodInfos=" + methodInfosMap +
                '}';
    }


//    @Override
//    public String getFiledName() {
//        String name = getName();
//        if (StringUtils.isNotEmpty(name)) {
//            char c = name.charAt(0);
//            return Character.toLowerCase(c) + name.substring(1);
//        }
//        return null;
//    }
}
