package org.forkjoin.apikit.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zuoge85 on 15/6/14.
 */
public class ModelInfo {
    private Map<String, PackageInfo<MessageInfo>> map = new TreeMap<>();

    private Map<String, PackageInfo<ApiInfo>> apiMap = new TreeMap<>();
    private PackageInfo<ApiInfo> defaultApiPackage;

    public void add(MessageInfo msg) {
        String javaPackage = msg.getPackageName();
        PackageInfo<MessageInfo> p = map.get(javaPackage);
        if (p == null) {
            p = new PackageInfo<>();
            p.setName(javaPackage);
            map.put(javaPackage, p);
        }
        p.add(msg);
    }

    public void add(ApiInfo msg) {
        String javaPackage = msg.getPackageName();
        PackageInfo<ApiInfo> p = apiMap.get(javaPackage);
        if (p == null) {
            p = new PackageInfo<>();
            p.setName(javaPackage);
            apiMap.put(javaPackage, p);

            if(javaPackage.length() == 0){
                this.defaultApiPackage = p;
            }
        }
        p.add(msg);
    }

    public Collection<PackageInfo<MessageInfo>> getMessagePackages(){
        return map.values();
    }

    public Collection<PackageInfo<ApiInfo>> getApiInfoPackages(){
        return apiMap.values();
    }

    public PackageInfo<ApiInfo> getDefaultApiPackage() {
        return defaultApiPackage;
    }
}
