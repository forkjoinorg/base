package org.forkjoin.apikit.oldmodel;

import org.forkjoin.apikit.old.Config;
import org.forkjoin.apikit.old.FileAnalyse;
import org.eclipse.jdt.core.dom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoge85 on 15/6/12.
 */
public class SupportType {
    private static final Logger log = LoggerFactory.getLogger(SupportType.class);

    private AttributeType type;
    private String packageName;
    private String name;
    private boolean isArray;
    private boolean isApiType = false;
    private List<SupportType> typeArguments = new ArrayList<>();

    private boolean generic = false;

    private SupportType() {
    }

    private SupportType(AttributeType type, String packageName, String name, boolean isArray, boolean isApiType) {
        this.type = type;
        this.packageName = packageName;
        this.name = name;
        this.isArray = isArray;
        this.isApiType = isApiType;
    }


    public static SupportType from(FileAnalyse fileAnalyse, Type fieldType) {
        SupportType supportType = innerForm(fileAnalyse, fieldType);
        if(supportType != null&&supportType.isOtherType()){
            supportType.setGeneric(fileAnalyse.isGeneric(supportType.getName()));
        }
        return supportType;
    }

    private static SupportType innerForm(FileAnalyse fileAnalyse, Type fieldType) {
        Config config = fileAnalyse.getConfig();

        String name;
        AttributeType type = null;

        if (fieldType.isPrimitiveType()) {
            PrimitiveType primitiveType = (PrimitiveType) fieldType;
            name = primitiveType.getPrimitiveTypeCode().toString();
            type = AttributeType.form(name);
            return from(config, name, null, type, false);
        } else if (fieldType.isSimpleType()) {
            SimpleType simpleType = (SimpleType) fieldType;
            return formSimpleType(config, fileAnalyse, simpleType, false);
        } else if (fieldType.isArrayType()) {
            ArrayType arrayType = (ArrayType) fieldType;
            Type elementType = arrayType.getElementType();
            if (elementType.isPrimitiveType()) {
                PrimitiveType primitiveType = (PrimitiveType) elementType;
                if (primitiveType.getPrimitiveTypeCode().toString().equals("byte")) {
                    type = AttributeType.BYTES;
                }
            }
            return from(config, null, null, type, false);
        } else if (fieldType.isParameterizedType()) {
            ParameterizedType parameterizedType = (ParameterizedType) fieldType;
            Type parameterizedTypeType = parameterizedType.getType();
            if (parameterizedTypeType.isSimpleType()) {
                QualifiedName typeName = fileAnalyse.getTypeName(((SimpleType) parameterizedTypeType).getName());
                List typeArguments = parameterizedType.typeArguments();
                SupportType supportType = formSimpleType(config, fileAnalyse, (SimpleType) parameterizedTypeType, isArray(typeName));
                if (supportType != null) {
                    for (Object typeArgument : typeArguments) {
                        supportType.addTypeArgument(SupportType.from(fileAnalyse, (Type) typeArgument));
                    }
                }
                return supportType;
            }
        }
        log.error("不支持的类型：name:{}", fieldType);
        return null;
    }

    private void addTypeArgument(SupportType type) {
        if (type != null) {
            typeArguments.add(type);
        }
    }

    public List<SupportType> getTypeArguments() {
        return typeArguments;
    }

    private static SupportType formSimpleType(Config config, FileAnalyse fileAnalyse, SimpleType simpleType, boolean isArray) {

        QualifiedName typeName = fileAnalyse.getTypeName(simpleType.getName());
        if (typeName != null) {
            String name = typeName.getName().getFullyQualifiedName();
            String packageName = typeName.getQualifier().getFullyQualifiedName();
            AttributeType type = AttributeType.formWrap(packageName, name);
            return from(config, name, packageName, type, isArray);
        }
        return null;
    }

    public static SupportType from(Config config, ImportDeclaration importDeclaration) {
        if (importDeclaration.isStatic()) {
            throw new RuntimeException("不支持静态导入");
        }

        Name name = importDeclaration.getName();
        if (name instanceof QualifiedName) {
            QualifiedName qName = (QualifiedName) name;
            return from(config, qName.getName().getFullyQualifiedName(), qName.getQualifier().getFullyQualifiedName(), AttributeType.OTHER, false);
        } else {
            throw new RuntimeException("不支持的导入名称:" + name);
        }
    }

    public static SupportType from(
            Config config, String name, String packageName,
            AttributeType type, boolean isArray
    ) {
        boolean isApiType = false;
        if (packageName != null) {
            String rootPackage = config.getRootPackage();
            if (packageName.startsWith(rootPackage)) {
                isApiType = true;
                packageName = packageName.substring(rootPackage.length());
                if (packageName.startsWith(".")) {
                    packageName = packageName.substring(1);
                }
            }
        }
        return new SupportType(type, packageName, name, isArray, isApiType);
    }

    private static boolean isArray(QualifiedName typeName) {
        String name = typeName.getName().getFullyQualifiedName();
        String packageName = typeName.getQualifier().getFullyQualifiedName();
        return packageName.equals("java.util") && name.equals("List");
    }

