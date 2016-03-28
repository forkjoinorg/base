package org.forkjoin.apikit.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.JavadocInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.TypeInfo;

import java.util.*;

/**
 * @author zuoge85 on 15/6/15.
 */
public class BuilderWrapper<T extends ModuleInfo> {
    protected T moduleInfo;
    private String rootPackage;
    private ArrayList<String> importExcludes = new ArrayList<>();
    private ArrayList<String> imports = new ArrayList<>();
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
        return formatComment(comment, start);
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

    public static String formatBaseComment(JavadocInfo comment, String start) {
        if (comment == null) {
            return start;
        }
        StringBuilder sb = new StringBuilder();

        for (String tagName : comment.getTags().keySet()) {
            sb.append(start);
            formatCommentItem(comment, start, sb, tagName);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        } else {
            return start;
        }
        return sb.toString();
    }

    public static String formatComment(JavadocInfo comment, String start) {
        if (comment == null) {
            return start;
        }
        StringBuilder sb = new StringBuilder();
        for (String tagName : comment.getTags().keySet()) {
            sb.append(start);
            if (StringUtils.isNotEmpty(tagName)) {
                sb.append(tagName);
                sb.append(' ');
            }
            formatCommentItem(comment, start, sb, tagName);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private static void formatCommentItem(JavadocInfo comment, String start, StringBuilder sb, String tagName) {
        Collection<String> fragments = comment.getTags().get(tagName);
        int i = 0;
        for (String fragment : fragments) {
            if (i > 0) {
                sb.append(start);
            }
            sb.append(fragment);
            sb.append('\n');
            i++;
        }
    }

    public static Map<String, String> commentToMap(JavadocInfo comment) {
        if (comment == null) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<>();

        for (String tagName : comment.getTags().keySet()) {
            StringBuilder sb = new StringBuilder();
            String paramName = null;

            boolean isParam = tagName.equals("@param");

            Collection<String> fragments = comment.getTags().get(tagName);
            for (String fragment : fragments) {
                paramName = fragment;
                if (!isParam) {
                    sb.append(fragment);
                    sb.append(' ');
                }
            }

            if (isParam && paramName != null) {
                map.put(paramName, sb.toString());
            } else {
                map.put(tagName, sb.toString());
            }
        }
        return map;
    }

    public String getFullName(TypeInfo typeInfo) {
        return getPack(typeInfo.getPackageName()) + "." + typeInfo.getName();
    }

    public String getPack(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            return rootPackage;
        } else {
            return rootPackage + "." + packageName;
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
}
