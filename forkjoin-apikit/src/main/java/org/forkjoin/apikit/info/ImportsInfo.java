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


    public Import getOnDemandImport(String name) {
        for (Import imp : imports) {
            if (imp.isOnDemand()) {
                try {
                    Class<?> aClass = Class.forName(imp.getFullName() + "." + name);
                    return new Import(imp.getFullName(), name, false, false);
                } catch (ClassNotFoundException ignored) {
                }
            }
        }
        return null;
    }
}
