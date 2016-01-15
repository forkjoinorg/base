package org.forkjoin.core.dao;

/**
 * @author zuoge85 on 15/11/17.
 */
public final class Field {
    public static final Field ALL_FIELDS = new Field("*");

    private final String value;

    private Field(String value) {
        this.value = value;
    }

    public static Field[] forms(String... names) {
        Field[] fields = new Field[names.length];
        for (int i = 0; i < names.length; i++) {
            fields[i] = form(names[i]);
        }
        return fields;
    }

    public static Field form(String name) {
        return new Field("`" + SqlUtils.nameFilter(name) + "`");
    }

    public static Field form(String tableName, String name) {
        return new Field("`" + SqlUtils.nameFilter(tableName) + "`.`" +
                SqlUtils.nameFilter(name) + "`");
    }

    public static Field formAliasName(String name, String filedAliasName) {
        return new Field("`" + SqlUtils.nameFilter(name) + "` " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`");
    }

    public static Field form(String tableName, String name, String filedAliasName) {
        return new Field("`" +
                SqlUtils.nameFilter(tableName) + "`.`" +
                SqlUtils.nameFilter(name) + "` " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`");
    }


    public String getValue() {
        return value;
    }

    public static Field sum(String name) {
        return fun("SUM", name);
    }

    public static Field sum(String tableName, String name) {
        return fun("SUM", tableName, name);
    }

    public static Field sumAliasName(String name, String filedAliasName) {
        return funAliasName("SUM", name, filedAliasName);
    }

    public static Field sum(String tableName, String name, String filedAliasName) {
        return fun("SUM", tableName, name, filedAliasName);
    }


    public static Field count(String name) {
        return fun("COUNT", name);
    }

    public static Field count(String tableName, String name) {
        return fun("COUNT", tableName, name);
    }

    public static Field countAliasName(String name, String filedAliasName) {
        return funAliasName("COUNT", name, filedAliasName);
    }

    public static Field count(String tableName, String name, String filedAliasName) {
        return fun("COUNT", tableName, name, filedAliasName);
    }


    private static Field fun(String fun, String name) {
        return new Field(fun + "(`" + SqlUtils.nameFilter(name) + "`)");
    }

    private static Field fun(String fun, String tableName, String name) {
        return new Field(fun + "(`" + SqlUtils.nameFilter(tableName) + "`.`" +
                SqlUtils.nameFilter(name) + "`)");
    }

    private static Field funAliasName(String fun, String name, String filedAliasName) {
        return new Field(fun + "(`" + SqlUtils.nameFilter(name) + "`) " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`");
    }

    private static Field fun(String fun, String tableName, String name, String filedAliasName) {
        return new Field(fun + "(`" +
                SqlUtils.nameFilter(tableName) + "`.`" +
                SqlUtils.nameFilter(name) + "`) " + "`" +
                SqlUtils.nameFilter(filedAliasName) + "`");
    }



}
