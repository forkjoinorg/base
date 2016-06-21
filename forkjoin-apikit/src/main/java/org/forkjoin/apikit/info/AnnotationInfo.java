package org.forkjoin.apikit.info;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoge85 on 15/12/9.
 */
public class AnnotationInfo {
    private TypeInfo typeInfo;

    private List<String> args = new ArrayList<>();

    public AnnotationInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    public List<String> getArgs() {
        return args;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void addArgs(String arg) {
        args.add(arg);
    }
}
