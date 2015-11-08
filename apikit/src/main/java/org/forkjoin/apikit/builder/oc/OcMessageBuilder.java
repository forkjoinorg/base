package org.forkjoin.apikit.builder.oc;

import org.forkjoin.core.ResultExecute;
import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.builder.BuilderUtils;
import org.forkjoin.apikit.builder.HttlHelper;
import org.forkjoin.apikit.oldmodel.MessageInfo;
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
 * @author zuoge85 on 15/6/14.
 */
public class OcMessageBuilder extends OcBuilder<MessageInfo> {
    private static final Logger log = LoggerFactory.getLogger(OcMessageBuilder.class);

    public OcMessageBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
    }

    @Override
    protected void forEach(ResultExecute<Void, MessageInfo> resultExecute) {
        log.info("开始生成message");
        super.forEachMessage(resultExecute);
    }

    @Override
    protected BuilderUtils getUtils(MessageInfo info) {
        return new OcMessageUtils(config, modelInfo, info, rootPackage);
    }

    @Override
    protected void executeModule(BuilderUtils utils, MessageInfo moduleInfo, String templPath) throws IOException, ParseException {
        File file = getFileName(utils, moduleInfo);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("m", moduleInfo);
        parameters.put("utils", utils);

        HttlHelper.renderFile(file, parameters, templPath, true);

        File implFile = getImplementationFileName(utils, moduleInfo);

        HttlHelper.renderFile(implFile, parameters, getImplementationTemplPath(), true);
    }

    @Override
    protected String getTemplPath() {
        return "/org/forkjoin/apikit/templ/oc/OcMessageH.httl";
    }

    protected String getImplementationTemplPath() {
        return "/org/forkjoin/apikit/templ/oc/OcMessageM.httl";
    }

    protected File getImplementationFileName(BuilderUtils utils, MessageInfo moduleInfo) {
        return Utils.packToPath(srcPath, utils.getPack(), utils.getName(), ".m");
    }
}
