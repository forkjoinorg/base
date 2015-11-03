package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.Config;
import org.forkjoin.apikit.model.ApiInfo;
import org.forkjoin.apikit.model.ApiMethodInfo;
import org.forkjoin.apikit.model.ModelInfo;
import com.google.common.collect.ImmutableSet;
import com.isnowfox.api.ApiMethod;
import com.isnowfox.spring.annotation.Account;
import com.isnowfox.spring.annotation.AccountParam;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zuoge85 on 15/6/17.
 */
public class JavaControllerUtils extends JavaApiUtils {
    public static final Set<String> excludesSet = ImmutableSet.of(
            ResponseBody.class.getName(),
            Account.class.getName(),
            RequestMapping.class.getName(),
            ApiMethod.class.getName()
    );

    public JavaControllerUtils(Config config, ModelInfo modelInfo, ApiInfo apiInfo, String rootPackage, String messagePackage) {
        super(config, modelInfo, apiInfo, rootPackage, messagePackage);
        super.addImport(config.getApiAccount().getImport(apiInfo));
    }

    @Override
    public String getName() {
        return moduleInfo.getName() + "Controller";
    }

    public String annotations(ApiMethodInfo method, String lineStart) {
        StringBuilder sb = new StringBuilder();

        sb.append(lineStart);
        sb.append("@").append(ResponseBody.class.getSimpleName()).append("\n");
        if(!method.isAccount()){
            sb.append(lineStart);
            sb.append("@").append(Account.class.getSimpleName()).append("(false)\n");
        }

        sb.append(lineStart);
        sb.append("@").append(RequestMapping.class.getSimpleName()).append("(value = \"")
                .append(StringEscapeUtils.escapeJava(method.getUrl()))
                .append("\", method = RequestMethod.")
                .append(method.getType().toMethod().toString())
                .append(")\n");


        LinkedHashMap<QualifiedName, Annotation> annotationsMap = method.getAnnotationsMap();

        for (Map.Entry<QualifiedName, Annotation> entry : annotationsMap.entrySet()) {
            QualifiedName name = entry.getKey();
            if (!excludesSet.contains(name.getFullyQualifiedName())) {
                sb.append(lineStart);
                sb.append(entry.getValue().toString());
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    @Override
    public String params(ApiMethodInfo method, boolean isAnnotation) {
        StringBuilder sb = new StringBuilder();
        ArrayList<SingleVariableDeclaration> params = method.getParams();
        sb.append(StringUtils.join(params.toArray(), ", "));
        if (method.getAccountParam() != null) {

            String aSimpleName = config.getApiAccount().getSimpleName(apiInfo);
            if(sb.length() > 0){
                sb.append(" , ");
            }
            if(isAnnotation){
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
