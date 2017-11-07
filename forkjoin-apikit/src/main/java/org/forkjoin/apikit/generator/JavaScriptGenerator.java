package org.forkjoin.apikit.generator;

import org.apache.commons.io.FileUtils;
import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.utils.NameUtils;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.apikit.wrapper.JSApiWrapper;
import org.forkjoin.apikit.wrapper.JSMessageWrapper;
import org.forkjoin.apikit.wrapper.JSWrapper;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 这个版本还不能 ,解开泛型或者支持泛型
 */
public class JavaScriptGenerator extends HttlGenerator {
    private JSWrapper.Type type = JSWrapper.Type.JS;

    public JavaScriptGenerator() {

    }

    protected File getFileName(BuilderWrapper utils) {
        return Utils.packToPath(outPath, utils.getPack(), utils.getName(), ".js");
    }

    protected File getTsDFileName(BuilderWrapper utils) {
        return Utils.packToPath(outPath, utils.getPack(), utils.getName(), ".d.ts");
    }

    @Override
    public void generateApi(ApiInfo apiInfo) throws Exception {
        JSApiWrapper utils = new JSApiWrapper(context, apiInfo, rootPackage);
        File dFile = getTsDFileName(utils);
        File file = getFileName(utils);
        utils.setType(type);
        utils.init();

        executeModule(
                utils,
                "/org/forkjoin/apikit/generator/js/ApiItem.httl",
                file
        );

        executeModule(
                utils,
                "/org/forkjoin/apikit/generator/js/ApiItem.d.httl",
                dFile
        );
    }

    @Override
    public void generateMessage(MessageInfo messageInfo) throws Exception {
        JSMessageWrapper utils = new JSMessageWrapper(context, messageInfo, rootPackage);
        File dFile = getTsDFileName(utils);
        File file = getFileName(utils);
        utils.setType(type);
        utils.init();
        executeModule(
                utils,
                "/org/forkjoin/apikit/generator/js/Message.d.httl",
                dFile
        );

        FileUtils.write(file, "", "utf8");
    }

    public void copyTool(String name) throws Exception {
        File file = new File(outPath, name);
        FileUtils.copyInputStreamToFile(
                JavaScriptGenerator.class.getResourceAsStream(
                        "/org/forkjoin/apikit/generator/js/tool/" + name
                ),
                file
        );
    }

    @Override
    public void generateTool() throws Exception {
        //copy 工具文件
        copyTool("AbstractApi.d.ts");
        copyTool("AbstractApi.js");

        copyTool("HttpGroup.d.ts");
        copyTool("HttpGroup.js");

        copyTool("HttpGroupImpi.d.ts");
        copyTool("HttpGroupImpi.js");

        copyTool("HttpRequest.d.ts");
        copyTool("HttpRequest.js");

        copyTool("HttpUtils.d.ts");
        copyTool("HttpUtils.js");

        {
            Map<String, Object> parameters = new HashMap<>();

            Set<Map.Entry<String, Collection<MessageInfo>>> all = context.getMessages().getAll().entrySet();
            parameters.put("all", all);
            parameters.put("values", context.getMessages().getValues());
            parameters.put("apis", context.getApis().getValues());
            File dFile = Utils.packToPath(outPath, "", "index", ".d.ts");
            File file = Utils.packToPath(outPath, "", "index", ".js");

            execute(
                    parameters,
                    "/org/forkjoin/apikit/generator/js/index.d.httl",
                    dFile
            );

            execute(
                    parameters,
                    "/org/forkjoin/apikit/generator/js/index.httl",
                    file
            );
        }

        {
            Map<String, Object> parameters = new HashMap<>();
            Collection<ApiInfo> values = context.getApis().getValues();
            parameters.put("values", values);
            parameters.put("nameUtils", new NameUtils());
            File dFile = Utils.packToPath(outPath, "", "Apis", ".d.ts");
            execute(
                    parameters,
                    "/org/forkjoin/apikit/generator/js/Apis.d.httl",
                    dFile
            );
            File file = Utils.packToPath(outPath, "", "Apis", ".js");
            execute(
                    parameters,
                    "/org/forkjoin/apikit/generator/js/Apis.httl",
                    file
            );
        }
    }

    public JSWrapper.Type getType() {
        return type;
    }

    public void setType(JSWrapper.Type type) {
        this.type = type;
    }
}
