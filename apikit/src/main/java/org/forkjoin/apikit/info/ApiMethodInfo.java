package org.forkjoin.apikit.info;


import org.eclipse.jdt.core.dom.Javadoc;
import org.forkjoin.api.Account;
import org.forkjoin.api.ActionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoge85 on 15/6/12.
 */
public class ApiMethodInfo {
    private String name;
    private String url;
    private ActionType type = ActionType.GET;
    private boolean account = true;
    /**
     * 账户对象名称
     */
    private String accountParam = Account.PARAM_NAME;

    private ArrayList<ApiMethodParamInfo> params = new ArrayList<>();
//    private AttributeInfo formParam;

    private TypeInfo resultType;
    private JavadocInfo comment;

    private List<AnnotationInfo> annotations = new ArrayList<>();
//    private ArrayList<SingleVariableDeclaration> params = new ArrayList<>();
//    private String id;

    public ApiMethodInfo() {
    }

    public void addParam(ApiMethodParamInfo param) {
        params.add(param);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public TypeInfo getResultType() {
        return resultType;
    }

    public void setResultType(TypeInfo resultType) {
        this.resultType = resultType;
    }

    public void addAnnotation(AnnotationInfo annotation) {
        annotations.add(annotation);
    }

    public List<AnnotationInfo> getAnnotations() {
        return annotations;
    }

    public boolean isAccount() {
        return account;
    }

    public void setAccount(boolean account) {
        this.account = account;
    }

    public void setComment(JavadocInfo comment) {
        this.comment = comment;
    }

    public JavadocInfo getComment() {
        return comment;
    }

    /**
     * 账户对象名称
     */
    public void setAccountParam(String accountParam) {
        this.accountParam = accountParam;
    }


    /**
     * 账户对象名称
     */
    public String getAccountParam() {
        return accountParam;
    }

    public ArrayList<ApiMethodParamInfo> getParams() {
        return params;
    }


    //    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }


    @Override
    public String toString() {
        return "ApiMethodInfo{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", account=" + account +
                ", accountParam='" + accountParam + '\'' +
                ", params=" + params +
                ", resultType=" + resultType +
                ", comment=" + comment +
                ", annotations=" + annotations +
                '}';
    }
}
