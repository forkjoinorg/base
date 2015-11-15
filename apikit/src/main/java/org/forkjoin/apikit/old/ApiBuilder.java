package org.forkjoin.apikit.old;

import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.builder.oc.OcMessageBuilder;
import org.forkjoin.apikit.builder.swift.SwiftApiImpiBuilder;
import org.forkjoin.apikit.builder.swift.SwiftMessageBuilder;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.forkjoin.apikit.builder.*;
import org.forkjoin.apikit.oldmodel.*;
import org.forkjoin.apikit.oldmodel.ApiInfo;
import org.forkjoin.apikit.oldmodel.ApiMethodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zuoge85 on 15/6/11.
 */
public class ApiBuilder {
    private static final Logger log = LoggerFactory.getLogger(ApiBuilder.class);
    public static final String FILE_CHARSET = "utf8";

    private Config config;
    //    private FileAnalyse fileAnalyse;
    private ModelInfo modelInfo;


    public ApiBuilder(Config config) {
        this.config = config;
//        fileAnalyse = new FileAnalyse(config);
    }

    public void analyse() {
        File dir = Utils.packToPath(config.getPath(), config.getRootPackage());
        modelInfo = new ModelInfo();
        analyse(dir);
    }

    private void analyse(File dir) {
        for (File f : dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().endsWith(Config.getFileSuffix());
            }
        })) {
            if (f.isDirectory()) {
                analyse(f);
            } else {
                try (InputStream in = new FileInputStream(f)) {
                    String codeString = IOUtils.toString(in, FILE_CHARSET);
//                    org.forkjoin.apikit.info.ModuleInfo m = new FileAnalyse(config, codeString, f.getAbsolutePath()).analyse();
//
//                    if (m instanceof MessageInfo) {
//                        modelInfo.add((MessageInfo) m);
//                    } else if (m instanceof ApiInfo) {
//                        modelInfo.add((ApiInfo) m);
//                    }
//                    log.info("分析完毕一个Message:{}", m);
                } catch (IOException e) {
                    throw new RuntimeException("分析文件错误！", e);
                }
            }
        }
    }

    public void buildServer() {
        String srcPath = config.getJavaServerSrcPath();
        String messageRootPackage = config.getJavaServerRootPackage();
        new JavaMessageBuilder(
                config, modelInfo,
                srcPath, messageRootPackage
        ).builder();

        try {
            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, messageRootPackage, "ApiMessage", ".java"),
                    ImmutableMap.<String, Object>of("packName", messageRootPackage),
                    "/org/forkjoin/apikit/templ/JavaApiMessage.httl", true
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        new JavaControllerBuilder(
                config, modelInfo,
                srcPath, config.getJavaServerControllerRootPackage(),
                messageRootPackage
        ).builder();

        new JavaApiInterfaceBuilder(
                config, modelInfo,
                srcPath, config.getJavaServerControllerRootPackage(),
                messageRootPackage
        ).builder();
    }

    public void buildOc() {
        String srcPath = config.getOcClientSrcPath();
        String messageRootPackage = config.getOcClientRootPackage();

        new OcMessageBuilder(
                config, modelInfo,
                srcPath, messageRootPackage
        ).builder();
    }

    public void buildSwift() {
        String srcPath = config.getSwiftClientSrcPath();
        String rootPackage = config.getSwiftClientRootPackage();

        new SwiftMessageBuilder(
                config, modelInfo,
                srcPath, rootPackage
        ).builder();

        new SwiftApiImpiBuilder(
                config, modelInfo,
                srcPath, rootPackage, rootPackage
        ).builder();


        copySwiftClientClass(srcPath, rootPackage, ".swift");

        createSwiftApiManager(srcPath, rootPackage, ".swift");
    }


    public void buildAndroid() {
        String srcPath = config.getJavaClientSrcPath();
        String rootPackage = config.getJavaClientRootPackage();

        buildJavaMessage(srcPath, rootPackage);

        new JavaApiBuilder(
                config, modelInfo,
                srcPath, rootPackage, rootPackage
        ) {
            @Override
            protected String getTemplPath() {
                return "/org/forkjoin/apikit/templ/android/JavaApiImpi.httl";
            }
        }.builder();

        copyAndroidClientClass(srcPath, rootPackage, ".java");
        createJavaApiManager(srcPath, rootPackage, ".java");
    }

    public void buildJavaClient() {
        String srcPath = config.getJavaClientSrcPath();
        String rootPackage = config.getJavaClientRootPackage();

        buildJavaMessage(srcPath, rootPackage);

        new JavaApiBuilder(
                config, modelInfo,
                srcPath, rootPackage, rootPackage
        ).builder();

        copyJavaClientClass(srcPath, rootPackage, ".java");
        createJavaApiManager(srcPath, rootPackage, ".java");
    }

    private void buildJavaMessage(final String srcPath, final String rootPackage) {
        new JavaMessageBuilder(
                config, modelInfo,
                srcPath, rootPackage
        ) {
            @Override
            protected BuilderUtils getUtils(MessageInfo info) {
                JavaAndroidMessageUtils utils = new JavaAndroidMessageUtils(config, modelInfo, info, rootPackage);
                utils.addExclude("com.isnowfox.api");
                utils.addExclude("org.springframework");
                utils.addExclude("javax.validation");
                utils.addExclude("org.hibernate.validator");
                utils.addExclude("com.isnowfox.spring.annotation");
                return utils;
            }
        }.builder();
    }

    private void copySwiftClientClass(String srcPath, String rootPackage, String suffix) {
        try {
            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "ApiObject", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/swift/ApiObject.httl", false
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "Result", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/swift/Result.httl", false
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "Api", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/swift/Api.httl", false
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "ApiHttpClientAdapter", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/swift/ApiHttpClientAdapter.httl", false
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void copyAndroidClientClass(String srcPath, String rootPackage, String suffix) {
        try {
            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "Api", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/JavaApi.httl", true
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "Result", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/JavaResult.httl", true
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "ApiMessage", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/android/JavaApiMessage.httl", true
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "HttpClientAdapter", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/android/HttpClientAdapter.httl", true
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void copyJavaClientClass(String srcPath, String rootPackage, String suffix) {
        try {
            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "Api", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/JavaApi.httl", true
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "Result", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/JavaResult.httl", true
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "ApiMessage", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/JavaApiMessage.httl", true
            );

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "HttpClientAdapter", suffix),
                    ImmutableMap.<String, Object>of("packName", rootPackage),
                    "/org/forkjoin/apikit/templ/HttpClientAdapter.httl", true
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createSwiftApiManager(String srcPath, String rootPackage, String suffix) {
        try {
            Collection<PackageInfo<ApiInfo>> packageInfos = modelInfo.getApiInfoPackages();

            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "ApiManager", suffix),
                    ImmutableMap.of(
                            "packageInfos", packageInfos,
                            "packName", rootPackage,
                            "prefix", config.getSwiftClientClassPrefix()
                    ),
                    "/org/forkjoin/apikit/templ/swift/ApiManager.httl", false
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createJavaApiManager(String srcPath, String rootPackage, String suffix) {
        try {
            Collection<PackageInfo<ApiInfo>> packageInfos = modelInfo.getApiInfoPackages();
//            PackageInfo<ApiInfo> defaultPackage = modelInfo.getDefaultApiPackage();
            HttlHelper.renderFile(
                    Utils.packToPath(srcPath, rootPackage, "ApiManager", suffix),
                    ImmutableMap.of(
                            "packageInfos", packageInfos,
                            "packName", rootPackage
                    ),
                    "/org/forkjoin/apikit/templ/JavaApiManagerApi.httl", true
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void check() {
        PackageInfo<ApiInfo> defaultApiPackage = modelInfo.getDefaultApiPackage();

        if (modelInfo.getDefaultApiPackage() != null) {
            for (ApiInfo apiInfo : defaultApiPackage.getValues()) {
                check(apiInfo);
            }
        }
        Collection<PackageInfo<ApiInfo>> apiInfoPackages = modelInfo.getApiInfoPackages();
        for (PackageInfo<ApiInfo> packageInfo : apiInfoPackages) {
            for (ApiInfo apiInfo : packageInfo.getValues()) {
                check(apiInfo);
            }
        }
    }

    private void check(ApiInfo apiInfo) {
        Set<String> set = new HashSet<>();
        for (ApiMethodInfo info : apiInfo.getMethodInfos()) {
            if (!set.add(info.getUrl() + "$" + info.getType())) {
                throw new RuntimeException("冲突api 函数，定义冲突" + info);
            }
        }
    }
}
