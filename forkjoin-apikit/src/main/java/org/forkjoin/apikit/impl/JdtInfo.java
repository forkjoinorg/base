package org.forkjoin.apikit.impl;

import org.apache.commons.lang3.ClassUtils;
import org.eclipse.jdt.core.dom.*;
import org.forkjoin.apikit.AnalyseException;
import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.info.Import;
import org.forkjoin.apikit.info.ImportsInfo;
import org.forkjoin.apikit.info.TypeInfo;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoge85 on 15/12/8.
 */
public class JdtInfo {
    public static final JdtInfo parser(String code, String packageName) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(code.toCharArray());
        CompilationUnit node = (CompilationUnit) parser.createAST(null);

        String sourcePackage = node.getPackage().getName().getFullyQualifiedName();
        sourcePackage = sourcePackage.substring(0, sourcePackage.length() - packageName.length());


        List types = node.types();
        if (types.size() != 1) {
//            throw new AnalyseException("错误的类型，现在只支持一个文件单个类:");
            return null;
        }

        TypeDeclaration type = (TypeDeclaration) types.get(0);

        JdtInfo jdtInfo = new JdtInfo(node, type, sourcePackage, packageName);
        jdtInfo.analyseImports();
        jdtInfo.analyseTypeParameters();
        return jdtInfo;
    }

    protected CompilationUnit node;
    protected TypeDeclaration type;
    protected String name;
    protected String packageName;
