package org.forkjoin.apikit.builder.swift;

import org.forkjoin.apikit.Config;
import org.forkjoin.apikit.builder.Builder;
import org.forkjoin.apikit.builder.BuilderUtils;
import org.forkjoin.apikit.builder.HttlHelper;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.oldmodel.ModuleInfo;
import org.forkjoin.apikit.oldmodel.Utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/14.
 */
public abstract class SwiftBuilder<T extends ModuleInfo> extends Builder<T> {

    public SwiftBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
    }

    @Override
    protected String getSuffix() {
        return ".swift";
    }

    @Override
    protected File getFileName(BuilderUtils utils, T moduleInfo) {
        return Utils.packToPath(srcPath, utils.getPack(), utils.getName(), getSuffix());
    }

    @Override
    protected void executeModule(BuilderUtils utils, T moduleInfo, String templPath) throws IOException, ParseException {
        File file = getFileName(utils, moduleInfo);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("m", moduleInfo);
        parameters.put("utils", utils);

        HttlHelper.renderFile(file, parameters, templPath, false);
    }
}
