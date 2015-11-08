package org.forkjoin.apikit.oldmodel;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.Javadoc;

import java.util.ArrayList;

/**
 * @author zuoge85 on 15/6/11.
 */
public class ModuleInfo {
    private ArrayList<SupportType> imports = new ArrayList<>();
    private Javadoc comment;
    private SupportType supportType;
    private int id;
    private PackageInfo pack;

    public void addImport(SupportType importDeclaration) {
        imports.add(importDeclaration);
    }

    public ArrayList<SupportType> getImports() {
        return imports;
    }


    public Javadoc getComment() {
        return comment;
    }

    public void setComment(Javadoc comment) {
        this.comment = comment;
    }

    public SupportType getSupportType() {
        return supportType;
    }

    public void setSupportType(SupportType supportType) {
        this.supportType = supportType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PackageInfo getPack() {
        return pack;
    }

    public void setPack(PackageInfo pack) {
        this.pack = pack;
    }

    public String getName(){
        return supportType.getName();
    }

    public String getPackageName(){
        return supportType.getPackageName();
    }

    @Override
    public String toString() {
        return "ModuleInfo{" +
                "imports=" + imports +
                ", comment=" + comment +
                ", supportType=" + supportType +
                ", id=" + id +
                ", pack=" + pack +
                '}';
    }

    public String getFiledName(){
        String name = getName();
        if(StringUtils.isNotEmpty(name)){
            char c = name.charAt(0);
            return Character.toLowerCase(c) + name.substring(1);
        }
        return null;
    }
}
