package org.forkjoin.apikit.info;

/**
 * @author zuoge85 on 15/11/15.
 */
public class Import {
    private String packageName;
    private String name;
    private boolean isInside = false;



    public Import(String packageName, String name, boolean isInside) {
        this.packageName = packageName;
        this.name = name;
        this.isInside = isInside;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return packageName + "." + name;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }
}
