package org.forkjoin.apikit.info;

/**
 * @author zuoge85 on 15/11/15.
 */
public class Import {
    private String packageName;
    private String name;

    /**
     * 是否包内，也就是需要进行包转换
     */
    private boolean isInside = false;
    private boolean onDemand;



    public Import(String packageName, String name, boolean isInside, boolean onDemand) {
        this.packageName = packageName;
        this.name = name;
        this.isInside = isInside;
        this.onDemand = onDemand;
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

    public boolean isOnDemand() {
        return onDemand;
    }

    public void setOnDemand(boolean onDemand) {
        this.onDemand = onDemand;
    }
}
