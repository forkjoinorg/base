package org.forkjoin.apikit.generator;

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
    private JSWrapper.Type type = JSWrapper.Type.FLOW_TYPE;

    public JavaScriptGenerator() {

    }

    protected File getFileName(BuilderWrapper utils) {
        return Utils.packToPath(outPath, utils.getPack(), utils.getName(), ".js");
    }

    @Override
    public void generateApi(ApiInfo apiInfo) throws Exception {
        JSApiWrapper utils = new JSApiWrapper(context, apiInfo, rootPackage);
        File file = getFileName(utils);
        utils.setType(type);
        utils.init();
        executeModule(
                utils,
                "/org/forkjoin/apikit/generator/js/ApiItem.httl",
                file
        );
    }

    @Override
    public void generateMessage(MessageInfo messageInfo) throws Exception {
        JSMessageWrapper utils = new JSMessageWrapper(context, messageInfo, rootPackage);
        File file = getFileName(utils);
        utils.setType(type);
        utils.init();
        executeModule(
                utils,
                "/org/forkjoin/apikit/generator/js/Message.httl",
                file
        );
    }

    @Override
    public void generateTool() throws Exception {
        {
            Map<String, Object> parameters = new HashMap<>();

            Set<Map.Entry<String, Collection<MessageInfo>>> all = context.getMessages().getAll().entrySet();
            parameters.put("all", all);
            parameters.put("values",  context.getMessages().getValues());
            File file = Utils.packToPath(outPath, "", "index", ".js");

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
