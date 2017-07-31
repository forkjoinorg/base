package org.forkjoin.apikit.example;

import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.Manager;
import org.forkjoin.apikit.ObjectFactory;
import org.forkjoin.apikit.generator.ApiDocGenerator;
import org.forkjoin.apikit.generator.JavaClientGenerator;
import org.forkjoin.apikit.generator.JavaScriptGenerator;
import org.forkjoin.apikit.impl.JdtAnalyse;
import org.forkjoin.apikit.jgit.GitGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class ApiBuilderMain {
    private static final Logger log = LoggerFactory
            .getLogger(ApiBuilderMain.class);

    /**
     *
     */
    public static void main(String[] args) throws Exception {
        final String version = "v1";
        File root = new File("forkjoin-apikit-example");
        if (!root.exists()) {
            root = new File(".");
        }

        File dir = new File(root, "src/main/java/");
        File apiDocDir = new File(root, "src/main/resources/");

        File javaClientDir = new File(root, "src/test/java/");
        File jsClientDir = new File(root, "src/test/js/");


        // TODO 修改下面的乱七八糟的路径
        log.info("代码路径:{}", dir.getAbsolutePath());


        Manager manager = new Manager();
        manager.setPath(dir.getAbsolutePath());
        manager.setRootPackage("org.forkjoin.apikit.example");
        manager.setObjectFactory(objectFactory);

        //开始处理
        manager.analyse();

        {
            JavaClientGenerator generator = new JavaClientGenerator();
            generator.setOutPath(javaClientDir.getAbsolutePath());
            generator.setVersion(version);
            generator.setRootPackage("org.forkjoin.apikit.example.client");
            manager.generate(generator);
        }

        {
            ApiDocGenerator generator = new ApiDocGenerator();
            generator.setAmd(true);
            generator.setOutPath(apiDocDir.getAbsolutePath());
            generator.setVersion(version);
            manager.generate(generator);
        }


        {
            JavaScriptGenerator generator = new JavaScriptGenerator();
            generator.setOutPath(jsClientDir.getAbsolutePath());
            generator.setVersion(version);
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
