package org.forkjoin.apikit;

/**
 * @author zuoge85 on 15/6/11.
 */
public class Config {
    private String path;
    private String rootPackage;

    private String javaServerControllerRootPackage;
    private String javaServerRootPackage;
    private String javaServerSrcPath;

    private String javaClientRootPackage;
    private String javaClientSrcPath;

    private String ocClientClassPrefix;
    private String ocClientRootPackage;
    private String ocClientSrcPath;

    private String swiftClientClassPrefix;
    private String swiftClientRootPackage;
    private String swiftClientSrcPath;

    private ApiAccount apiAccount;
    private String version;

    private static String fileSuffix = ".java";

    public static String getFileSuffix() {
        return fileSuffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getJavaClientRootPackage() {
        return javaClientRootPackage;
    }

    public void setJavaClientRootPackage(String javaClientRootPackage) {
        this.javaClientRootPackage = javaClientRootPackage;
    }

    public String getOcClientRootPackage() {
        return ocClientRootPackage;
    }

    public void setOcClientRootPackage(String ocClientRootPackage) {
        this.ocClientRootPackage = ocClientRootPackage;
    }

    public String getJavaClientSrcPath() {
        return javaClientSrcPath;
    }

    public void setJavaClientSrcPath(String javaClientSrcPath) {
        this.javaClientSrcPath = javaClientSrcPath;
    }

    public String getOcClientSrcPath() {
        return ocClientSrcPath;
    }

    public void setOcClientSrcPath(String ocClientSrcPath) {
        this.ocClientSrcPath = ocClientSrcPath;
    }


    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }


    public static void setFileSuffix(String fileSuffix) {
        Config.fileSuffix = fileSuffix;
    }


    public String getJavaServerRootPackage() {
        return javaServerRootPackage;
    }

    public void setJavaServerRootPackage(String javaServerRootPackage) {
        this.javaServerRootPackage = javaServerRootPackage;
    }

    public String getJavaServerSrcPath() {
        return javaServerSrcPath;
    }

    public void setJavaServerSrcPath(String javaServerSrcPath) {
        this.javaServerSrcPath = javaServerSrcPath;
    }

    public String getJavaServerControllerRootPackage() {
        return javaServerControllerRootPackage;
    }

    public void setJavaServerControllerRootPackage(String javaServerControllerRootPackage) {
        this.javaServerControllerRootPackage = javaServerControllerRootPackage;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ApiAccount getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(ApiAccount apiAccount) {
        this.apiAccount = apiAccount;
    }

    public String getOcClientClassPrefix() {
        return ocClientClassPrefix;
    }

    public void setOcClientClassPrefix(String ocClientClassPrefix) {
        this.ocClientClassPrefix = ocClientClassPrefix;
    }


    public String getSwiftClientClassPrefix() {
        return swiftClientClassPrefix;
    }

    public void setSwiftClientClassPrefix(String swiftClientClassPrefix) {
        this.swiftClientClassPrefix = swiftClientClassPrefix;
    }

    public String getSwiftClientRootPackage() {
        return swiftClientRootPackage;
    }

    public void setSwiftClientRootPackage(String swiftClientRootPackage) {
        this.swiftClientRootPackage = swiftClientRootPackage;
    }

    public String getSwiftClientSrcPath() {
        return swiftClientSrcPath;
    }

    public void setSwiftClientSrcPath(String swiftClientSrcPath) {
        this.swiftClientSrcPath = swiftClientSrcPath;
    }


    @Override
    public String toString() {
        return "Config{" +
                "path='" + path + '\'' +
                ", rootPackage='" + rootPackage + '\'' +
                ", javaServerControllerRootPackage='" + javaServerControllerRootPackage + '\'' +
                ", javaServerRootPackage='" + javaServerRootPackage + '\'' +
                ", javaServerSrcPath='" + javaServerSrcPath + '\'' +
                ", javaClientRootPackage='" + javaClientRootPackage + '\'' +
                ", javaClientSrcPath='" + javaClientSrcPath + '\'' +
                ", ocClientClassPrefix='" + ocClientClassPrefix + '\'' +
                ", ocClientRootPackage='" + ocClientRootPackage + '\'' +
                ", ocClientSrcPath='" + ocClientSrcPath + '\'' +
                ", swiftClientClassPrefix='" + swiftClientClassPrefix + '\'' +
                ", swiftClientRootPackage='" + swiftClientRootPackage + '\'' +
                ", swiftClientSrcPath='" + swiftClientSrcPath + '\'' +
                ", apiAccount=" + apiAccount +
                ", version='" + version + '\'' +
                '}';
    }
}
