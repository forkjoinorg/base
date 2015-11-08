package org.forkjoin.apikit;

import org.forkjoin.apikit.oldmodel.ApiInfo;

/**
 * @author zuoge85 on 15/9/9.
 */
public class ApiAccount {
    private String fullClassName;

    public String getImport(ApiInfo apiInfo) {
        return fullClassName;
    }

    public String getSimpleName(ApiInfo apiInfo) {
        return getSimpleByFullName(fullClassName);
    }

    public static ApiAccount form(String fullClassName) {
        ApiAccount apiAccount = new ApiAccount();
        apiAccount.fullClassName = fullClassName;
        return apiAccount;
    }

    public String getSimpleByFullName(String fullName) {
        int i = fullName.lastIndexOf(".");
        return i > -1 ? fullName.substring(i + 1) : fullName;
    }
}
