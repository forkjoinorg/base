package org.forkjoin.apikit.model;


import com.forkjoin.api.ActionType;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author zuoge85 on 15/6/12.
 */
public class ApiMethodInfo {
    private String name;
    private String url;
    private ActionType type = ActionType.GET;
    private boolean account = true;
    private String accountParam = null;

    private ArrayList<AttributeInfo> pathParams = new ArrayList<>();
    private AttributeInfo formParam;

    private SupportType resultType;
    private Javadoc comment;

    public ApiMethodInfo() {
    }

    private LinkedHashMap<QualifiedName, Annotation> annotationsMap = new LinkedHashMap<>();

    private ArrayList<SingleVariableDeclaration> params = new ArrayList<>();
    private String id;

    public void addPathParam(AttributeInfo attr) {
        pathParams.add(attr);
    }

    public void setFormParam(AttributeInfo formParam) {
        this.formParam = formParam;
    }

    public AttributeInfo getFormParam() {
        return formParam;
    }

    public ArrayList<AttributeInfo> getPathParams() {
        return pathParams;
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

    public SupportType getResultType() {
        return resultType;
    }

    public void setResultType(SupportType resultType) {
        this.resultType = resultType;
    }

    public void addAnnotation(QualifiedName typeName, Annotation annotation) {
        annotationsMap.put(typeName, annotation);
    }

    public LinkedHashMap<QualifiedName, Annotation> getAnnotationsMap() {
        return annotationsMap;
    }

    public boolean isAccount() {
        return account;
    }

    public void setAccount(boolean account) {
        this.account = account;
    }

    public void setComment(Javadoc comment) {
        this.comment = comment;
    }

    public Javadoc getComment() {
        return comment;
    }

    public ArrayList<SingleVariableDeclaration> getParams() {
        return params;
    }

    public void addParam(SingleVariableDeclaration param) {
        params.add(param);
    }

    public void setAccountParam(String accountParam) {
        this.accountParam = accountParam;
    }

    public String getAccountParam() {
        return accountParam;
    }

    @Override
    public String toString() {
        return "ApiMethodInfo{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", account=" + account +
                ", accountParam='" + accountParam + '\'' +
                ", pathParams=" + pathParams +
                ", formParam=" + formParam +
                ", resultType=" + resultType +
                ", comment=" + comment +
                ", annotationsMap=" + annotationsMap +
                ", params=" + params +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
