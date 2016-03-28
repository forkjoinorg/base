package org.forkjoin.apikit.wrapper;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringEscapeUtils;
import org.forkjoin.apikit.core.AccountParam;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.ApiMethodInfo;
import org.forkjoin.apikit.info.ApiMethodParamInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Set;

//import org.forkjoin.spring.annotation.Account;
//import org.forkjoin.spring.annotation.AccountParam;

/**
 * @author zuoge85 on 15/6/17.
 */
public class JavaControllerWrapper extends JavaApiWrapper {
    public static final Set<String> excludesSet = ImmutableSet.of(
            ResponseBody.class.getName(),
//            Account.class.getName(),
            RequestMapping.class.getName()
//            ApiMethod.class.getName()
    );

    private String apiAccountClassName;


    public JavaControllerWrapper(Context context, ApiInfo moduleInfo, String rootPackage) {
        super(context, moduleInfo, rootPackage);

    }

    @Override
    public void init() {
        super.addImport(apiAccountClassName);
        super.init();
    }

    public String getApiAccountClassName() {
        return apiAccountClassName;
    }

    public void setApiAccountClassName(String apiAccountClassName) {
        this.apiAccountClassName = apiAccountClassName;
    }

    @Override
    public String getName() {
        return moduleInfo.getName();
    }

    public String annotations(ApiMethodInfo method, String lineStart) {
        StringBuilder sb = new StringBuilder();

        sb.append(lineStart);
        sb.append("@").append(ResponseBody.class.getSimpleName()).append("\n");
        if (!method.isAccount()) {
            sb.append(lineStart);
//            sb.append("@").append(Account.class.getSimpleName()).append("(false)\n");
        }

        sb.append(lineStart);
        sb.append("@").append(RequestMapping.class.getSimpleName()).append("(value = \"")
                .append(StringEscapeUtils.escapeJava(method.getUrl()))
                .append("\", method = RequestMethod.")
                .append(method.getType().name())
                .append(")\n");

        sb.append(formatAnnotations(method.getAnnotations(), lineStart));
        return sb.toString();
    }

    @Override
    public String params(ApiMethodInfo method, boolean isAnnotation) {
        StringBuilder sb = new StringBuilder();
        ArrayList<ApiMethodParamInfo> params = method.getParams();
        for (int i = 0; i < params.size(); i++) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            ApiMethodParamInfo param = params.get(i);
            sb.append(toJavaTypeString(param.getTypeInfo(), false, true));
            sb.append(' ');
            sb.append(param.getName());
        }
        if (method.getAccountParam() != null) {
            String aSimpleName = apiAccountClassName;
            if (sb.length() > 0) {
                sb.append(", ");
            }
            if (isAnnotation) {
                sb.append('@');
                sb.append(AccountParam.class.getSimpleName());
                sb.append(' ');
            }
            sb.append(aSimpleName);
            sb.append(' ');
            sb.append(method.getAccountParam());
        }
        return sb.toString();
    }
}
