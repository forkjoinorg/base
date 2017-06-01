package org.forkjoin.jdbckit.core.identifier;

/**
 * @author zuoge85@gmail.com on 2017/5/23.
 */
public final class Key extends Identifier {
    private boolean isUnique = true;
    protected Key(String nativeValue,boolean isUnique) {
        super(nativeValue);
        this.isUnique = isUnique;
    }

    public static Key of(String nativeValue,boolean isUnique) {
        return new Key(nativeValue,isUnique);
    }

    public boolean isUnique() {
        return isUnique;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Key key = (Key) o;

        if (isUnique != key.isUnique) return false;
        return value.equals(key.value);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isUnique ? 1 : 0);
        result = 31 * result + value.hashCode();
        return result;
    }
}
