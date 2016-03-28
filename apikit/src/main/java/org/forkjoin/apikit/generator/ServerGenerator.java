package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.generator.jdt.JavaCodeUpdate;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.utils.HttlUtils;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.apikit.wrapper.JavaControllerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 *
 */
public class ServerGenerator extends JavaGenerator {
    private static final Logger log = LoggerFactory.getLogger(ServerGenerator.class);
    private String apiAccountClassName;
    private String version;


    public void setApiAccountClassName(String apiAccountClassName) {
        this.apiAccountClassName = apiAccountClassName;
    }

    protected File getInterfaceFileName(BuilderWrapper utils) {
        return Utils.packToPath(srcPath, utils.getPack() + ".i", utils.getName() + "Interface", ".java");
    }

    @Override
    protected File getFileName(BuilderWrapper utils) {
        return Utils.packToPath(srcPath, utils.getPack(), utils.getName() + "Controller", ".java");
    }

    @Override
    public void generateApi(ApiInfo apiInfo) throws Exception {
        JavaControllerWrapper utils = new JavaControllerWrapper(context, apiInfo, rootPackage);
        utils.setApiAccountClassName(apiAccountClassName);
        utils.setVersion(version);
        utils.init();
//        executeModule(
//                utils,
//                "/org/forkjoin/apikit/generator/JavaControllerInterface.httl",
//                getInterfaceFileName(utils)
//        );


        File file = getFileName(utils);
        if (!file.exists()) {
            executeModule(
                utils,
                "/org/forkjoin/apikit/generator/JavaController.httl",
                file
            );
        } else {
            String code = executeModuleToString(
                utils,
                "/org/forkjoin/apikit/generator/JavaController.httl"
            );
            log.error("文件已经存在，现在开始替换模式！fullName:{},file:{}", utils.getFullName(), file);
            new JavaCodeUpdate(file, utils, apiInfo).update(code, utils.getName()+"Controller");
        }
    }

    @Override
    public void generateMessage(MessageInfo messageInfo) throws Exception {

    }

    @Override
    public void generateTool() throws Exception {

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
