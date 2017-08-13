package org.forkjoin.jdbckit.mysql;

import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author zuoge85@gmail.com on 2017/8/8.
 */
public class DefaultTablePrefix implements Predicate<Table> {
    private Set<String> excludeTableName;


    public DefaultTablePrefix() {
        this(null);
    }

    public DefaultTablePrefix(Set<String> excludeTableName) {
        if (excludeTableName == null) {
            this.excludeTableName = newDefault();
        } else {
            this.excludeTableName = excludeTableName;
        }
    }


    protected ImmutableSet<String> newDefault() {
        return ImmutableSet.of(
                "schema_version"
        );
    }

    @Override
    public boolean test(Table table) {
        return !excludeTableName.contains(table.getDbName());
    }

    public Set<String> getExcludeTableName() {
        return excludeTableName;
    }

    public void setExcludeTableName(Set<String> excludeTableName) {
        this.excludeTableName = excludeTableName;
    }
}
