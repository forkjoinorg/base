package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.oldmodel.ApiInfo;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.apikit.wrapper.JavaControllerWrapper;
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
public class JavaApiInterfaceBuilder extends JavaControllerBuilder {
    private static final Logger log = LoggerFactory.getLogger(JavaApiInterfaceBuilder.class);


    public JavaApiInterfaceBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage, String messageRootPackage) {
        super(config, modelInfo, srcPath, rootPackage, messageRootPackage);
    }



    @Override
    protected void executeModule(BuilderWrapper utils, ApiInfo moduleInfo, String templPath) throws IOException, ParseException {
        File file = getFileName(utils, moduleInfo);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("m", moduleInfo);
        parameters.put("utils", utils);
        HttlHelper.renderFile(file, parameters, templPath, true);
    }

    @Override
    protected BuilderWrapper getUtils(ApiInfo info) {
        return new JavaControllerWrapper(config, modelInfo, info, rootPackage, messageRootPackage){
            @Override
            public String getName() {
                return moduleInfo.getName();
            }
        };
    }

    @Override
    protected String getTemplPath() {
        return "/org/forkjoin/apikit/templ/JavaApiInterface.httl";
    }

    @Override
    protected File getFileName(BuilderWrapper utils, ApiInfo moduleInfo) {
        return Utils.packToPath(srcPath, utils.getPack() + ".i", moduleInfo.getName(), getSuffix());
    }
}
