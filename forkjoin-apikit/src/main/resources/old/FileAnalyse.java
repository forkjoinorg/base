package org.forkjoin.apikit.old;


import org.forkjoin.api.Api;
import org.forkjoin.api.ApiMethod;
import org.forkjoin.apikit.oldmodel.ApiInfo;
import org.forkjoin.apikit.oldmodel.ApiMethodInfo;
//import org.forkjoin.spring.annotation.Account;
import org.eclipse.jdt.core.dom.*;
import org.forkjoin.apikit.oldmodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 非线程安全的
 *
 * @author zuoge85
 */
public class FileAnalyse {
    private static final Logger log = LoggerFactory.getLogger(FileAnalyse.class);

    private Config config;
    private CompilationUnit node;
    private Map<String, QualifiedName> nameMaps = new HashMap<>();
    private TypeDeclaration type;
    private String file;

    private String name;
    private ModuleInfo info;

    public FileAnalyse(Config config, String codeString, String file) {
        this.config = config;
        this.file = file;

        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(codeString.toCharArray());
        node = (CompilationUnit) parser.createAST(null);

        List imports = node.imports();
        for (Object importItem : imports) {
            ImportDeclaration importDeclaration = (ImportDeclaration) importItem;

            Name name = importDeclaration.getName();
            if (name instanceof QualifiedName) {
                QualifiedName qName = (QualifiedName) name;
                nameMaps.put(qName.getName().getIdentifier(), qName);
            }
        }
        List types = node.types();
        if (types.size() == 1) {
            type = (TypeDeclaration) types.get(0);
            name = type.getName().getFullyQualifiedName();
        } else {
            throw new RuntimeException("错误的类型，现在只支持一个文件单个类:" + file);
        }
    }

