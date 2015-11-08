package org.forkjoin.apikit.builder;

import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.oldmodel.AttributeType;
import org.forkjoin.apikit.oldmodel.ModelInfo;
import org.forkjoin.apikit.oldmodel.ModuleInfo;
import org.forkjoin.apikit.oldmodel.SupportType;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;

import java.util.*;

/**
 * @author zuoge85 on 15/6/15.
 */
public class BuilderUtils {
    protected Config config;
    protected ModelInfo modelInfo;
    protected ModuleInfo moduleInfo;
    private String rootPackage;
    private Object file;
    private ArrayList<String> importExcludes = new ArrayList<>();
    private ArrayList<String> imports = new ArrayList<>();

    public BuilderUtils(Config config, ModelInfo modelInfo, ModuleInfo moduleInfo, String rootPackage) {
        this.config = config;
        this.modelInfo = modelInfo;
        this.moduleInfo = moduleInfo;
        this.rootPackage = rootPackage;
    }

    public String getVersion() {
        return config.getVersion();
    }

    public void init() {
        initImport();
    }

    public String comment(String start) {
        Javadoc comment = moduleInfo.getComment();
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

    public static String formatBaseComment(Javadoc comment, String start) {
        if (comment == null) {
            return start;
        }
        StringBuilder sb = new StringBuilder();
        List tags = comment.tags();
        for (Object tag : tags) {
            TagElement tagElement = (TagElement) tag;
            String tagName = tagElement.getTagName();

            if (StringUtils.isNotEmpty(tagName)) {
                continue;
            }

            sb.append(start);
            List fragments = tagElement.fragments();
            for (int i = 0; i < fragments.size(); i++) {
                Object fragment = fragments.get(i);
                if (fragment instanceof TextElement) {
                    TextElement textElement = (TextElement) fragment;
                    if (i > 0) {
                        sb.append(start);
                    }
                    sb.append(textElement.getText());
                    sb.append('\n');
                } else {
                    if (i > 0) {
                        sb.append(start);
                    }
                    sb.append(fragment.toString());
                    sb.append('\n');
                }
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        } else {
            return start;
        }
        return sb.toString();
    }

    public static String formatComment(Javadoc comment, String start) {
        if (comment == null) {
            return start;
        }
        StringBuilder sb = new StringBuilder();
        List tags = comment.tags();
        for (Object tag : tags) {
            TagElement tagElement = (TagElement) tag;
            String tagName = tagElement.getTagName();
            sb.append(start);
            if (StringUtils.isNotEmpty(tagName)) {
                sb.append(tagName);
                sb.append(' ');
            }
            List fragments = tagElement.fragments();
            for (int i = 0; i < fragments.size(); i++) {
                Object fragment = fragments.get(i);
                if (fragment instanceof TextElement) {
                    TextElement textElement = (TextElement) fragment;
                    if (i > 0) {
                        sb.append(start);
                    }
                    sb.append(textElement.getText());
                    sb.append('\n');
                } else {
                    if (i > 0) {
                        sb.append(start);
                    }
                    sb.append(fragment.toString());
                    sb.append('\n');
                }
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static Map<String, String> commentToMap(Javadoc comment) {
        if(comment == null){
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<>();

        List tags = comment.tags();
        for (Object tag : tags) {
            TagElement tagElement = (TagElement) tag;
            String tagName = tagElement.getTagName();
            if (StringUtils.isEmpty(tagName)) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            List fragments = tagElement.fragments();
            String paramName = null;

            boolean isParam = tagName.equals("@param");

            for (int i = 0; i < fragments.size(); i++) {
                Object fragment = fragments.get(i);
                if (fragment instanceof TextElement) {
                    TextElement textElement = (TextElement) fragment;
                    sb.append(textElement.getText());
                    sb.append(' ');
                } else {
                    paramName = fragment.toString();
                    if(!isParam){
                        sb.append(fragment.toString());
                        sb.append(' ');
                    }
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


    private String getPack(String packageName) {
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
        for (SupportType supportType : moduleInfo.getImports()) {
            if (supportType.isApiType()) {
                addImport(getPack(supportType.getPackageName()) + "." + supportType.getName());
            } else {
                String fullName = supportType.getPackageName() + "." + supportType.getName();
                if (!exclude(fullName)) {
                    addImport(fullName);
                }
            }
        }
    }


    public String getTypeString(SupportType supportType) {
        return getTypeString(supportType,false);
    }

    public String getTypeString(SupportType supportType, boolean isWrap) {
        return supportType.getJavaTypeString(isWrap, false);
    }

    public String getTypeString(SupportType supportType, boolean isWrap, boolean isArrayList) {
        return supportType.getJavaTypeString(isWrap, isArrayList);
    }

    public String getTypeStringNoArray(SupportType supportType) {
        AttributeType attributeType = supportType.getType();
        if (attributeType == AttributeType.OTHER) {
            return supportType.getName();
        } else {
            return AttributeType.toJavaType(attributeType);
        }
    }

    public String formatAnnotations(ArrayList<Annotation> annotations, String start) {
        StringBuilder sb = new StringBuilder();
        for (Annotation annotation : annotations) {
            sb.append(start);
            sb.append(annotation.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

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
