package org.forkjoin.apikit.wrapper;

import com.google.common.collect.ImmutableMap;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.ModuleInfo;
import org.forkjoin.apikit.info.TypeInfo;

/**
 * @author zuoge85 on 15/6/15.
 */
public class JSWrapper<T extends ModuleInfo> extends BuilderWrapper<T> {
    public enum Type {
        ES6,
        JS,
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


    public String toTypeString(TypeInfo typeInfo) {
        return toTypeString(typeInfo, false);
    }

    public String toTypeString(TypeInfo typeInfo, boolean isArray) {
        StringBuilder sb = new StringBuilder();
        TypeInfo.Type type = typeInfo.getType();
        if (typeInfo.isCollection()) {
            TypeInfo typeInfoArg = typeInfo.getTypeArguments().get(0);
            return toTypeString(typeInfoArg, true);
        } else if (type == TypeInfo.Type.OTHER) {
            if (typeInfo.isGeneric()) {
                sb.append("Object");
            } else {
                sb.append(typeInfo.getName());
            }
        } else {
            sb.append(toJavaScriptString(type));
        }
        if (typeInfo.isArray() || isArray) {
            sb.append("[]");
        }
        return sb.toString();
    }

    private static final ImmutableMap<TypeInfo.Type, String> typeMap
            = ImmutableMap.<TypeInfo.Type, String>builder()
            .put(TypeInfo.Type.VOID, "void")
            .put(TypeInfo.Type.BOOLEAN, "boolean")
            .put(TypeInfo.Type.BYTE, "number")
            .put(TypeInfo.Type.SHORT, "number")
            .put(TypeInfo.Type.INT, "number")
            .put(TypeInfo.Type.LONG, "number")
            .put(TypeInfo.Type.FLOAT, "number")
            .put(TypeInfo.Type.DOUBLE, "number")
            .put(TypeInfo.Type.DATE, "Date")
            .put(TypeInfo.Type.STRING, "string")
            .build();


    public String toJavaScriptString(TypeInfo.Type type) {
        return typeMap.get(type);
    }

}
