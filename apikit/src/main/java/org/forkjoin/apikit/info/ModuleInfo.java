package org.forkjoin.apikit.info;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.Javadoc;
import org.forkjoin.apikit.oldmodel.PackageInfo;
import org.forkjoin.apikit.oldmodel.SupportType;

import java.util.ArrayList;

/**
 * @author zuoge85 on 15/6/11.
 */
public class ModuleInfo {
    private ImportsInfo imports;
    private JavadocInfo comment;
    private int id;
    private String name;
    private String packageName;

    public void setImports(ImportsInfo imports) {
        this.imports = imports;
    }

    public ImportsInfo getImports() {
        return imports;
    }

    public JavadocInfo getComment() {
        return comment;
    }

    public void setComment(JavadocInfo comment) {
        this.comment = comment;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFiledName() {
        if (StringUtils.isNotEmpty(name)) {
            char c = name.charAt(0);
            return Character.toLowerCase(c) + name.substring(1);
        }
        return null;
    }
}
