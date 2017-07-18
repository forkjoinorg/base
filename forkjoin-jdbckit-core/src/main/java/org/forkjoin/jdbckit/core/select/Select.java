package org.forkjoin.jdbckit.core.select;

/**
 * @author zuoge85 on 15/11/17.
 */

//SELECT
//        SUM(a.service_out_count) ssum, SUM(a.advice_count) asum,SUM(a.rent_fee) rsum,SUM(a.foregift_fee) fsum,SUM(a.couplant_fee) csum
//        FROM
//        statistics a
//
//        where
//        a.create_time > '2015-11-11 11:17:22'
//        and
//        a.create_time < '2015-12-11 11:17:22'
//        and
//        a.hospital_id in(1,2,3)
//        GROUP BY
//        a.hospital_id
//
//        ORDER BY
//        asum DESC


import org.forkjoin.jdbckit.core.Order;
import org.forkjoin.jdbckit.core.QueryParam;
import org.forkjoin.jdbckit.core.QueryParams;
import org.forkjoin.jdbckit.core.SqlUtils;
import org.forkjoin.jdbckit.core.identifier.Field;
import org.forkjoin.jdbckit.core.identifier.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理相对复杂的查询
 * 依然是简单查询封装
 */
public class Select {
    private final Field[] fields;
    private Table table;
    private String tableAliasName;
    private QueryParams queryParams;
    private Order order;
    private Field[] groupFields;

    private List<Join> joins;

    public Select() {
        this(Field.ALL_FIELDS);
    }

    public Select(Field... fields) {
        this.fields = fields;
    }

    public Select(String... fields) {
        this.fields = Field.of(fields);
    }

    public Select from(Table table) {
        this.table = table;
        return this;
    }

    public Select from(Table table, String tableAliasName) {
        this.table = table;
        this.tableAliasName = tableAliasName;
        return this;
    }

//    public Select join(Table table, QueryParams qs) {
//       return join(table, null , qs);
//    }
//
//    public Select join(Table table, QueryParams qs) {
//        return join(table, null , qs);
//    }

    public Select join(Table table, String tableAliasName, QueryParams qs) {
       addJoin(new Join(table, tableAliasName, qs));
       return this;
    }

//    public Select leftJoin(Table table, String tableAliasName, QueryParams qs) {
//
//    }

    private void addJoin(Join join) {
        if (joins == null) {
            joins = new ArrayList<>();
        }
        joins.add(join);
    }

    public Select where(QueryParams params) {
        this.queryParams = params;
        return this;
    }

    public Select where(Field key, Object value) {
        return where(QueryParams.single(
                key, value, QueryParam.OperatorType.EQ, false
        ));
    }

    public Select where(Field key, Object value, QueryParam.OperatorType opt) {
        return where(QueryParams.single(
                key, value, opt, false
        ));
    }

    public Select where(final Field key, final Object value, final QueryParam.OperatorType opt, final boolean not) {
        return where(QueryParams.single(
                key, value, opt, not
        ));
    }

    public Select orderBy(Order order) {
        this.order = order;
        return this;
    }

    public Select orderBy(Field name) {
        this.order = Order.createSingleton(name, true);
        return this;
    }

    public Select orderBy(Field name, boolean isDesc) {
        this.order = Order.createSingleton(name, isDesc);
        return this;
    }

    public Select orderByDesc(Field name) {
        this.order = Order.desc(name);
        return this;
    }

    public Select orderByAsc(Field name) {
        this.order = Order.asc(name);
        return this;
    }


    public Select groupBy(Field... fields) {
        this.groupFields = fields;
        return this;
    }

    public Select groupByNames(String... fields) {
        this.groupFields = Field.of(fields);
        return this;
    }

    public Select groupBy(String name) {
        this.groupFields = new Field[]{Field.of(name)};
        return this;
    }

    public Select groupBy(String table, String name) {
        return groupBy(Field.of(table, name));
    }

    public Select groupByAliasName(String name, String filedAliasName) {
        return groupBy(Field.ofAliasName(name, filedAliasName));
    }

    public Select groupBy(String table, String name, String filedAliasName) {
        return groupBy(Field.of(table, name, filedAliasName));
    }

    //    SELECT
//
//            FROM
//
//    WHERE
//
//    GROUP BY
//
//    ORDER BY `id`
    public String toSql() {
        return toSql(new StringBuilder()).toString();
    }

    public StringBuilder toSql(StringBuilder sb) {
        return toSql(sb, false);
    }

    public String toCountSql() {
        return toCountSql(new StringBuilder()).toString();
    }

    public StringBuilder toCountSql(StringBuilder sb) {
        return toSql(sb, true);
    }

    public StringBuilder toSql(StringBuilder sb, boolean isCount) {
        boolean isGroup = groupFields != null && groupFields.length > 0;

        sb.append("SELECT ");
        if (isCount) {
            if (isGroup) {
                sb.append("count(distinct ");
                for (int i = 0; i < groupFields.length; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(groupFields[i].getValue());
                }
                sb.append(")");
            } else {
                sb.append(SqlUtils.STRING_COUNT);
            }
        } else {
            for (int i = 0; i < fields.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(fields[i].getValue());
            }
        }
        sb.append(" FROM ");

        sb.append(table).append(" ");

        if (tableAliasName != null) {
            sb.append("`")
                    .append(SqlUtils.nameFilter(tableAliasName))
                    .append("` ");
        }
        if (queryParams != null) {
            queryParams.toSql(sb);
        }


        if (isCount) {
            return sb;
        }

        if (isGroup) {
            sb.append(" GROUP BY ");
            for (int i = 0; i < groupFields.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(groupFields[i].getValue());
            }
        }

        if (order != null) {
            order.toSql(sb);
        }
        return sb;
    }

    public Object[] toParams() {
        return queryParams == null ? EMPTY_OBJECT_ARRAY : queryParams.toParams();
    }

    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
}