    public String getJavaTypeString() {
        return getJavaTypeString(false, false);
    }

    public String getJavaTypeString(boolean isWrap, boolean isArrayList) {
        String listType = isArrayList ? "java.util.ArrayList" : "java.util.List";
        AttributeType attributeType = this.getType();
        if (attributeType == AttributeType.OTHER) {
            if (this.isArray()) {
                return listType + "<" + this.getName() + ">";
            } else {
                return this.getName();
            }
        } else {
            if (this.isArray()) {
                return listType + "<" + AttributeType.toJavaWrapType(attributeType) + ">";
            } else {
                if (isWrap) {
                    return AttributeType.toJavaWrapType(attributeType);
                } else {
                    return AttributeType.toJavaType(attributeType);
                }
            }
        }
    }

//    public String getSwiftTypeString() {
////        String listType = isArrayList ? "java.util.ArrayList" : "java.util.List";
//        AttributeType attributeType = this.getType();
//        if (attributeType == AttributeType.OTHER) {
//            if (this.isArray()) {
//                return "[" + this.getName() + "]";
//            } else {
//                return this.getName();
//            }
//        } else {
//            if (this.isArray()) {
//                return "[" + AttributeType.toSwiftType(attributeType) + "]";
//            } else {
//                return AttributeType.toSwiftType(attributeType);
//            }
//        }
//    }

    public String getSwiftTypeString(String prefix,boolean isTypeArguments) {
        StringBuilder sb = new StringBuilder();
        AttributeType attributeType = this.getType();
        List<SupportType> typeArguments = new ArrayList<>(getTypeArguments());

        if (attributeType == AttributeType.OTHER) {
            if (isArray()) {
                sb.append("[");
                if(!typeArguments.isEmpty()){
                    SupportType type = typeArguments.remove(0);
                    sb.append(type.getSwiftTypeString(prefix, true));
                }
                sb.append("]");
                return sb.toString();
            } else {
                if(!this.isGeneric() && !getName().equals("Void")){
                    sb.append(prefix);
                }
                sb.append(this.getName());
            }
        } else {
            sb.append(AttributeType.toSwiftType(attributeType));
        }

        if (!typeArguments.isEmpty() && isTypeArguments) {
            sb.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                SupportType typeArgument = typeArguments.get(i);
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(typeArgument.getSwiftTypeString(prefix, true));
            }
            sb.append('>');
        }
        return sb.toString();
    }

    public String getNewJavaTypeString(boolean isWrap, boolean isArrayList) {
        return getNewJavaTypeString(isWrap, isArrayList, true);
    }

    public String getNewJavaTypeString(boolean isWrap, boolean isArrayList, boolean isTypeArguments) {
        StringBuilder sb = new StringBuilder();
        AttributeType attributeType = this.getType();
        if (attributeType == AttributeType.OTHER) {
            if (isArrayList && isArray()) {
                sb.append("java.util.ArrayList");
            } else {
                sb.append(this.getName());
            }
        } else if (isWrap) {
            sb.append(AttributeType.toJavaWrapType(attributeType));
        } else {
            sb.append(AttributeType.toJavaType(attributeType));
        }
        List<SupportType> typeArguments = getTypeArguments();
        if (!typeArguments.isEmpty() && isTypeArguments) {
            sb.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                SupportType typeArgument = typeArguments.get(i);
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(typeArgument.getNewJavaTypeString(true, isArrayList));
            }
            sb.append('>');
        }
        return sb.toString();
    }

    public String getNewJavaTypeStringNoArray() {
        StringBuilder sb = new StringBuilder();
        List<SupportType> typeArguments = getTypeArguments();
        if (!typeArguments.isEmpty()) {
            sb.append(typeArguments.get(0).getNewJavaTypeString(true, false));
        }
        return sb.toString();
    }

    public String getTypeStringNoArray() {
        AttributeType attributeType = this.getType();
        if (attributeType == AttributeType.OTHER) {
            return this.getName();
        } else {
            return AttributeType.toJavaType(attributeType);
        }
    }

    public AttributeType getType() {
        return type;
    }

    public boolean isOtherType() {
        return type == AttributeType.OTHER;
    }

    public boolean isHasNull() {
//        BOOLEAN, INT, LONG, FLOAT, DOUBLE, STRING, BYTES, DATE, OTHER;
        return AttributeType.isHasNull(type);
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }

    public boolean isApiType() {
        return isApiType;
    }

    public void setIsApiType(boolean isApiType) {
        this.isApiType = isApiType;
    }


    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    @Override
    public String toString() {
        return "SupportType{" +
                "type=" + type +
                ", packageName='" + packageName + '\'' +
                ", name='" + name + '\'' +
                ", isArray=" + isArray +
                ", isApiType=" + isApiType +
                ", typeArguments=" + typeArguments +
                ", generic=" + generic +
                '}';
    }

    public boolean isBaseType() {
        return AttributeType.isBaseType(getType());
    }

    public String getSwiftDefault() {
        return AttributeType.getSwiftDefault(getType());
    }
}
