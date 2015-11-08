package org.forkjoin.apikit.oldmodel;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Javadoc;

import java.util.ArrayList;

public final class AttributeInfo {
    private SupportType supportType;
    private String name;
    private Javadoc comment;
    private ArrayList<Annotation> annotations = new ArrayList<>();

    public AttributeInfo() {

    }

    public String getJavaAddName() {
        return "add" + Utils.toClassName(name);
    }

    public String getJavaSetName() {
        return "set" + Utils.toClassName(name);
    }

    public String getJavaGetName() {
        return "get" + Utils.toClassName(name);
    }


    public SupportType getSupportType() {
        return supportType;
    }

    public void setSupportType(SupportType supportType) {
        this.supportType = supportType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Javadoc getComment() {
        return comment;
    }

    public void setComment(Javadoc comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "AttributeInfo{" +
                "supportType=" + supportType +
                ", name='" + name + '\'' +
                '}';
    }

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }


    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }
}
