package org.forkjoin.apikit.utils;

import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.ApiMethodInfo;
import org.forkjoin.apikit.info.ApiMethodParamInfo;
import org.forkjoin.apikit.info.TypeInfo;
import org.forkjoin.apikit.wrapper.BuilderWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author zuoge85@gmail.com on 2017/7/6.
 */
public class ApiImport {
    private TreeSet<String> importSet = new TreeSet<>();
    private BuilderWrapper<ApiInfo> builderWrapper;

    public ApiImport(BuilderWrapper<ApiInfo> builderWrapper) {
        this.builderWrapper = builderWrapper;
        ArrayList<ApiMethodInfo> methodInfos = builderWrapper.getModuleInfo().getMethodInfos();
        for (int i = 0; i < methodInfos.size(); i++) {
            ApiMethodInfo apiMethodInfo = methodInfos.get(i);
            addImport(apiMethodInfo.getResultType());

            ArrayList<ApiMethodParamInfo> params = apiMethodInfo.getParams();
            for (int j = 0; j < params.size(); j++) {
                ApiMethodParamInfo apiMethodParamInfo = params.get(j);
                if (apiMethodParamInfo.isFormParam() || apiMethodParamInfo.isPathVariable()) {
                    TypeInfo typeInfo = apiMethodParamInfo.getTypeInfo();
                    addImport(typeInfo);
                }
            }
        }
    }

    public void add(String packageName, String name) {
        StringBuilder sb = new StringBuilder();
        ApiInfo moduleInfo = builderWrapper.getModuleInfo();
        String moduleInfoPackageName = moduleInfo.getPackageName();
        //自己的目录级别
        int myLevel = moduleInfoPackageName.split(".").length + 1;

        importSet.add(sb.append("import ")
                .append(name)
                .append(" from './")
                .append(StringUtils.repeat("../", myLevel))
                .append(packageName.replace(".", "/")).append('/').append(name).append("'\n").toString());
    }


    private void addImport(TypeInfo type) {
        if (type.isInside()) {
            add(builderWrapper.getPack(type.getPackageName()), type.getName());
        }
        List<TypeInfo> typeArguments = type.getTypeArguments();
        for (int i = 0; i < typeArguments.size(); i++) {
            TypeInfo typeInfo = typeArguments.get(i);
            addImport(typeInfo);
        }
    }

    @Override
    public String toString() {
        return StringUtils.join(importSet.toArray(), "\n");
    }
}
