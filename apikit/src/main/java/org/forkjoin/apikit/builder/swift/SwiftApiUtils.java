package org.forkjoin.apikit.builder.swift;

import org.forkjoin.apikit.old.Config;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.oldmodel.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/14.
 */
public class SwiftApiUtils extends SwiftUtils {
    private String messagePackage;

    public SwiftApiUtils(Config config, ModelInfo modelInfo, ApiInfo apiInfo, String rootPackage, String messagePackage) {
        super(config, modelInfo, apiInfo, rootPackage);
        this.messagePackage = messagePackage;
    }

    public String result(ApiMethodInfo method) {
        StringBuilder sb = new StringBuilder();
        SupportType resultType = method.getResultType();
        sb.append(resultType.getNewJavaTypeString(true, false));
        return sb.toString();
    }

    public String resultClass(ApiMethodInfo method) {
        StringBuilder sb = new StringBuilder();
        SupportType resultType = method.getResultType();
        sb.append(resultType.getNewJavaTypeString(true, false, false));
        sb.append(".class");
        return sb.toString();
    }

    @Override
    public void initImport() {
        for (SupportType supportType : moduleInfo.getImports()) {
            if (supportType.isApiType()) {
                String packageName = supportType.getPackageName();
                if (StringUtils.isEmpty(packageName)) {
                    addImport(messagePackage + "." + supportType.getName());
                } else {
                    addImport(messagePackage + "." + packageName + "." + supportType.getName());
                }
            } else {
                String fullName = supportType.getPackageName() + "." + supportType.getName();
                if (!exclude(fullName)) {
                    addImport(fullName);
                }
            }
        }
    }

    /**
     *
     */
    public String requestComment(ApiMethodInfo method, String start) {
        StringBuilder sb = new StringBuilder(start);
        sb.append("<div class='http-info'>http 说明：<b>Api Url:</b> <b>")
                .append(method.getUrl())
                .append("</b>\n")
                .append(start)
                .append("<ul>\n");

//        sb.append(start).append("<li><b>Method:</b> ")
//                .append(method.getType().toMethod())
//                .append("</li>\n");
        Map<String, String> stringStringMap = commentToMap(method.getComment());

        ArrayList<AttributeInfo> pathParams = method.getPathParams();
        for (AttributeInfo attributeInfo : pathParams) {

            String name = attributeInfo.getName();
            String txt = stringStringMap.get(name);
            sb.append(start).append("<li><b>PathVariable:</b> ")
                    .append(
                            StringEscapeUtils.escapeHtml4(
                                    attributeInfo.getSupportType().getNewJavaTypeString(false, false)
                            )
                    )
                    .append(" ")
                    .append(attributeInfo.getName());

            if (StringUtils.isNotEmpty(txt)) {
                sb.append(" ");
                sb.append("<span>");
                sb.append(txt);
                sb.append("</span>");
            }
            sb.append("</li>\n");
        }

        if (method.getFormParam() != null) {
            sb.append(start).append("<li><b>Form:</b>")
                    .append(
                            StringEscapeUtils.escapeHtml4(
                                    method.getFormParam().getSupportType().getNewJavaTypeString(false, false)
                            )
                    )
                    .append("")
                    .append(method.getName())
                    .append("</li>\n");
        }

        String returnType = method.getResultType().getNewJavaTypeString(false, false);

        sb.append(start).append("<li><b>Model:</b> ").append("").append(
                StringEscapeUtils.escapeHtml4(returnType)
        ).append("")
                .append("</li>\n");

        if (method.isAccount()) {
            sb.append(start).append("<li>需要登录</li>\n");
        }


        sb.append(start).append("</ul>\n").append(start).append("</div>\n");


        for (AttributeInfo attributeInfo : pathParams) {
            String name = attributeInfo.getName();
            String txt = stringStringMap.get(name);
            if (StringUtils.isNotEmpty(txt)) {
                sb.append(start).append("@param ")
                        .append(name)
                        .append(" ")
                        .append(txt.replace("\n", ""))
                        .append("\n");
            }
        }
        if (method.getFormParam() != null) {
            String name = method.getFormParam().getName();
            String txt = stringStringMap.get(name);
            if (StringUtils.isNotEmpty(txt)) {
                sb.append(start).append("@param ")
                        .append(name)
                        .append(" ")
                        .append(txt.replace("\n", ""))
                        .append("\n");
            }
        }


        if (!returnType.equals("Void")) {
            sb.append(start).append("@see ").append(
                    StringEscapeUtils.escapeHtml4(method.getResultType().getNewJavaTypeString(true, false, false))
            ).append("\n");
        }

        if (method.getFormParam() != null) {
            sb.append(start).append("@see ").append(
                    StringEscapeUtils.escapeHtml4(method.getFormParam().getSupportType().getNewJavaTypeString(true, false, false))
            ).append("\n");
        }


        if (sb.length() > 0) {
            sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    public String asyncParams(ApiMethodInfo method) {
        String params = params(method);
        if (params.length() > 0) {
            params += ", ";
        }
        String swiftTypeString = method.getResultType().getSwiftTypeString(config.getSwiftClientClassPrefix(), true);
        return params + "call _call:(Result<" + swiftTypeString + ">)->Void ";
    }

    public String apiRequestParamGeneric(ApiMethodInfo method) {
        //<Bool,BaseApiObject>
        SupportType resultType = method.getResultType();
        String swiftTypeString = resultType.getSwiftTypeString(config.getSwiftClientClassPrefix(), true);
        String type;
        if(resultType.isOtherType() && !resultType.getName().equals("Void")){
            type = swiftTypeString;
        }else{
            type = "BaseApiObject";
        }
        return "<" + swiftTypeString + ", " + type + ">";
    }


    public String params(ApiMethodInfo method) {
        StringBuilder sb = new StringBuilder();
        ArrayList<AttributeInfo> pathParams = method.getPathParams();

        for (int i = 0; i < pathParams.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            AttributeInfo param = pathParams.get(i);
//            sb.append("_ ");
            sb.append(param.getName());
            sb.append(':');
            sb.append(param.getSupportType().getSwiftTypeString(config.getSwiftClientClassPrefix(), true));
        }

        AttributeInfo formParam = method.getFormParam();
        if (formParam != null) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
//            sb.append("_ ");
            sb.append(formParam.getName());
            sb.append(':');
            sb.append(formParam.getSupportType().getSwiftTypeString(config.getSwiftClientClassPrefix(), true));
        }
        return sb.toString();
    }

    public String args(ApiMethodInfo method, boolean isAnnotation) {
        StringBuilder sb = new StringBuilder();
        ArrayList<AttributeInfo> pathParams = method.getPathParams();

        for (int i = 0; i < pathParams.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            AttributeInfo param = pathParams.get(i);
            sb.append(param.getName());
        }

        AttributeInfo formParam = method.getFormParam();
        if (formParam != null) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(formParam.getName());
        }
        return sb.toString();
    }
}
