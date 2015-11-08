package org.forkjoin.apikit.builder.swift;

import org.forkjoin.core.ResultExecute;
import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.builder.BuilderUtils;
import org.forkjoin.apikit.oldmodel.ApiInfo;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author zuoge85 on 15/6/15.
 */
public class SwiftApiImpiBuilder extends SwiftBuilder<ApiInfo> {
    private static final Logger log = LoggerFactory.getLogger(SwiftApiImpiBuilder.class);

    private String messageRootPackage;

    public SwiftApiImpiBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage, String messageRootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
        this.messageRootPackage = messageRootPackage;
    }

    @Override
    protected void forEach(ResultExecute<Void, ApiInfo> resultExecute) {
        super.forEachApi(resultExecute);
    }


    @Override
    protected BuilderUtils getUtils(ApiInfo info) {
        SwiftApiUtils javaApiUtils = new SwiftApiUtils(config, modelInfo, info, rootPackage, messageRootPackage);
        return javaApiUtils;
    }

    @Override
    protected String getTemplPath() {
        return "/org/forkjoin/apikit/templ/swift/ApiImpi.httl";
    }

    @Override
    protected File getFileName(BuilderUtils utils, ApiInfo moduleInfo) {
//        PackageInfo pack = moduleInfo.getPack();
        return Utils.packToPath(srcPath, utils.getPack(), moduleInfo.getName(), getSuffix());
    }
}
