package org.forkjoin.apikit;

import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;

/**
 *
 */
public abstract class AbstractFileGenerator implements Generator {
    protected String outPath;
    protected String rootPackage;
    protected String version;

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