    public boolean isGeneric(String name) {
        if(info instanceof MessageInfo){
            MessageInfo messageInfo = (MessageInfo) info;
            List<TypeParameter> typeParameters = messageInfo.getTypeParameters();
            for (TypeParameter typeParameter : typeParameters) {
                if(typeParameter.getName().getIdentifier().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    public ModuleInfo analyse() {
        if (isApi(type)) {
            log.debug("isApi:{}", type.getName());
            return analyseApi();
        } else if (isMessage(type)) {
            log.debug("isMessage:{}", type.getName());
            return analyseMessage();
        } else {
            log.debug("不支持的java文件:{}", file);
        }
        return null;
    }

    private ModuleInfo analyseApi() {
        ApiInfo apiInfo = new ApiInfo();
        this.info = apiInfo;
        initModuleInfo(apiInfo);
        MethodDeclaration[] methods = type.getMethods();
        for (MethodDeclaration method : methods) {
            List modifiers = method.modifiers();
            for (Object o : modifiers) {
                if (o instanceof Annotation) {
                    Annotation annotation = (Annotation) o;
                    if (equalsType(annotation.getTypeName(), ApiMethod.class)) {
                        ApiMethodInfo apiMethodInfo = analyseMethodInfo(method, annotation);
                        apiInfo.addApiMethod(apiMethodInfo);
                    }
                }
            }
        }
        return apiInfo;
    }

    private ApiMethodInfo analyseMethodInfo(MethodDeclaration method, Annotation annotation) {
//        ApiMethodInfo apiMethodInfo = new ApiMethodInfo();
//        apiMethodInfo.setName(method.getName().getFullyQualifiedName());
//        SupportType returnType = SupportType.from(this, method.getReturnType2());
//        apiMethodInfo.setResultType(returnType);
//        apiMethodInfo.setComment(method.getJavadoc());
////        getTypeName(returnType2.ge)
//
//        if (annotation instanceof NormalAnnotation) {
//            @SuppressWarnings("unchecked")
//            List<MemberValuePair> values = ((NormalAnnotation) annotation).values();
//            for (MemberValuePair pair : values) {
//                String pairName = pair.getName().getFullyQualifiedName();
//                Expression value = pair.getValue();
//                switch (pairName) {
//                    case "value":
//                        String url = StringEscapeUtils.unescapeJava(value.toString());
//                        apiMethodInfo.setUrl(url.substring(1, url.length() - 1));
//                        break;
//                    case "type":
//                        ActionType actionType = ActionType.valueOf(((QualifiedName) value).getName().getFullyQualifiedName());
//                        apiMethodInfo.setType(actionType);
//                        break;
//                }
//            }
//        } else if (annotation instanceof SingleMemberAnnotation) {
//            String value = ((SingleMemberAnnotation) annotation).getValue().toString();
//            apiMethodInfo.setUrl(value);
//        }
//
//        //处理注解
//        List modifiers = method.modifiers();
//        for (Object o : modifiers) {
//            if (o instanceof Annotation && o != annotation) {
//                Annotation methodAnnotation = (Annotation) o;
//                QualifiedName typeName = getTypeName(methodAnnotation.getTypeName());
//
//                apiMethodInfo.addAnnotation(typeName, methodAnnotation);
//                if (typeName.getFullyQualifiedName().equals(Account.class.getName())) {
//                    if (methodAnnotation instanceof SingleMemberAnnotation) {
//                        String value = ((SingleMemberAnnotation) methodAnnotation).getValue().toString();
//                        apiMethodInfo.setAccount(Boolean.valueOf(value));
//                    } else if (methodAnnotation instanceof NormalAnnotation) {
//                        @SuppressWarnings("unchecked")
//                        List<MemberValuePair> values = ((NormalAnnotation) methodAnnotation).values();
//                        for (MemberValuePair pair : values) {
//                            String pairName = pair.getName().getFullyQualifiedName();
//                            String value = pair.getValue().toString();
//                            switch (pairName) {
//                                case "value":
//                                    apiMethodInfo.setAccount(Boolean.valueOf(value));
//                                    break;
//                                case "param":
//                                    if (StringUtils.isNotEmpty(value)) {
//                                        apiMethodInfo.setAccountParam(value.substring(1, value.length() - 1));
//                                    }
//                                    break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if (apiMethodInfo.getAccountParam() != null) {
//            if (!apiMethodInfo.isAccount()) {
//                throw new RuntimeException("不对！需要登录参数的怎么能不需要登录？" + file);
//            }
//        }
//        analyseMethodParamsInfo(apiMethodInfo, method);
//        return apiMethodInfo;
        return null;
    }

    private void analyseMethodParamsInfo(ApiMethodInfo apiMethodInfo, MethodDeclaration method) {
        List parameters = method.parameters();

        for (Object parameter : parameters) {
            SingleVariableDeclaration paramDeclaration = (SingleVariableDeclaration) parameter;
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.setSupportType(SupportType.from(this, paramDeclaration.getType()));

            String paramName = paramDeclaration.getName().getFullyQualifiedName();
            attributeInfo.setName(paramName);

            List modifiers = paramDeclaration.modifiers();
            for (Object o : modifiers) {
                if (o instanceof Annotation) {
                    Annotation annotation = (Annotation) o;
                    attributeInfo.addAnnotation(annotation);

                    QualifiedName typeName = getTypeName(annotation.getTypeName());
                    if (typeName.getFullyQualifiedName().equals(PathVariable.class.getName())) {
                        apiMethodInfo.addPathParam(attributeInfo);
                    } else if (typeName.getFullyQualifiedName().equals(Valid.class.getName())) {
                        if (apiMethodInfo.getFormParam() != null) {
                            throw new RuntimeException("现在只支持一个form," + file + ",method:" + method.getName().getFullyQualifiedName());
                        }
                        apiMethodInfo.setFormParam(attributeInfo);
                    }
                }
            }
            apiMethodInfo.addParam(paramDeclaration);
        }
    }

    private boolean equalsType(Name typeName, Class<?> apiMethodClass) {
        QualifiedName qualifiedName = getTypeName(typeName);
        return apiMethodClass.getName().equals(qualifiedName.toString());
    }

    private MessageInfo analyseMessage() {
        MessageInfo messageInfo = new MessageInfo();
        this.info = messageInfo;
        for (Object o : type.typeParameters()) {
            messageInfo.addTypeParameter((TypeParameter) o);
        }


        initModuleInfo(messageInfo);

        FieldDeclaration[] fields = type.getFields();
        for (FieldDeclaration field : fields) {
            List fragments = field.fragments();
            VariableDeclarationFragment nameFragment = (VariableDeclarationFragment) fragments.get(0);
            String fieldName = nameFragment.getName().getFullyQualifiedName();

            AttributeInfo attributeInfo = new AttributeInfo();
            SupportType supportType = SupportType.from(this, field.getType());
            if (supportType == null) {
                throw new RuntimeException("错误的字段:" + file + ",fieldName:" + fieldName);
            }
            attributeInfo.setSupportType(supportType);
            attributeInfo.setComment(field.getJavadoc());

            attributeInfo.setName(fieldName);

            List modifiers = field.modifiers();
            for (Object o : modifiers) {
                if (o instanceof Annotation) {
                    Annotation annotation = (Annotation) o;
                    attributeInfo.addAnnotation(annotation);
                }
            }
            messageInfo.add(attributeInfo);
        }
        return messageInfo;
    }

    private void initModuleInfo(ModuleInfo moduleInfo) {
        Javadoc javadoc = type.getJavadoc();


        SupportType supportType = SupportType.from(config, name, getPackageName(), AttributeType.OTHER, false);
        moduleInfo.setSupportType(supportType);
        moduleInfo.setComment(javadoc);

        List imports = node.imports();
        for (Object importItem : imports) {
            ImportDeclaration importDeclaration = (ImportDeclaration) importItem;

            SupportType importSupportType = SupportType.from(config, importDeclaration);
            moduleInfo.addImport(importSupportType);
        }
    }

    private boolean isApi(TypeDeclaration type) {
        List modifiers = type.modifiers();
        for (Object o : modifiers) {
            if (o instanceof Annotation) {
                Annotation annotation = (Annotation) o;
                QualifiedName typeName = getTypeName(annotation.getTypeName());
                if (typeName.getFullyQualifiedName().equals(Api.class.getName())) {
                    return type.isInterface();
                }
            }
        }
        return false;
    }

    private boolean isMessage(TypeDeclaration type) {
        List modifiers = type.modifiers();
        for (Object o : modifiers) {
            if (o instanceof Annotation) {
                Annotation annotation = (Annotation) o;
                QualifiedName typeName = getTypeName(annotation.getTypeName());
                if (typeName.getFullyQualifiedName().equals(org.forkjoin.api.Message.class.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public QualifiedName getTypeName(Name typeName) {
        if (typeName instanceof QualifiedName) {
            return (QualifiedName) typeName;
        } else {
            String fullyQualifiedName = typeName.getFullyQualifiedName();
            QualifiedName qualifiedName = nameMaps.get(fullyQualifiedName);
            if (qualifiedName == null) {
                if (typeName.isSimpleName()) {
                    AST ast = typeName.getAST();
                    qualifiedName = formJavaLang((SimpleName) typeName);
                    if (qualifiedName == null) {
                        qualifiedName = ast.newQualifiedName(ast.newName(getPackageName()), ast.newSimpleName(fullyQualifiedName));
                    }
                }
            }
            return qualifiedName;
        }
    }

    public QualifiedName formJavaLang(SimpleName typeName) {
        String fullyQualifiedName = typeName.getFullyQualifiedName();
        AttributeType attributeType = AttributeType.formWrap(fullyQualifiedName);
        if (attributeType != AttributeType.OTHER) {
            AST ast = typeName.getAST();
            return ast.newQualifiedName(ast.newName("java.lang"), ast.newSimpleName(fullyQualifiedName));
        }
        return null;
    }

    private String getPackageName() {
        return node.getPackage().getName().getFullyQualifiedName();
    }

    public Config getConfig() {
        return config;
    }


}
