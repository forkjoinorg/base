package org.forkjoin.jdbckit.core.identifier;

/**
 * @author zuoge85@gmail.com on 2017/5/23.
 */
public final class Table extends Identifier {
    protected Table(String nativeValue) {
        super(nativeValue);
    }

    public static Table of(String nativeValue) {
        return new Table(nativeValue);
    }
}
