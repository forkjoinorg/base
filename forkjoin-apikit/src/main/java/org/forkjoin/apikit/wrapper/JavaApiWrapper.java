package org.forkjoin.apikit.wrapper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.ApiMethodInfo;
import org.forkjoin.apikit.info.ApiMethodParamInfo;
import org.forkjoin.apikit.info.TypeInfo;
import org.forkjoin.apikit.utils.CommentUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author zuoge85 on 15/6/14.
 */
public class JavaApiWrapper extends JavaWrapper<ApiInfo> {
    private String version;

    public JavaApiWrapper(Context context, ApiInfo moduleInfo, String rootPackage) {
        super(context, moduleInfo, rootPackage);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String result(ApiMethodInfo method) {
        StringBuilder sb = new StringBuilder();
        TypeInfo resultType = method.getResultType();
        sb.append(toJavaTypeString(resultType, true, true));
        return sb.toString();
    }

    public String resultData(ApiMethodInfo method) {
        StringBuilder sb = new StringBuilder();
        TypeInfo resultType = method.getResultDataType();
        sb.append(toJavaTypeString(resultType, true, true));
        return sb.toString();
    }

    public String resultClass(ApiMethodInfo method) {
        StringBuilder sb = new StringBuilder();
        TypeInfo resultType = method.getResultType();
        sb.append(toJavaTypeString(resultType, false, false, false));
        sb.append(".class");
        return sb.toString();
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
        Map<String, String> stringStringMap = CommentUtils.commentToMap(method.getComment());

        ArrayList<ApiMethodParamInfo> params = method.getParams();
        for (ApiMethodParamInfo attributeInfo : params) {
            if (attributeInfo.isPathVariable()) {
                String name = attributeInfo.getName();
                String txt = stringStringMap.get(name);
                sb.append(start).append("<li><b>PathVariable:</b> ")
                        .append(
                                StringEscapeUtils.escapeHtml4(
                                        toJavaTypeString(attributeInfo.getTypeInfo(), false, true)
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
            } else {
                sb.append(start).append("<li><b>Form:</b>")
                        .append(
                                StringEscapeUtils.escapeHtml4(
                                        toJavaTypeString(attributeInfo.getTypeInfo(), false, true)
                                )
                        )
                        .append("")
                        .append(method.getName())
                        .append("</li>\n");
            }
        }

        String returnType = toJavaTypeString(method.getResultType(), false, true);

        sb.append(start).append("<li><b>Model:</b> ").append("").append(
                StringEscapeUtils.escapeHtml4(returnType)
        ).append("")
                .append("</li>\n");

        if (method.isAccount()) {
            sb.append(start).append("<li>需要登录</li>\n");
        }


        sb.append(start).append("</ul>\n").append(start).append("</div>\n");

        for (ApiMethodParamInfo attributeInfo : method.getParams()) {
            if (attributeInfo.isPathVariable()) {
                String name = attributeInfo.getName();
                String txt = stringStringMap.get(name);
                if (StringUtils.isNotEmpty(txt)) {
                    sb.append(start).append("@param ")
                            .append(name)
                            .append(" ")
                            .append(txt.replace("\n", ""))
                            .append("\n");
                }
            } else {
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
        }

        if (method.getResultType().getType() != TypeInfo.Type.VOID) {
            sb.append(start).append("@see ").append(
                    StringEscapeUtils.escapeHtml4(
                            toJavaTypeString(method.getResultType(), false, true)
                    )
            ).append("\n");
        }

        for (ApiMethodParamInfo attributeInfo : method.getParams()) {
            if (attributeInfo.getTypeInfo().getType() != TypeInfo.Type.VOID) {
                if (attributeInfo.isFormParam() || attributeInfo.isPathVariable()) {
                    sb.append(start).append("@see ").append(
                            StringEscapeUtils.escapeHtml4(toJavaTypeString(attributeInfo.getTypeInfo(), false, true))
                    ).append("\n");
                }
            }
        }

        if (sb.length() > 0) {
            sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }


    public String params(ApiMethodInfo method) {
        return params(method, true);
    }

    public String params(ApiMethodInfo method, boolean isAnnotation) {
        StringBuilder sb = new StringBuilder();
        ArrayList<ApiMethodParamInfo> params = method.getParams();
        for (int i = 0; i < params.size(); i++) {
            ApiMethodParamInfo attributeInfo = params.get(i);
            if (i > 0) {
                sb.append(", ");
            }
            if (isAnnotation) {
                if (attributeInfo.isPathVariable()) {
                    sb.append("@").append(PathVariable.class.getSimpleName()).append(" ");
                }
                if (attributeInfo.isFormParam()) {
                    sb.append("@").append(Valid.class.getSimpleName()).append(" ");
                }
            }
            sb.append(toJavaTypeString(attributeInfo.getTypeInfo(), false, true));
            sb.append(' ');
            sb.append(attributeInfo.getName());
        }
        return sb.toString();
    }

    public String args(ApiMethodInfo method, boolean isAnnotation) {
        StringBuilder sb = new StringBuilder();

        ArrayList<ApiMethodParamInfo> params = method.getParams();
        for (int i = 0; i < params.size(); i++) {
            ApiMethodParamInfo attributeInfo = params.get(i);
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(attributeInfo.getName());
        }
        return sb.toString();
    }

    public String resultTypeString(ApiMethodInfo method, String start) {
        StringBuilder sb = new StringBuilder(start);
        sb.append("private static final ApiType _").append(method.getIndex()).append("Type = ApiUtils.type("+method.getResultWrappedType().getName()+".class, ");
        resultTypeString(sb, method.getResultType());
        sb.append(");");
        return sb.toString();
    }

    // private static final ApiType _0Type = ApiUtils.type(Result.class,  ApiUtils.type(java.util.ArrayList.class));
    //Result<AppModel[]>
    private void resultTypeString(StringBuilder sb, TypeInfo resultType) {
        if (resultType.isArray()) {
            sb.append(" ApiUtils.type(java.util.ArrayList.class, ").append(toJavaTypeString(resultType, true, false, false)).append(".class");
        } else {
            sb.append(" ApiUtils.type(").append(toJavaTypeString(resultType, true, false, false)).append(".class");//
        }
        if (resultType.getTypeArguments().isEmpty()) {
            sb.append(")");
        } else {
            for (TypeInfo typeArgument : resultType.getTypeArguments()) {
                sb.append(",");
                resultTypeString(sb, typeArgument);
            }
            sb.append(")");
        }
    }
}
