package org.forkjoin.apikit.info;

import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.builder.BuilderUtils;
import org.forkjoin.apikit.oldmodel.ModuleInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * api 信
 * @author zuoge85 on 15/6/11.
 */
public class ApiInfo extends ModuleInfo {
    private ArrayList<ApiMethodInfo> methodInfos = new ArrayList<>();

    private Set<String> idSet = new HashSet<>();

    public void addApiMethod(ApiMethodInfo apiMethodInfo) {
        methodInfos.add(apiMethodInfo);
        String id =  apiMethodInfo.getName();
        int i = 0;
        while(idSet.contains(id)){
            id += i++;
        }
        idSet.add(id);
        apiMethodInfo.setId(id);
    }

    public ArrayList<ApiMethodInfo> getMethodInfos() {
        return methodInfos;
    }

    @Override
    public String toString() {
        return "ApiInfo{" +
                "methodInfos=" + methodInfos +
                '}';
    }


    public String comment(String start) {
        return BuilderUtils.formatComment(getComment(), start);
    }

    @Override
    public String getFiledName(){
        String name = getName();
        if(StringUtils.isNotEmpty(name)){
            char c = name.charAt(0);
            return Character.toLowerCase(c) + name.substring(1);
        }
        return null;
    }
}
