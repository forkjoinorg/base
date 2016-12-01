package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.utils.CommentUtils;
import org.forkjoin.apikit.utils.NameUtils;
import org.forkjoin.apikit.wrapper.JSWrapper;
import org.forkjoin.apikit.wrapper.JavaClientApiWrapper;
import org.forkjoin.apikit.wrapper.JavaMessageWrapper;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JavaClientGenerator extends JavaGenerator {
    private JSWrapper.Type type = JSWrapper.Type.FLOW_TYPE;

    private boolean isAnnotations = false;

    public JavaClientGenerator() {

    }

    protected JavaMessageWrapper createMessageWarpper(MessageInfo messageInfo) {
        JavaMessageWrapper javaMessageWrapper = new JavaMessageWrapper(context, messageInfo, rootPackage);
        javaMessageWrapper.setAnnotations(isAnnotations);
        return javaMessageWrapper;
    }

    @Override
    public void generateApi(ApiInfo apiInfo) throws Exception {
        JavaClientApiWrapper utils = new JavaClientApiWrapper(context, apiInfo, rootPackage);
        utils.setAnnotations(isAnnotations);
        File file = getFileName(utils);
        utils.init();
        executeModule(
                utils,
                "/org/forkjoin/apikit/generator/ApiItem.httl",
                file
        );
    }


    @Override
    public void generateTool() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        Collection<ApiInfo> values = context.getApis().getValues();
        parameters.put("values", values);
        parameters.put("nameUtils", new NameUtils());
        parameters.put("commentUtils", new CommentUtils());
        parameters.put("pack", getRootPackage());
        File file = Utils.packToPath(outPath, getRootPackage(), "ApiManager", ".java");
        execute(
                parameters,
                "/org/forkjoin/apikit/generator/JavaApiManager.httl",
                file
        );
    }


    public boolean isAnnotations() {
        return isAnnotations;
    }

    public void setAnnotations(boolean annotations) {
        isAnnotations = annotations;
    }
}
