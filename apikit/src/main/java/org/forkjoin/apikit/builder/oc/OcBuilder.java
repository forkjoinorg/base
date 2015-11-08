package org.forkjoin.apikit.builder.oc;

import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.builder.Builder;
import org.forkjoin.apikit.builder.BuilderUtils;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.oldmodel.ModuleInfo;
import org.forkjoin.apikit.Utils;

import java.io.File;

/**
 * @author zuoge85 on 15/6/14.
 */
public abstract class OcBuilder<T extends ModuleInfo> extends Builder<T>{

    public OcBuilder(Config config, ModelInfo modelInfo, String srcPath, String rootPackage) {
        super(config, modelInfo, srcPath, rootPackage);
    }

    @Override
    protected String getSuffix() {
        return ".h";
    }

    @Override
    protected File getFileName(BuilderUtils utils, T moduleInfo) {
        return Utils.packToPath(srcPath, utils.getPack(), utils.getName(), getSuffix());
    }
}
