package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.apikit.wrapper.JavaApiWrapper;
import org.forkjoin.core.ResultExecute;
import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.oldmodel.ApiInfo;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author zuoge85 on 15/6/15.
 */
public class JavaApiBuilder extends Builder<ApiInfo> {
    private static final Logger log = LoggerFactory.getLogger(JavaApiBuilder.class);

    private String messageRootPackage;

    public JavaApiBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage, String messageRootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
        this.messageRootPackage = messageRootPackage;
    }

    @Override
    protected void forEach(ResultExecute<Void, ApiInfo> resultExecute) {
        super.forEachApi(resultExecute);
    }

    @Override
    protected String getSuffix() {
        return ".java";
    }

    @Override
    protected BuilderWrapper getUtils(ApiInfo info) {
        JavaApiWrapper javaApiUtils = new JavaApiWrapper(config, modelInfo, info, rootPackage, messageRootPackage);
        javaApiUtils.addExclude("com.isnowfox.api");
        javaApiUtils.addExclude("org.springframework");
        javaApiUtils.addExclude("javax.validation");
        javaApiUtils.addExclude("com.isnowfox.spring.annotation");
        return javaApiUtils;
    }

    @Override
    protected String getTemplPath() {
        return "/org/forkjoin/apikit/templ/JavaApiImpi.httl";
    }

    @Override
    protected File getFileName(BuilderWrapper utils, ApiInfo moduleInfo) {
//        PackageInfo pack = moduleInfo.getPack();
        return Utils.packToPath(srcPath, utils.getPack(), moduleInfo.getName(), getSuffix());
    }
}
