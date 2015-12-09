package org.forkjoin.apikit.impi;

import org.eclipse.jdt.core.dom.*;
import org.forkjoin.apikit.AnalyseException;
import org.forkjoin.apikit.Utils;
import org.forkjoin.apikit.info.Import;
import org.forkjoin.apikit.info.ImportsInfo;
import org.forkjoin.apikit.info.TypeInfo;
import org.forkjoin.apikit.oldmodel.AttributeType;

import java.util.List;

/**
 * @author zuoge85 on 15/12/8.
 */
public class JdtInfo {
    protected CompilationUnit node;
    protected TypeDeclaration type;
    protected String name;
    protected String packageName;
//    protected Map<String, QualifiedName> nameMaps = new HashMap<>();

    protected ImportsInfo importsInfo = new ImportsInfo();

    public JdtInfo(String code, String packageName) {
        this.packageName = packageName;
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(code.toCharArray());
        node = (CompilationUnit) parser.createAST(null);


        analyseImports();

        List types = node.types();
        if (types.size() == 1) {
            type = (TypeDeclaration) types.get(0);
            name = type.getName().getFullyQualifiedName();
        } else {
            throw new AnalyseException("错误的类型，现在只支持一个文件单个类:");
        }
    }

    /**
     * 注意查找方式是,如果是不完整名称,那么先检查import,在检查lang ,最后是包内!
     * 处理逻辑和javac 不一致,注意
     *
     * @param typeName 类型名称,全面或者不是全名
     * @return 返回类型名称全称
     */
    public String getFullTypeName(Name typeName) {
        return getTypeName(typeName).getFullName();
    }

    public Import getTypeName(Name typeName) {
        if (typeName instanceof QualifiedName) {
            QualifiedName name = (QualifiedName) typeName;
            return new Import(
                    name.getQualifier().getFullyQualifiedName(),
                    name.getName().getFullyQualifiedName()
            );
        } else {
            //下面是处理名称不完整的情况
            String name = typeName.getFullyQualifiedName();
            Import anImport = importsInfo.get(name);

            if (anImport == null) {
                anImport = Utils.getLangImport(name);
                if (anImport == null) {
                    return new Import(packageName, name);
                }
            }
            return anImport;
        }
    }


    /**
     * TODO 处理 api 包内的类会出问题
     */
    private void analyseImports() {
        List imports = node.imports();
        for (Object importItem : imports) {
            ImportDeclaration importDeclaration = (ImportDeclaration) importItem;
            if (importDeclaration.isStatic()) {
                throw new RuntimeException("不支持静态导入");
            }

            Name name = importDeclaration.getName();
            if (name instanceof QualifiedName) {
                QualifiedName qName = (QualifiedName) name;
                importsInfo.add(
                        qName.getQualifier().getFullyQualifiedName(),
                        qName.getName().getFullyQualifiedName()
                );
            } else {
                throw new RuntimeException("不支持的导入名称:" + name);
            }
        }
    }


    public TypeInfo analyseType(Name typeName) {
        Import anImport = getTypeName(typeName);
        return TypeInfo.formImport(anImport, false);
    }

    public TypeInfo analyseType(Type fieldType) {
        if (fieldType.isPrimitiveType()) {
            PrimitiveType primitiveType = (PrimitiveType) fieldType;
            String name = primitiveType.getPrimitiveTypeCode().toString();
            return TypeInfo.formBaseType(name, primitiveType.isArrayType());
        } else if (fieldType.isSimpleType()) {
            SimpleType simpleType = (SimpleType) fieldType;
            Import anImport = getTypeName(simpleType.getName());
            return TypeInfo.formImport(anImport, fieldType.isArrayType());
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
        }
        return null;
    }

//    public static SupportType from(
//            Config config, String name, String packageName,
//            AttributeType type, boolean isArray
//    ) {
//        boolean isApiType = false;
//        if (packageName != null) {
//            String rootPackage = config.getRootPackage();
//            if (packageName.startsWith(rootPackage)) {
//                isApiType = true;
//                packageName = packageName.substring(rootPackage.length());
//                if (packageName.startsWith(".")) {
//                    packageName = packageName.substring(1);
//                }
//            }
//        }
//        return new SupportType(type, packageName, name, isArray, isApiType);
//    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
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
