package org.forkjoin.apikit.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.JavadocInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.TypeInfo;
import org.forkjoin.apikit.utils.CommentUtils;

import java.util.ArrayList;

/**
 * @author zuoge85 on 15/6/15.
 */
public class BuilderWrapper<T extends ModuleInfo> {
    protected T moduleInfo;
    private String rootPackage;
    private ArrayList<String> importExcludes = new ArrayList<>();
    protected ArrayList<String> imports = new ArrayList<>();
    protected Context context;

    public BuilderWrapper(Context context, T moduleInfo, String rootPackage) {
        this.context = context;
        this.moduleInfo = moduleInfo;
        this.rootPackage = rootPackage;
    }

    public void init() {
        initImport();
    }

    public T getModuleInfo() {
        return moduleInfo;
    }

    public String comment(String start) {
        JavadocInfo comment = moduleInfo.getComment();
        return CommentUtils.formatComment(comment, start);
    }

    public void addExclude(String exclude) {
        importExcludes.add(exclude);
    }

    protected boolean exclude(String packageName) {
        for (String exclude : importExcludes) {
            if (packageName.startsWith(exclude)) {
                return true;
            }
        }
        return false;
    }


    public String getFullName(TypeInfo typeInfo) {
        return getPack(typeInfo.getPackageName()) + "." + typeInfo.getName();
    }

    public String getPack(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            return rootPackage;
        } else {
            if (StringUtils.isEmpty(rootPackage)) {
                return packageName;
            } else {
                return rootPackage + "." + packageName;
            }
        }
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public String getPack() {
        String packageName = moduleInfo.getPackageName();
        return getPack(packageName);
    }

    public String getFullName() {
        return getPack() + "." + getName();
    }

    public String getImports() {
        StringBuilder sb = new StringBuilder();
        for (String importItem : imports) {
            sb.append("import ");
            sb.append(importItem);
            sb.append(";\n");
        }
        return sb.toString();
    }

    public void initImport() {

    }
//
//
//    public String getTypeString(SupportType supportType) {
//        return getTypeString(supportType, false);
//    }
//
//    public String getTypeString(SupportType supportType, boolean isWrap) {
//        return supportType.getJavaTypeString(isWrap, false);
//    }
//
//    public String getTypeString(SupportType supportType, boolean isWrap, boolean isArrayList) {
//        return supportType.getJavaTypeString(isWrap, isArrayList);
//    }
//
//    public String getTypeStringNoArray(SupportType supportType) {
//        AttributeType attributeType = supportType.getType();
//        if (attributeType == AttributeType.OTHER) {
//            return supportType.getName();
//        } else {
//            return AttributeType.toJavaType(attributeType);
//        }
//    }

    public String getName() {
        return moduleInfo.getName();
    }

    public void addImport(String importItem) {
        imports.add(importItem);
    }

    public String getSimpleByFullName(String fullName) {
        int i = fullName.lastIndexOf(".");
        return i > -1 ? fullName.substring(i + 1) : fullName;
    }


    public static String formatBaseComment(JavadocInfo comment, String start) {
        return CommentUtils.formatBaseComment(comment, start);
    }

    public static String formatComment(JavadocInfo comment, String start) {
        return CommentUtils.formatComment(comment, start);
    }
}
