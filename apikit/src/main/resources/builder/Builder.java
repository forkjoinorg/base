package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.core.ResultExecute;
import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.oldmodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zuoge85 on 15/6/15.
 */
public abstract class Builder<T extends ModuleInfo> {
    private static final Logger log = LoggerFactory.getLogger(Builder.class);

    protected Config config;
    protected ModelInfo modelInfo;
    protected String srcPath;
    protected String rootPackage;

    public Builder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage) {
        this.config = config;
        this.modelInfo = modelInfo;
        this.srcPath = srcPath;
        this.rootPackage = rootPackage;
    }

    public void builder() {
        forEach(moduleInfo -> {
            try {
                BuilderWrapper utils = getUtils(moduleInfo);
                utils.init();
                executeModule(utils, moduleInfo, getTemplPath());
            } catch (Exception e) {
                log.error("生成message 错误:{}", moduleInfo, e);
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    protected abstract void forEach(ResultExecute<Void, T> resultExecute);

    protected void forEachMessage(ResultExecute<Void, MessageInfo> resultExecute) {
        log.info("开始处理message");
        Collection<PackageInfo<MessageInfo>> messagePackages = modelInfo.getMessagePackages();
        for (PackageInfo<MessageInfo> packageInfo : messagePackages) {
            Set<MessageInfo> messageInfos = packageInfo.getValues();
            for (MessageInfo messageInfo : messageInfos) {
                resultExecute.exe(messageInfo);
            }
        }
    }

    protected void forEachApi(ResultExecute<Void, ApiInfo> resultExecute) {
        log.info("开始处理api");
        Collection<PackageInfo<ApiInfo>> apiInfoPackages = modelInfo.getApiInfoPackages();
        for (PackageInfo<ApiInfo> packageInfo : apiInfoPackages) {
            Set<ApiInfo> apiInfos = packageInfo.getValues();
            for (ApiInfo apiInfo : apiInfos) {
                resultExecute.exe(apiInfo);
            }
        }
    }

    protected void executeModule(BuilderWrapper utils, T moduleInfo, String templPath) throws IOException, ParseException {
        File file = getFileName(utils, moduleInfo);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("m", moduleInfo);
        parameters.put("utils", utils);

        HttlHelper.renderFile(file, parameters, templPath, true);
    }

    protected File getFileName(BuilderWrapper utils, T moduleInfo) {
        return Utils.packToPath(srcPath, utils.getPack(), moduleInfo.getName(), getSuffix());
    }




    protected abstract String getSuffix();

    protected abstract BuilderWrapper getUtils(T info);

    protected abstract String getTemplPath();
}
