package org.forkjoin.apikit.info;

/**
 * @author zuoge85 on 15/12/9.
 */
public class PropertyInfo extends FieldInfo {
    private JavadocInfo comment;

    public PropertyInfo(String name, TypeInfo typeInfo) {
        super(name, typeInfo);
    }

    public JavadocInfo getComment() {
        return comment;
    }

    public void setComment(JavadocInfo comment) {
        this.comment = comment;
    }
}
