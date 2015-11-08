package org.forkjoin.apikit.oldmodel;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;

import java.util.Date;


/**
 *
 */
public enum AttributeType {
    BOOLEAN, INT, LONG, FLOAT, DOUBLE, STRING, BYTES, DATE, OTHER;

    private static final ImmutableBiMap<String, AttributeType> typeMap = ImmutableBiMap.<String, AttributeType>builder()
            .put("boolean", BOOLEAN)
            .put("int", INT)
            .put("long", LONG)
            .put("float", FLOAT)
            .put("double", DOUBLE)
            .put("String", STRING)
            .put("byte[]", BYTES)
            .put("Date", DATE)
            .build();

    private static final ImmutableBiMap<String, AttributeType> wrapTypeMap = ImmutableBiMap.<String, AttributeType>builder()
            .put(Boolean.class.getName(), BOOLEAN)
            .put(Integer.class.getName(), INT)
            .put(Long.class.getName(), LONG)
            .put(Float.class.getName(), FLOAT)
            .put(Double.class.getName(), DOUBLE)
            .put(String.class.getName(), STRING)
            .put(Date.class.getName(), DATE)
            .build();

    private static final ImmutableBiMap<String, AttributeType> wrapTypeNoPackageMap = ImmutableBiMap.<String, AttributeType>builder()
            .put(Boolean.class.getSimpleName(), BOOLEAN)
            .put(Integer.class.getSimpleName(), INT)
            .put(Long.class.getSimpleName(), LONG)
            .put(Float.class.getSimpleName(), FLOAT)
            .put(Double.class.getSimpleName(), DOUBLE)
            .put(String.class.getSimpleName(), STRING)
            .put(Date.class.getSimpleName(), DATE)
            .build();


    private static final ImmutableMap<AttributeType, String> ocType = ImmutableMap.<AttributeType, String>builder()
            .put(BOOLEAN, "BOOL")
            .put(INT, "NSNumber")
            .put(LONG, "NSNumber")
            .put(FLOAT, "NSNumber")
            .put(DOUBLE, "NSNumber")
            .put(STRING, "NSString")
            .put(BYTES, "NSData")
            .put(DATE, "NSDate")
            .build();

    private static final ImmutableMap<AttributeType, String> swiftType = ImmutableMap.<AttributeType, String>builder()
            .put(BOOLEAN, "Bool")
            .put(INT, "Int32")
            .put(LONG, "Int64")
            .put(FLOAT, "Float")
            .put(DOUBLE, "Double")
            .put(STRING, "String")
            .put(BYTES, "NSData")
            .put(DATE, "NSDate")
            .build();


    private static final ImmutableMap<AttributeType, String> swiftDefault = ImmutableMap.<AttributeType, String>builder()
            .put(BOOLEAN, "false")
            .put(INT, "Int32(0)")
            .put(LONG, "Int64(0)")
            .put(FLOAT, "Float(0)")
            .put(DOUBLE, "Double(0)")
            .put(STRING, "nil")
            .put(BYTES, "nil")
            .put(DATE, "nil")
            .build();
//    @property(nonatomic, copy) NSString *jiancetime;
//    @property(nonatomic, copy) NSString *hospital;
//    @property(nonatomic, assign) int64_t int64;
//    @property(nonatomic, assign) int32_t int32;
//
//    @property(nonatomic, assign) float floatValue;
//    @property(nonatomic, assign) double doubleValue;
//    @property(nonatomic, assign) NSDate *ns;
//    @property(nonatomic, assign) MyClass *myClass;


    public static AttributeType form(String name) {
        AttributeType attributeType = typeMap.get(name);
        return attributeType == null ? OTHER : attributeType;
    }

    public static AttributeType formWrap(String name) {
        return formWrap(null, name);
    }

    public static AttributeType formWrap(String packageName, String name) {
        if (packageName == null) {
            AttributeType form = form(name);
            if (form == OTHER) {
                if(wrapTypeNoPackageMap.containsKey(name)){
                    form = wrapTypeNoPackageMap.get(name);
                }
            }
            return form;
        }
        AttributeType attributeType = wrapTypeMap.get(packageName + '.' + name);
        return attributeType == null ? OTHER : attributeType;
    }

    public static String toJavaType(AttributeType type) {
        if (type == OTHER) {
            throw new RuntimeException("错误的类型:" + type);
        }
        return typeMap.inverse().get(type);
    }

    public static String toOcType(AttributeType type) {
        return ocType.get(type);
    }

    public static String toSwiftType(AttributeType type) {
        return swiftType.get(type);
    }

    public static String getSwiftDefault(AttributeType type) {
        return swiftDefault.get(type);
    }

    public static String toJavaWrapType(AttributeType type) {
        if (type == OTHER) {
            throw new RuntimeException("错误的类型:" + type);
        }
        return wrapTypeMap.inverse().get(type);
    }


    public static boolean isHasNull(AttributeType type) {
        return type == STRING || type == BYTES || type == OTHER;
    }

    public static boolean isBaseType(AttributeType type) {
        return type == INT || type == LONG || type == BOOLEAN || type == FLOAT || type == DOUBLE;
    }
}
