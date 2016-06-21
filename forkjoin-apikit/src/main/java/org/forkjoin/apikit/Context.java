package org.forkjoin.apikit;

import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.PackageInfo;

/**
 *
 */
public class Context {
    protected PackageInfo<MessageInfo> messages = new PackageInfo<>();
    protected PackageInfo<ApiInfo> apis = new PackageInfo<>();


    public void add(ModuleInfo m) {
        if (m instanceof MessageInfo) {
            messages.add(m.getPackageName(), (MessageInfo) m);
        } else if (m instanceof ApiInfo) {
            apis.add(m.getPackageName(), (ApiInfo) m);
        }
    }


    public PackageInfo<ApiInfo> getApis() {
        return apis;
    }

    public PackageInfo<MessageInfo> getMessages() {
        return messages;
    }
}
