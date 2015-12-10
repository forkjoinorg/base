package org.forkjoin.apikit.impl;

import org.forkjoin.api.Account;
import org.forkjoin.api.ActionType;
import org.forkjoin.api.Api;
import org.forkjoin.api.ApiMethod;
import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.info.*;
import org.junit.Test;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author zuoge85 on 15/12/9.
 */

public class JdtApiAnalyseTest extends BaseTest {
    @Test
    public void test() throws IOException {
        String code = readCode("api/BaseApi.java");
        Analyse analyse = createAnalyse();
        ApiInfo api = (ApiInfo) analyse.analyse(code, "api");
        assertEquals("BaseApi", api.getName());
        assertEquals("api", api.getPackageName());

        ImportsInfo imports = api.getImports();
        assertEquals(9, imports.getImports().size());


        assertImport(imports.get("Account"), Account.class.getName());
        assertImport(imports.get("ActionType"), ActionType.class.getName());
        assertImport(imports.get("Api"), Api.class.getName());
        assertImport(imports.get("ApiMethod"), ApiMethod.class.getName());
        assertImport(imports.get("TestForm"), "form.TestForm", true);
        assertImport(imports.get("TestObject"), "model.TestObject", true);
        assertImport(imports.get("User"), "model.User", true);
        assertImport(imports.get("PathVariable"), PathVariable.class.getName());
        assertImport(imports.get("Valid"), Valid.class.getName());

        //测试注释
        JavadocInfo comment = api.getComment();
        Map.Entry<String, List<String>> entry = comment.getTags().get(0);

        assertJavadocInfo(api.getComment(), "@author", Collections.singletonList(" zuoge85 on 15/6/11."));

        //测试Api函数
        testBase(api.get("base/"));


        testBaseId(api.get("base/{id}"));

        testBaseUrl(api.get("baseUrl/"));
    }

    private void testBaseUrl(ApiMethodInfo apiMethodInfo) {
        assertEquals("create", apiMethodInfo.getName());
        assertEquals(false, apiMethodInfo.isAccount());
        assertEquals("baseUrl/", apiMethodInfo.getUrl());

        TypeInfo resultType = apiMethodInfo.getResultType();
        assertType(
                resultType, "model.User", true, false, 0
        );
    }

    private void testBaseId(ApiMethodInfo apiMethodInfo) {
        assertEquals("get", apiMethodInfo.getName());
        assertEquals(Account.PARAM_NAME, apiMethodInfo.getAccountParam());
        assertEquals(true, apiMethodInfo.isAccount());
        assertEquals("base/{id}", apiMethodInfo.getUrl());

        //返回值
        TypeInfo resultType = apiMethodInfo.getResultType();

        assertEquals(TypeInfo.Type.VOID, resultType.getType());

        //参数
        ApiMethodParamInfo param = apiMethodInfo.getParams().get(0);

        testApiMethodPathParam(param, "ids", null, 0);

        assertEquals(1, param.getAnnotations().size());
        AnnotationInfo annotationInfo = param.getAnnotations().get(0);
        assertType(
                annotationInfo.getTypeInfo(), PathVariable.class.getName()
        );
        assertEquals(0, annotationInfo.getArgs().size());
    }

    private void testBase(ApiMethodInfo apiMethodInfo) {
        assertEquals("create", apiMethodInfo.getName());
        assertEquals("accountName", apiMethodInfo.getAccountParam());
        assertEquals(true, apiMethodInfo.isAccount());
        assertEquals("base/", apiMethodInfo.getUrl());

        TypeInfo resultType = apiMethodInfo.getResultType();
        assertType(
                resultType, "model.TestObject", true, true, 1
        );
        assertType(
                resultType.getTypeArguments().get(0), "model.User", true, true, 0
        );

        assertJavadocInfo(apiMethodInfo.getComment(), "@param", Arrays.asList("user", " 用户"));

        //测试参数0
        //Valid User user, @Valid TestForm<User> testForm
        ArrayList<ApiMethodParamInfo> params = apiMethodInfo.getParams();
        ApiMethodParamInfo param = params.get(0);
        testApiMethodParam(param, "user", "model.User", 0);

        assertEquals(1, param.getAnnotations().size());
        AnnotationInfo annotationInfo = param.getAnnotations().get(0);
        assertType(
                annotationInfo.getTypeInfo(), Valid.class.getName()
        );
        assertEquals(0, annotationInfo.getArgs().size());

        //测试参数1
        param = params.get(1);
        testApiMethodParam(param, "testForm", "form.TestForm", 1);

        assertEquals(1, param.getAnnotations().size());
        annotationInfo = param.getAnnotations().get(0);
        assertType(
                annotationInfo.getTypeInfo(), Valid.class.getName()
        );
        assertEquals(0, annotationInfo.getArgs().size());
    }

    private void testApiMethodParam(
            ApiMethodParamInfo param, String name,
            String typeName, int typeArguments
    ) {
        assertEquals(param.getName(), name);
        assertType(
                param.getTypeInfo(), typeName, true, false, typeArguments
        );
        assertEquals(param.isFormParam(), true);
        assertEquals(param.isPathVariable(), false);
    }

    private void testApiMethodPathParam(
            ApiMethodParamInfo param, String name,
            String typeName, int typeArguments
    ) {
        assertEquals(param.getName(), name);
        assertType(
                param.getTypeInfo(), typeName, false, true, typeArguments
        );
        assertEquals(param.isFormParam(), false);
        assertEquals(param.isPathVariable(), true);
    }
}