//    protected Map<String, QualifiedName> nameMaps = new HashMap<>();

    protected ImportsInfo importsInfo = new ImportsInfo();
    private String sourcePackage;
    private List<String> typeParameters = new ArrayList<>();

    private JdtInfo(CompilationUnit node, TypeDeclaration type, String sourcePackage, String packageName) {
        this.node = node;
        this.type = type;
        this.sourcePackage = sourcePackage;
        this.packageName = packageName;
        this.name = type.getName().getFullyQualifiedName();
    }


    private void analyseTypeParameters() {
        for (Object o : type.typeParameters()) {
            TypeParameter typeParameter = (TypeParameter) o;

            if (typeParameter.modifiers().size() > 0 || typeParameter.typeBounds().size() > 0) {
                throw new AnalyseException("错误的泛型,不支持复杂的泛型");
            }
            String name = typeParameter.getName().getFullyQualifiedName();
            typeParameters.add(name);
        }
    }

    /**
     * 注意查找方式是,如果是不完整名称,先检查是否泛型,在先检查import,在检查lang ,最后是包内!
     * 处理逻辑和javac 不一致,注意
     *
     * @param typeName 类型名称,全面或者不是全名
     * @return 返回类型名称全称
     */
    public String getFullTypeName(Name typeName) {
        return getTypeName(typeName).getFullName();
    }

    public Import getTypeName(Class cls) {
        return newImport(
                cls.getPackage().getName(),
                cls.getSimpleName(), false
        );
    }

    public Import getTypeName(Name typeName) {
        if (typeName instanceof QualifiedName) {
            QualifiedName name = (QualifiedName) typeName;
            String fullyQualifiedName = name.getQualifier().getFullyQualifiedName();
            //处理是否 inside
            return newImport(
                    fullyQualifiedName,
                    name.getName().getFullyQualifiedName(), false
            );
        } else {
            //下面是处理名称不完整的情况
            String name = typeName.getFullyQualifiedName();
            if (typeParameters.contains(name)) {
                return null;
            }

            Import anImport = importsInfo.get(name);
            if (anImport != null) {
                return anImport;
            }

            anImport = Utils.getLangImport(name);
            if (anImport != null) {
                return anImport;
            }

            /**
             * 在 *的包没查找
             */
            anImport = importsInfo.getOnDemandImport(name, sourcePackage);
            if (anImport != null) {
                return anImport;
            }

            return newImport(sourcePackage + packageName, name, false);
        }
    }


    private Import newImport(String packageName, String name, boolean onDemand) {
        boolean isInside = false;
        if (onDemand) {
            packageName = packageName + "." + name;
            name = "*";
        }
        if (packageName.startsWith(sourcePackage)) {
            isInside = true;
            packageName = packageName.substring(sourcePackage.length());
        }
        return new Import(packageName, name, isInside, onDemand);
    }

    /**
     *
     */
    private void analyseImports() {
        List imports = node.imports();
        for (Object importItem : imports) {
            ImportDeclaration importDeclaration = (ImportDeclaration) importItem;
            if (importDeclaration.isStatic()) {
                throw new RuntimeException("不支持静态导入:" + importDeclaration);
            }
            Name name = importDeclaration.getName();
            if (name instanceof QualifiedName) {
                QualifiedName qName = (QualifiedName) name;
                String packageName = qName.getQualifier().getFullyQualifiedName();

                importsInfo.add(
                        newImport(
                                packageName,
                                qName.getName().getFullyQualifiedName(), importDeclaration.isOnDemand()
                        )
                );
            } else {
                throw new RuntimeException("不支持的导入名称:" + name);
            }
        }
    }

    public TypeInfo form(java.lang.reflect.Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (ClassUtils.isPrimitiveOrWrapper(cls)) {
                return TypeInfo.formBaseType(cls.getName(), false);
            } else if (cls.isArray()) {
                TypeInfo typeInfo = form(cls.getComponentType());
                typeInfo.setArray(true);
                return typeInfo;
            } else {
                Import anImport = getTypeName(cls);
                return TypeInfo.formImport(anImport, false);
            }
        } else if (type instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.ParameterizedType paramType = (java.lang.reflect.ParameterizedType) type;
            Class rawType = (Class) paramType.getRawType();

            TypeInfo typeInfo = form(rawType);
            java.lang.reflect.Type[] arguments = paramType.getActualTypeArguments();
            for (java.lang.reflect.Type typeArgument : arguments) {
                TypeInfo typeArgumentInfo = form(typeArgument);
                typeInfo.addArguments(typeArgumentInfo);
            }
            return typeInfo;
        } else if (type instanceof java.lang.reflect.TypeVariable) {
            TypeVariable typeVar = (TypeVariable) type;
            TypeInfo typeInfo = TypeInfo.formGeneric(typeVar.getName(), false);
            return typeInfo;
        }
        throw new AnalyseException("暂时不支持的类型，分析失败:" + type);
    }


    public TypeInfo analyseType(Name typeName) {
        Import anImport = getTypeName(typeName);
        if (anImport == null) {
            return TypeInfo.formGeneric(typeName.getFullyQualifiedName(), false);
        }
        return TypeInfo.formImport(anImport, false);
    }

    public TypeInfo analyseType(Type fieldType) {
        if (fieldType.isPrimitiveType()) {
            PrimitiveType primitiveType = (PrimitiveType) fieldType;
            String name = primitiveType.getPrimitiveTypeCode().toString();
            return TypeInfo.formBaseType(name, primitiveType.isArrayType());
        } else if (fieldType.isSimpleType()) {
            SimpleType simpleType = (SimpleType) fieldType;
            Name name = simpleType.getName();
            Import anImport = getTypeName(name);
            if (anImport == null) {
                return TypeInfo.formGeneric(name.getFullyQualifiedName(), false);
            }
            return TypeInfo.formImport(anImport, fieldType.isArrayType());
        } else if (fieldType.isArrayType()) {
            ArrayType arrayType = (ArrayType) fieldType;
            if (arrayType.getDimensions() > 1) {
                throw new AnalyseException("错误的数量:" + arrayType.getDimensions());
            }

            TypeInfo typeInfo = analyseType(arrayType.getElementType());
            typeInfo.setArray(true);
            return typeInfo;
        } else if (fieldType.isParameterizedType()) {
            ParameterizedType parameterizedType = (ParameterizedType) fieldType;
            Type parameterizedTypeType = parameterizedType.getType();

            TypeInfo typeInfo = analyseType(parameterizedTypeType);
            if (typeInfo != null) {
                List typeArguments = parameterizedType.typeArguments();

                for (Object typeArgument : typeArguments) {
                    TypeInfo typeArgumentInfo = analyseType((Type) typeArgument);
                    typeInfo.addArguments(typeArgumentInfo);
                }
            }
            return typeInfo;
        }
        return null;
    }


    public List<String> getTypeParameters() {
        return typeParameters;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFullName() {
        return packageName + "." + name;
    }


    public CompilationUnit getNode() {
        return node;
    }

    public ImportsInfo getImportsInfo() {
        return importsInfo;
    }

    public TypeDeclaration getType() {
        return type;
    }

    @Override
    public String toString() {
        return "JdtInfo{" +
                "name='" + name + '\'' +
                ",packageName='" + packageName + '\'' +
                '}';
    }
}
