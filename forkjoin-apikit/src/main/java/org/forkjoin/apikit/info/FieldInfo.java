package org.forkjoin.apikit.info;

import java.util.ArrayList;
import java.util.List;

public class FieldInfo {
    private TypeInfo typeInfo;
    private String name;
    private List<AnnotationInfo> annotations = new ArrayList<>();

    public FieldInfo(String name, TypeInfo typeInfo) {
        this.name = name;
        this.typeInfo = typeInfo;
    }

    public void addAnnotation(AnnotationInfo annotation) {
        annotations.add(annotation);
    }

    public List<AnnotationInfo> getAnnotations() {
        return annotations;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "FieldInfo{" +
                "typeInfo=" + typeInfo +
                ", name='" + name + '\'' +
                ", annotations=" + annotations +
                '}';
    }
}
