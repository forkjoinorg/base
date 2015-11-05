package org.forkjoin.core.dao;

import com.jolbox.bonecp.BoneCPDataSource;
import org.slf4j.LoggerFactory;

/**
 * @author zuoge85 on 14-4-3.
 */
public class DataSourceFactory {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataSourceFactory.class);

    private String jdbcUrl;
    private String username;
    private String password;

    private int maxNums;
    private int minNums;
    private int partitionNums;
    private boolean readOnly = false;

    public BoneCPDataSource createBoneCPDataSource() {
        BoneCPDataSource ds = new BoneCPDataSource();
        ds.setDefaultAutoCommit(true);
        ds.setDriverClass("com.mysql.jdbc.Driver");

        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setMaxConnectionsPerPartition(maxNums);
        ds.setMinConnectionsPerPartition(minNums);
        ds.setPartitionCount(partitionNums);
        ds.setStatementsCacheSize(1000);

        ds.setInitSQL("SET NAMES utf8mb4;");
        ds.setDefaultReadOnly(readOnly);
        return ds;
    }

//    jdbc.maxNums=20
//    jdbc.minNums=5
//    jdbc.partitionNums=5


    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxNums() {
        return maxNums;
    }

    public void setMaxNums(int maxNums) {
        this.maxNums = maxNums;
    }

    public int getMinNums() {
        return minNums;
    }

    public void setMinNums(int minNums) {
        this.minNums = minNums;
    }

    public int getPartitionNums() {
        return partitionNums;
    }

    public void setPartitionNums(int partitionNums) {
        this.partitionNums = partitionNums;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
