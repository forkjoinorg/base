package org.forkjoin.apikit.wrapper;

import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.AnnotationInfo;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.ApiMethodInfo;
import org.forkjoin.apikit.info.ApiMethodParamInfo;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//import org.forkjoin.spring.annotation.Account;
//import org.forkjoin.spring.annotation.AccountParam;

/**
 * @author zuoge85 on 15/6/17.
 */
public class JavaClientApiWrapper extends JavaApiWrapper {

    private boolean isAnnotations = false;
    public JavaClientApiWrapper(Context context, ApiInfo moduleInfo, String rootPackage) {
        super(context, moduleInfo, rootPackage);
    }

    @Override
    public String formatAnnotations(List<AnnotationInfo> annotations, String start) {
        return null;
    }

    @Override
    public void init() {
        addExclude("org.forkjoin.apikit.spring.Result");
        addExclude("org.springframework.web.bind.annotation.PathVariable");
        addExclude("javax.validation");
        addExclude("org.hibernate.validator");
        addExclude("org.forkjoin.apikit.core.Account");
        addExclude("org.forkjoin.apikit.core.ActionType");
        addExclude("org.forkjoin.apikit.core.Api");
        addExclude("org.forkjoin.apikit.core.ApiMethod");
        super.init();
    }

    public String asyncParams(ApiMethodInfo method) {
        String params = params(method);
        if (params.length() > 0) {
            params += ", ";
        }
        return params + "Callback<" + result(method) + "> callable";
    }

    public String asyncReuestCallbackParams(ApiMethodInfo method) {
        String params = params(method, false);
        if (params.length() > 0) {
            params += ", ";
        }
        return params + "Callback<" + result(method) + "> callable, " +
                "RequestCallback<" + result(method) + "> requestCallback";
    }

    @Override
    public String params(ApiMethodInfo method, boolean isAnnotation) {
        StringBuilder sb = new StringBuilder();
        ArrayList<ApiMethodParamInfo> params = method.getParams();
        for (int i = 0; i < params.size(); i++) {
            ApiMethodParamInfo attributeInfo = params.get(i);
            if(attributeInfo.isFormParam() || attributeInfo.isPathVariable()){
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(toJavaTypeString(attributeInfo.getTypeInfo(), false, true));
                sb.append(' ');
                sb.append(attributeInfo.getName());
            }
        }
        return sb.toString();
    }


    public String args(ApiMethodInfo method, boolean isAnnotation) {
        StringBuilder sb = new StringBuilder();

        ArrayList<ApiMethodParamInfo> params = method.getParams();
        for (int i = 0; i < params.size(); i++) {
            ApiMethodParamInfo attributeInfo = params.get(i);
            if(attributeInfo.isFormParam() || attributeInfo.isPathVariable()){
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(attributeInfo.getName());
            }
        }
        return sb.toString();
    }

    public boolean isAnnotations() {
        return isAnnotations;
    }

    public void setAnnotations(boolean annotations) {
        isAnnotations = annotations;
    }
}
