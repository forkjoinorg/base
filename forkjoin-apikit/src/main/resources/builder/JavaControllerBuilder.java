package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.apikit.wrapper.JavaControllerWrapper;
import org.forkjoin.core.ResultExecute;
import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.builder.update.JavaUpdate;
import org.forkjoin.apikit.oldmodel.ApiInfo;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/15.
 */
public class JavaControllerBuilder extends Builder<ApiInfo> {
    private static final Logger log = LoggerFactory.getLogger(JavaControllerBuilder.class);

    protected String messageRootPackage;
    public JavaControllerBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage, String messageRootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
        this.messageRootPackage = messageRootPackage;
    }

    @Override
    protected void forEach(ResultExecute<Void, ApiInfo> resultExecute) {
        super.forEachApi(resultExecute);
    }

    @Override
    protected void executeModule(BuilderWrapper utils, ApiInfo moduleInfo, String templPath) throws IOException, ParseException {
        File file = getFileName(utils, moduleInfo);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("m", moduleInfo);
        parameters.put("utils", utils);

        if(!file.exists()){
            HttlHelper.renderFile(file, parameters, templPath, true);
        } else {
            log.error("文件已经存在，现在开始替换模式！");
            String targetSource = HttlHelper.renderToString(parameters, templPath);
            new JavaUpdate(file, utils, moduleInfo).update(targetSource);
        }
    }

    @Override
    protected String getSuffix() {
        return ".java";
    }

    @Override
    protected BuilderWrapper getUtils(ApiInfo info) {
        return new JavaControllerWrapper(config, modelInfo, info, rootPackage, messageRootPackage);
    }

    @Override
    protected String getTemplPath() {
        return "/org/forkjoin/apikit/templ/JavaController.httl";
    }

    @Override
    protected File getFileName(BuilderWrapper utils, ApiInfo moduleInfo) {
        return Utils.packToPath(srcPath, utils.getPack(), moduleInfo.getName() + "Controller", getSuffix());
    }
}
