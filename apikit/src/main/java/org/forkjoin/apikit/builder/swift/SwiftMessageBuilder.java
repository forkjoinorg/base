package org.forkjoin.apikit.builder.swift;

import org.forkjoin.core.ResultExecute;
import org.forkjoin.apikit.Config;
import org.forkjoin.apikit.builder.BuilderUtils;
import org.forkjoin.apikit.oldmodel.MessageInfo;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zuoge85 on 15/6/14.
 */
public class SwiftMessageBuilder extends SwiftBuilder<MessageInfo> {
    private static final Logger log = LoggerFactory.getLogger(SwiftMessageBuilder.class);

    public SwiftMessageBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
    }

    @Override
    protected void forEach(ResultExecute<Void, MessageInfo> resultExecute) {
        log.info("开始生成message");
        super.forEachMessage(resultExecute);
    }

    @Override
    protected BuilderUtils getUtils(MessageInfo info) {
        return new SwiftMessageUtils(config, modelInfo, info, rootPackage);
    }

//    @Override
//    protected void executeModule(BuilderUtils utils, MessageInfo moduleInfo, String templPath) throws IOException, ParseException {
//        File file = getFileName(utils, moduleInfo);
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("m", moduleInfo);
//        parameters.put("utils", utils);
//
//        HttlHelper.renderFile(file, parameters, templPath, true);
//
//        File implFile = getImplementationFileName(utils, moduleInfo);
//
//        HttlHelper.renderFile(implFile, parameters, getImplementationTemplPath(), true);
//    }

    @Override
    protected String getTemplPath() {
        return "/org/forkjoin/apikit/templ/swift/SwiftMessage.httl";
    }

//    protected String getImplementationTemplPath() {
//        return "/cn/ihealthbaby/tool/api/templ/oc/OcMessageM.httl";
//    }

//    protected File getImplementationFileName(BuilderUtils utils, MessageInfo moduleInfo) {
//        return Utils.packToPath(srcPath, utils.getPack(), utils.getName(), ".m");
//    }
}
