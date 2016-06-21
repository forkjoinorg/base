package org.forkjoin.apikit.example;

import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.Manager;
import org.forkjoin.apikit.ObjectFactory;
import org.forkjoin.apikit.generator.JSGenerator;
import org.forkjoin.apikit.generator.ServerGenerator;
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
    public static void main(String[] args) throws Exception {
        final String version = "v1";
        File dir = new File("forkjoin-apikit-example/src/main/java/");
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

        //开始生产
        {
            ServerGenerator generator = new ServerGenerator();
            generator.setRootPackage("com.text");
            generator.setSrcPath("/Users/zuoge85/Documents/open/forkjoin/forkjoin-apikit-example/src/test/java/");
            generator.setApiAccountClassName("java.lang.Object");
            generator.setVersion("v1");
            manager.generate(generator);
        }


        {
            JSGenerator generator = new JSGenerator();
            generator.setSrcPath("/Users/zuoge85/Documents/open/forkjoin/forkjoin-apikit-example/src/test/js/");
            generator.setVersion("v1");
            manager.generate(generator);
        }
    }

    private static ObjectFactory objectFactory = new ObjectFactory() {
        @Override
        public Analyse createAnalyse() {
            return new JdtAnalyse();
        }

        @Override
        public Context createContext() {
            return new Context();
        }
    };
}
