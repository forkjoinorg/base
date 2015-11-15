package org.forkjoin.apikit.info;

/**
 * @author zuoge85 on 15/11/15.
 */
public class Import {
    private String packageName;
    private String name;


    public Import(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }
}
