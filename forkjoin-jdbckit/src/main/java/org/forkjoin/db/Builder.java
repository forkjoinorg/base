package org.forkjoin.db;

import org.forkjoin.jdbckit.mysql.Config;
import org.forkjoin.jdbckit.mysql.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;

/**
 * @author zuoge85@gmail.com on 2017/5/23.
 */
public class Builder {
    protected static final Logger log = LoggerFactory.getLogger(Builder.class);
    protected Generator generator;

    public Builder(File rootDir, DataSource ds, String packageName) throws Exception {
        this(rootDir, ds, packageName, null);
    }

    public Builder(File rootDir, DataSource ds, String packageName, String jdbcDataSourceName) throws Exception {
        File dir = new File(rootDir, "src/main/java/");
        File resourcesDir = new File(rootDir, "src/main/resources/");
        log.info("代码路径:{}", dir.getAbsolutePath());

        Config config = new Config(dir, resourcesDir);
        config.setPack(packageName);
        config.setJdbcDataSourceName(jdbcDataSourceName);
        Connection conn = null;
        try {
            conn = ds.getConnection();
            generator = new Generator(config, conn);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void build() throws Exception {
        generator.objectCreate();
        generator.objectMetaCreate();
        //generator.daoCreate();
        generator.daoImplCreate();
        generator.springXmlCreate();
    }

    public void readOnlyBuild() throws Exception {
        generator.objectCreate();
        generator.objectMetaCreate();
        //generator.daoCreate();
        generator.readOnlyDaoImplCreate();
        generator.springXmlCreate();
    }
}
