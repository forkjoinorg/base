package org.forkjoin.apikit;

import org.apache.commons.io.IOUtils;
import org.forkjoin.apikit.info.ModuleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
//    private List<Generator> messageGenerators = new ArrayList<>();
    private File rootDir;
    private String rootDirPath;
    private Context context;

    public void analyse() {
        rootDir = Utils.packToPath(path, rootPackage);
        rootDirPath = rootDir.getAbsolutePath();
        context = objectFactory.createContext();

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

                    context.add(m);
                    if(m!=null){
                        log.info("分析完毕一个,name:{},Message:{}", m.getFullName(), m);
                    }else{
                        log.info("分析完毕一个,不存在module:{}", f.getName());
                    }
                } catch (IOException e) {
                    throw new RuntimeException("分析文件错误！", e);
                }
            }
        }
    }

    public void generate(Generator generator) throws Exception {
        generator.generate(context);
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


    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
