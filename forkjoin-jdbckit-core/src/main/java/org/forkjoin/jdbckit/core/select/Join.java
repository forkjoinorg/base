package org.forkjoin.jdbckit.core.select;

import org.forkjoin.jdbckit.core.QueryParams;
import org.forkjoin.jdbckit.core.identifier.Table;

/**
 * @author zuoge85@gmail.com on 2017/7/16.
 */
public class Join {
    public enum Type{
        DEFAULT,
        INNER,
        LEFT,
        RIGHT,
    }

    private Type type;
    private Table table;
    private String tableAliasName;

    private QueryParams queryParams;

    public Join(Type type, Table table, String tableAliasName, QueryParams queryParams) {
        this.type = type;
        this.table = table;
        this.tableAliasName = tableAliasName;
        this.queryParams = queryParams;
    }

    public Join(Table table, String tableAliasName, QueryParams queryParams) {
        this.type = Type.DEFAULT;
        this.table = table;
        this.tableAliasName = tableAliasName;
        this.queryParams = queryParams;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getTableAliasName() {
        return tableAliasName;
    }

    public void setTableAliasName(String tableAliasName) {
        this.tableAliasName = tableAliasName;
    }

    public QueryParams getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
    }

    @Override
    public String toString() {
        return "Join{" +
                "type=" + type +
                ", table=" + table +
                ", tableAliasName='" + tableAliasName + '\'' +
                ", queryParams=" + queryParams +
                '}';
    }


}
