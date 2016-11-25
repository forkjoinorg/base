package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.AbstractGenerator;
import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.utils.CommentUtils;
import org.forkjoin.apikit.utils.HttlUtils;
import org.forkjoin.apikit.utils.NameUtils;
import org.forkjoin.apikit.wrapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JavaClientGenerator extends JavaGenerator {
    private JSWrapper.Type type = JSWrapper.Type.FLOW_TYPE;

    public JavaClientGenerator() {

    }

    @Override
    public void generateApi(ApiInfo apiInfo) throws Exception {
        JavaClientWrapper utils = new JavaClientWrapper(context, apiInfo, rootPackage);
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

}
