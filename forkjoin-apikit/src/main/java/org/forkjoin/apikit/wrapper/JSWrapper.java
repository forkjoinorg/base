package org.forkjoin.apikit.wrapper;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.Import;
import org.forkjoin.apikit.info.ImportsInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.TypeInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zuoge85 on 15/6/15.
 */
public class JSWrapper<T extends ModuleInfo> extends BuilderWrapper<T> {
    public enum Type {
        ES6,
        FLOW_TYPE
    }

    private Type type = Type.FLOW_TYPE;

    public JSWrapper(Context context, T moduleInfo, String rootPackage) {
        super(context, moduleInfo, rootPackage);
    }

    @Override
    public void init() {
        addExclude("org.forkjoin.api.Message");
        super.init();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }




    public static String toTypeString(TypeInfo typeInfo) {
        StringBuilder sb = new StringBuilder();
        TypeInfo.Type type = typeInfo.getType();
        if (type == TypeInfo.Type.OTHER) {
            if (typeInfo.isGeneric()) {
                sb.append("Object");
            } else {
                sb.append(typeInfo.getName());
            }
        } else {
            sb.append(toJavaScriptString(type));
        }
        if (typeInfo.isArray()) {
            sb.append("[]");
        }
        return sb.toString();
    }

    private static final ImmutableMap<TypeInfo.Type, String> typeMap
            = ImmutableMap.<TypeInfo.Type, String>builder()
            .put(TypeInfo.Type.VOID, "void")
            .put(TypeInfo.Type.BOOLEAN, "Boolean")
            .put(TypeInfo.Type.BYTE, "Number")
            .put(TypeInfo.Type.SHORT, "Number")
            .put(TypeInfo.Type.INT, "Number")
            .put(TypeInfo.Type.LONG, "Number")
            .put(TypeInfo.Type.FLOAT, "Number")
            .put(TypeInfo.Type.DOUBLE, "Number")
            .put(TypeInfo.Type.DATE, "Date")
            .put(TypeInfo.Type.STRING, "String")
            .build();


    public static String toJavaScriptString(TypeInfo.Type type) {
        return typeMap.get(type);
    }

}
