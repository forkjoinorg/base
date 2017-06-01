package org.forkjoin.jdbckit.core.identifier;

import org.forkjoin.jdbckit.core.SqlUtils;

/**
 * @author zuoge85@gmail.com on 2017/5/23.
 */
public final class Field extends Identifier {
    protected Field(String nativeValue) {
        super(nativeValue);
    }

    public Field(String value, String nativeValue) {
        super(value, nativeValue);
    }

    public static final Field ALL_FIELDS = new Field("*", "*");


    public static Field[] of(String... names) {
        Field[] fields = new Field[names.length];
        for (int i = 0; i < names.length; i++) {
            fields[i] = of(names[i]);
        }
        return fields;
    }

    public static Field of(String nativeValue) {
        return new Field(nativeValue);
    }


    public static Field of(String table, String name) {
        return new Field("`" + SqlUtils.nameFilter(table) + "`.`" +
                SqlUtils.nameFilter(name) + "`", name);
    }

    public static Field of(Table table, String name) {
        return new Field(table.toString() + ".`" +
                SqlUtils.nameFilter(name) + "`", name);
    }

    /**
     * @param name           原始名称
     * @param filedAliasName 别名
     */
    public static Field ofAliasName(String name, String filedAliasName) {
        return new Field("`" + SqlUtils.nameFilter(name) + "` " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`", name);
    }

    /**
     * @param table      表名
     * @param name           原始名称
     * @param filedAliasName 别名
     */
    public static Field of(String table, String name, String filedAliasName) {
        return new Field("`" +
                SqlUtils.nameFilter(table) + "`.`" +
                SqlUtils.nameFilter(name) + "` " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`", name);
    }

    /**
     * @param table      表名
     * @param name           原始名称
     * @param filedAliasName 别名
     */
    public static Field of(Table table, String name, String filedAliasName) {
        return new Field(table + "`.`" +
                SqlUtils.nameFilter(name) + "` " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`", name);
    }


    public static Field sum(String name) {
        return fun("SUM", name);
    }

    public static Field sum(String table, String name) {
        return fun("SUM", table, name);
    }

    public static Field sum(Table table, String name) {
        return fun("SUM", table, name);
    }

    public static Field sumAliasName(String name, String filedAliasName) {
        return funAliasName("SUM", name, filedAliasName);
    }

    public static Field sum(String table, String name, String filedAliasName) {
        return fun("SUM", table, name, filedAliasName);
    }

    public static Field sum(Table table, String name, String filedAliasName) {
        return fun("SUM", table, name, filedAliasName);
    }

    //****************** count ****************
    public static Field count(String name) {
        return fun("COUNT", name);
    }

    public static Field count(String table, String name) {
        return fun("COUNT", table, name);
    }

    public static Field countAliasName(String name, String filedAliasName) {
        return funAliasName("COUNT", name, filedAliasName);
    }

    public static Field count(String table, String name, String filedAliasName) {
        return fun("COUNT", table, name, filedAliasName);
    }

    public static Field count(Table table, String name, String filedAliasName) {
        return fun("COUNT", table, name, filedAliasName);
    }

    //****************** max****************
    public static Field max(String name) {
        return fun("MAX", name);
    }


    public static Field max(String table, String name) {
        return fun("MAX", table, name);
    }

    public static Field max(Table table, String name) {
        return fun("MAX", table, name);
    }

    public static Field maxAliasName(String name, String filedAliasName) {
        return funAliasName("MAX", name, filedAliasName);
    }

    public static Field max(String table, String name, String filedAliasName) {
        return fun("MAX", table, name, filedAliasName);
    }

    public static Field max(Table table, String name, String filedAliasName) {
        return fun("MAX", table, name, filedAliasName);
    }

    //****************** min ****************
    public static Field min(String name) {
        return fun("MIN", name);
    }

    public static Field min(String table, String name) {
        return fun("MIN", table, name);
    }

    public static Field min(Table table, String name) {
        return fun("MIN", table, name);
    }

    public static Field minAliasName(String name, String filedAliasName) {
        return funAliasName("MIN", name, filedAliasName);
    }

    public static Field min(String table, String name, String filedAliasName) {
        return fun("MIN", table, name, filedAliasName);
    }

    public static Field min(Table table, String name, String filedAliasName) {
        return fun("MIN", table, name, filedAliasName);
    }

    public static Field fun(String fun, String name) {
        return new Field(fun + "(`" + SqlUtils.nameFilter(name) + "`)", name);
    }

    public static Field fun(String fun, String table, String name) {
        return new Field(fun + "(`" + SqlUtils.nameFilter(table) + "`.`" +
                SqlUtils.nameFilter(name) + "`)", name);
    }

    public static Field fun(String fun, Table table, String name) {
        return new Field(fun + "(" + table + ".`" +
                SqlUtils.nameFilter(name) + "`)", name);
    }

    public static Field funAliasName(String fun, String name, String filedAliasName) {
        return new Field(fun + "(`" + SqlUtils.nameFilter(name) + "`) " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`", name);
    }

    public static Field fun(String fun, String table, String name, String filedAliasName) {
        return new Field(fun + "(`" +
                SqlUtils.nameFilter(table) + "`.`" +
                SqlUtils.nameFilter(name) + "`) " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`", name);
    }

    public static Field fun(String fun, Table table, String name, String filedAliasName) {
        return new Field(fun + "(" +
                table + ".`" +
                SqlUtils.nameFilter(name) + "`) " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`", name);
    }
}
