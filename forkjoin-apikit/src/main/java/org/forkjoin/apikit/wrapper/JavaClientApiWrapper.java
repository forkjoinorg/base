package org.forkjoin.apikit.wrapper;

import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

//import org.forkjoin.spring.annotation.Account;
//import org.forkjoin.spring.annotation.AccountParam;

/**
 * @author zuoge85 on 15/6/17.
 */
public class JavaClientApiWrapper extends JavaApiWrapper {

    private boolean isAnnotations = false;
    private TreeSet<String> importSet = new TreeSet<>();

    public JavaClientApiWrapper(Context context, ApiInfo moduleInfo, String rootPackage) {
        super(context, moduleInfo, rootPackage);
    }

    @Override
    public String formatAnnotations(List<AnnotationInfo> annotations, String start) {
        return null;
    }

    @Override
    public void init() {
        initImport();
    }

    @Override
    public void initImport() {
        ArrayList<ApiMethodInfo> methodInfos = moduleInfo.getMethodInfos();
        for (int i = 0; i < methodInfos.size(); i++) {
            ApiMethodInfo apiMethodInfo = methodInfos.get(i);
            addImport(apiMethodInfo.getResultType());

            importSet.add(apiMethodInfo.getResultWrappedType().getFullName());

            ArrayList<ApiMethodParamInfo> params = apiMethodInfo.getParams();
            for (int j = 0; j < params.size(); j++) {
                ApiMethodParamInfo apiMethodParamInfo = params.get(j);
                if (apiMethodParamInfo.isFormParam() || apiMethodParamInfo.isPathVariable()) {
                    TypeInfo typeInfo = apiMethodParamInfo.getTypeInfo();
                    addImport(typeInfo);
                }
            }
        }
        for (String classFullName : importSet) {
            addImport(classFullName);
        }
    }

    private void addImport(TypeInfo type) {
        String classFullName = getFullName(type);
        if (type.isInside()) {
            importSet.add(classFullName);
        }
        List<TypeInfo> typeArguments = type.getTypeArguments();
        for (int i = 0; i < typeArguments.size(); i++) {
            TypeInfo typeInfo = typeArguments.get(i);
            addImport(typeInfo);
        }
    }


    public String asyncParams(ApiMethodInfo method) {
        String params = params(method);
        if (params.length() > 0) {
            params += ", ";
        }
        return params + "Callback<"+method.getResultWrappedType().getName()+"<"+result(method)+">," + resultData(method) + "> callable";
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
            if (attributeInfo.isFormParam() || attributeInfo.isPathVariable()) {
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
            if (attributeInfo.isFormParam() || attributeInfo.isPathVariable()) {
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
