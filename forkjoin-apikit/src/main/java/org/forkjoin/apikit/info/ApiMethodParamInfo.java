package org.forkjoin.apikit.info;

/**
 * @author zuoge85 on 15/12/9.
 */
public class ApiMethodParamInfo extends FieldInfo {
    private boolean isPathVariable = false;
    private boolean isFormParam = false;

    public ApiMethodParamInfo(String name, TypeInfo typeInfo) {
        super(name, typeInfo);
    }

    public boolean isFormParam() {
        return isFormParam;
    }

    public boolean isPathVariable() {
        return isPathVariable;
    }

    public void setFormParam(boolean formParam) {
        isFormParam = formParam;
    }

    public void setPathVariable(boolean pathVariable) {
        isPathVariable = pathVariable;
    }
}
