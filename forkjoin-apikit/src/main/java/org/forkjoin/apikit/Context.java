package org.forkjoin.apikit;

import org.forkjoin.apikit.info.*;

/**
 *
 */
public class Context {
    protected PackageInfo<MessageInfo> messages = new PackageInfo<>();
    protected PackageInfo<ApiInfo> apis = new PackageInfo<>();


    private String path;
    private String rootPackage;

    public void add(ModuleInfo m) {
        if (m instanceof MessageInfo) {
            messages.add(m.getPackageName(), (MessageInfo) m);
        } else if (m instanceof ApiInfo) {
            apis.add(m.getPackageName(), (ApiInfo) m);
        }
    }


    public MessageInfo getMessage(TypeInfo typeInfo) {
        return messages.getByType(typeInfo);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public PackageInfo<ApiInfo> getApis() {
        return apis;
    }

    public PackageInfo<MessageInfo> getMessages() {
        return messages;
    }

}
