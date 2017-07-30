package org.forkjoin.apikit.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoge85 on 15/11/15.
 */
public class ImportsInfo {
    private ArrayList<Import> imports = new ArrayList<>();
    private Map<String, Import> map = new HashMap<>();

    public void add(String packageName, String name, boolean isInside, boolean onDemand) {
        add(new Import(packageName, name, isInside, onDemand));
    }

    public void add(Import importInfo) {
        imports.add(importInfo);
        map.put(importInfo.getName(), importInfo);
    }

    public ArrayList<Import> getImports() {
        return imports;
    }

    public Import get(String name) {
        return map.get(name);
    }


    public Import getOnDemandImport(String name, String sourcePackage) {
        for (Import imp : imports) {
            if (imp.isOnDemand()) {
                try {
                    String className;
                    if (imp.isInside()) {
                        className = sourcePackage + imp.getPackageName() + "." + name;
                    } else {
                        className = imp.getPackageName() + "." + name;
                    }
                    Class<?> aClass = Class.forName(className);
                    return new Import(imp.getPackageName(), name, imp.isInside(), imp.isOnDemand());
                } catch (ClassNotFoundException ignored) {
                }
            }
        }
        return null;
    }
}
