package org.forkjoin.apikit.example;

import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.Manager;
import org.forkjoin.apikit.ObjectFactory;
import org.forkjoin.apikit.impl.JdtAnalyse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


public class ApiBuilderMain {
    private static final Logger log = LoggerFactory
            .getLogger(ApiBuilderMain.class);

    /**
     *
     */
    public static void main(String[] args) throws IOException {
        final String version = "v1";
        File dir = new File("apikit-example/src/main/java/");
        if (!dir.exists()) {
            dir = new File("src/main/java/");
        }

        // TODO 修改下面的乱七八糟的路径
        log.info("代码路径:{}", dir.getAbsolutePath());


        Manager manager = new Manager();
        manager.setPath(dir.getAbsolutePath());
        manager.setRootPackage("org.forkjoin.apikit.example");
        manager.setObjectFactory(objectFactory);

        //开始处理
        manager.analyse();

//		Config cfg = new Config();
//		cfg.setPath(dir.getAbsolutePath());
//		cfg.setRootPackage("org.forkjoin.apikit.example");
//
//		ApiBuilder builder = new ApiBuilder(cfg);
//		builder.analyse();
//		builder.check();

    }

    private static ObjectFactory objectFactory = new ObjectFactory() {
        @Override
        public Analyse createAnalyse() {
            return new JdtAnalyse();
        }
    };
}
