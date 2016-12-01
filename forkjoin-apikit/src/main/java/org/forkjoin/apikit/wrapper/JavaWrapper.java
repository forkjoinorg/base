package org.forkjoin.apikit.wrapper;

import com.google.common.collect.ImmutableMap;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.*;

import java.util.Date;
import java.util.List;

/**
 * @author zuoge85 on 15/6/15.
 */
public class JavaWrapper<T extends ModuleInfo> extends BuilderWrapper<T> {
    public JavaWrapper(Context context, T moduleInfo, String rootPackage) {
        super(context, moduleInfo, rootPackage);
    }

    @Override
    public void init() {
        addExclude("org.forkjoin.api.Message");
        super.init();
    }

    public String toJavaTypeStringNotArray(TypeInfo typeInfo) {
        return toJavaTypeString(typeInfo, false, false, false);
    }

    public String toJavaTypeString(TypeInfo typeInfo, boolean isWrap, boolean isArrayList) {
        return toJavaTypeString(typeInfo, isWrap, isArrayList, true);
    }


    public String toJavaTypeString(TypeInfo typeInfo) {
        return toJavaTypeString(typeInfo, true, true, true);
    }

    public String toJavaTypeString(TypeInfo typeInfo, boolean isWrap, boolean isArrayList, boolean isTypeArguments) {
        return toJavaTypeString(typeInfo, isWrap, isArrayList, isTypeArguments, isArrayList);
    }

    /**
     * @param isChildArrayList 参数类型是否处理数组
     */
    public String toJavaTypeString(TypeInfo typeInfo, boolean isWrap, boolean isArrayList, boolean isTypeArguments, boolean isChildArrayList) {
        StringBuilder sb = new StringBuilder();
        TypeInfo.Type type = typeInfo.getType();
        if (type == TypeInfo.Type.BYTE && typeInfo.isArray()) {
            sb.append("byte[]");
        } else if (isArrayList && typeInfo.isArray()) {
            toJavaArrayTypeString(typeInfo, sb, isWrap, true);
            return sb.toString();
        } else if (typeInfo.isOtherType()) {
            sb.append(typeInfo.getName());
        } else if (isWrap) {
            sb.append(toJavaWrapString(type));
        } else {
            sb.append(toJavaString(type));
        }
        List<TypeInfo> typeArguments = typeInfo.getTypeArguments();
        if (!typeArguments.isEmpty() && isTypeArguments) {
            sb.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                TypeInfo typeArgument = typeArguments.get(i);
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(toJavaTypeString(typeArgument, true, isChildArrayList));
            }
            sb.append('>');
        }
        return sb.toString();
    }

    /**
     * 处理数组!
     */
    protected void toJavaArrayTypeString(TypeInfo typeInfo, StringBuilder sb, boolean isWrap, boolean isArrayList) {
        sb.append("java.util.ArrayList");
        sb.append('<');
        sb.append(toJavaTypeString(typeInfo, true, false));
        sb.append('>');
    }


    // @Length(max = 255)
    public String formatAnnotations(List<AnnotationInfo> annotations, String start) {
        StringBuilder sb = new StringBuilder();
        for (AnnotationInfo annotation : annotations) {
            sb.append(start);
            sb.append("@");
            sb.append(toJavaTypeString(annotation.getTypeInfo(), true, true));

            List<String> args = annotation.getArgs();
            if (!args.isEmpty()) {
                sb.append("(");
                for (int i = 0; i < args.size(); i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    String arg = args.get(i);
                    sb.append(arg);
                }
                sb.append(")");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public void initImport() {
        ImportsInfo imports = moduleInfo.getImports();

        for (Import importItem : imports.getImports()) {
            if (importItem.isInside()) {
                String fullName = getPack(importItem.getPackageName()) + "." + importItem.getName();
                if (!exclude(fullName)) {
                    addImport(fullName);
                }
            } else {
                String fullName = importItem.getFullName();
                if (importItem.isOnDemand()) {
                    fullName += ".*";
                }
                if (!exclude(fullName)) {
                    addImport(fullName);
                }
            }
        }
    }

    private static final ImmutableMap<TypeInfo.Type, Class> typeMap
            = ImmutableMap.<TypeInfo.Type, Class>builder()
            .put(TypeInfo.Type.VOID, void.class)
            .put(TypeInfo.Type.BOOLEAN, boolean.class)
            .put(TypeInfo.Type.BYTE, byte.class)
            .put(TypeInfo.Type.SHORT, short.class)
            .put(TypeInfo.Type.INT, int.class)
            .put(TypeInfo.Type.LONG, long.class)
            .put(TypeInfo.Type.FLOAT, float.class)
            .put(TypeInfo.Type.DOUBLE, double.class)
            .put(TypeInfo.Type.DATE, Date.class)
            .put(TypeInfo.Type.STRING, String.class)
            .build();

    private static final ImmutableMap<TypeInfo.Type, Class> typeWrapMap
            = ImmutableMap.<TypeInfo.Type, Class>builder()
            .put(TypeInfo.Type.VOID, Void.class)
            .put(TypeInfo.Type.BOOLEAN, Boolean.class)
            .put(TypeInfo.Type.BYTE, Byte.class)
            .put(TypeInfo.Type.SHORT, Short.class)
            .put(TypeInfo.Type.INT, Integer.class)
            .put(TypeInfo.Type.LONG, Long.class)
            .put(TypeInfo.Type.FLOAT, Float.class)
            .put(TypeInfo.Type.DOUBLE, Double.class)
            .put(TypeInfo.Type.DATE, Date.class)
            .put(TypeInfo.Type.STRING, String.class)
            .build();

    public static String toJavaWrapString(TypeInfo.Type type) {
        return typeWrapMap.get(type).getSimpleName();
    }

    public static String toJavaString(TypeInfo.Type type) {
        return typeMap.get(type).getSimpleName();
    }
}
