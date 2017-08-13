package org.forkjoin.jdbckit.mysql;


import org.forkjoin.jdbckit.core.identifier.*;
import org.forkjoin.jdbckit.mysql.Table;

import java.io.File;
import java.util.function.Predicate;

public class Config {

    private String pack = "test";
    private String objectPack = "entity";
    private String daoPack = "dao";
    private String charset = "utf8";
    private String tablePrefix;
    private String jdbcDataSourceName;
    /**
     * 只生成 test 函数返回true 的表
     */
    private Predicate<Table> tableFilter = new DefaultTablePrefix();


    private File dir;
    private File resourcesDir;

    public File getPackPath(String childPack) {
        return new File(dir, pack.replace('.', File.separatorChar)
                + File.separatorChar + childPack.replace('.', File.separatorChar));
    }

    public File getResourcesPackPath(String childPack) {
        return new File(resourcesDir, pack.replace('.', File.separatorChar)
                + File.separatorChar + childPack.replace('.', File.separatorChar));
    }

    public String getPack(String childPack) {
        return pack + "." + childPack;
    }

    public String getPack(String pack, String childPack) {
        return pack + "." + childPack;
    }

    public Config(File dir, File resourcesDir) throws Exception {
        this.dir = dir;
        this.resourcesDir = resourcesDir;
        // =
//        velocityEngine.init();
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public File getDir() {
        return dir;
    }
//
//    public void setVelocityEngine(VelocityEngine velocityEngine) {
//        this.velocityEngine = velocityEngine;
//    }
//
//    public VelocityEngine getVelocityEngine() {
//        return velocityEngine;
//    }

    public void setObjectPack(String objectPack) {
        this.objectPack = objectPack;
    }

    public String getObjectPack() {
        return objectPack;
    }

    public void setDaoPack(String daoPack) {
        this.daoPack = daoPack;
    }

    public String getDaoPack() {
        return daoPack;
    }

    public File getResourcesDir() {
        return resourcesDir;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getJdbcDataSourceName() {
        return jdbcDataSourceName;
    }

    public void setJdbcDataSourceName(String jdbcDataSourceName) {
        this.jdbcDataSourceName = jdbcDataSourceName;
    }


    public Predicate<Table> getTableFilter() {
        return tableFilter;
    }

    public void setTableFilter(Predicate<Table> tableFilter) {
        this.tableFilter = tableFilter;
    }

    @Override
    public String toString() {
        return "Config{" +
                "pack='" + pack + '\'' +
                ", objectPack='" + objectPack + '\'' +
                ", daoPack='" + daoPack + '\'' +
                ", charset='" + charset + '\'' +
                ", tablePrefix='" + tablePrefix + '\'' +
                ", jdbcDataSourceName='" + jdbcDataSourceName + '\'' +
                ", tableFilter=" + tableFilter +
                ", dir=" + dir +
                ", resourcesDir=" + resourcesDir +
                '}';
    }
}

