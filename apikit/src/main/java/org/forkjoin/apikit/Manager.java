package org.forkjoin.apikit;

import org.apache.commons.io.IOUtils;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.MessageInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoge85 on 15/11/8.
 */
public class Manager {
    private static final Logger log = LoggerFactory.getLogger(Manager.class);

    private String path;
    private String rootPackage;
    private String fileCharset = "utf8";
    private String fileSuffix = ".java";

    //    private Analyse analyse;
    private ObjectFactory objectFactory;
    private List<Builder> list = new ArrayList<>();
    private File rootDir;
    private String rootDirPath;

    public void analyse() {
        rootDir = Utils.packToPath(path, rootPackage);
        rootDirPath = rootDir.getAbsolutePath();

        analyse(rootDir);
    }

    private void analyse(File dir) {
        for (File f : dir.listFiles(
                pathname -> pathname.isDirectory() ||
                        pathname.getName().endsWith(fileSuffix)
        )) {
            if (f.isDirectory()) {
                analyse(f);
            } else {
                try (InputStream in = new FileInputStream(f)) {
                    String code = IOUtils.toString(in, fileCharset);
                    String pack = analysePack(f);
                    Analyse analyse = objectFactory.createAnalyse();
                    ModuleInfo m = analyse.analyse(code, pack);

                    if (m instanceof MessageInfo) {
                        list.parallelStream().forEach(builder -> builder.build((MessageInfo) m));
                    } else if (m instanceof ApiInfo) {
                        list.parallelStream().forEach(builder -> builder.build((ApiInfo) m));
                    }
                    log.info("分析完毕一个Message:{}", m);
                } catch (IOException e) {
                    throw new RuntimeException("分析文件错误！", e);
                }
            }
        }
    }

    private String analysePack(File f) {
        String path = f.getParent();

        return path.length() > rootDirPath.length() ?
                Utils.pathToPack(path.substring(rootDirPath.length() + 1)) : "";
    }

    public void setFileCharset(String fileCharset) {
        this.fileCharset = fileCharset;
    }

    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    public void addBuilder(Builder builder) {
        list.add(builder);
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
