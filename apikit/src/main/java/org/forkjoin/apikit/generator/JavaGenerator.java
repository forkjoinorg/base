package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.wrapper.BuilderWrapper;
import org.forkjoin.apikit.wrapper.JavaMessageWrapper;

import java.io.File;

/**
 *
 */
public abstract class JavaGenerator extends HttlGenerator {

    protected File getFileName(BuilderWrapper utils) {
        return Utils.packToPath(srcPath, utils.getPack(), utils.getName(), ".java");
    }

    @Override
    public void generateMessage(MessageInfo messageInfo) throws Exception {
        JavaMessageWrapper utils = new JavaMessageWrapper(context, messageInfo, rootPackage);
        File file = getFileName(utils);
        utils.init();
        executeModule(
                utils,
                "/org/forkjoin/apikit/generator/JavaMessage.httl",
                file
        );
    }
}
