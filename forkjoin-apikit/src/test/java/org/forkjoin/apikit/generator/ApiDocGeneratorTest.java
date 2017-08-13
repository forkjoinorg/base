package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.generator.apidoc.ApiDocApi;
import org.forkjoin.apikit.impl.BaseTest;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author zuoge85@gmail.com on 2017/8/2.
 */
public class ApiDocGeneratorTest extends BaseTest {
    @Test
    public void generateApi() throws Exception {
        Context context = new Context();
        String apiCode = readCode("api/DocApiController.java");
        String testFormCode = readCode("api/form/TestForm.java");
        String testObjectCode = readCode("api/model/TestObject.java");
        String userCode = readCode("api/model/User.java");


        Analyse analyse = createAnalyse();
        ApiInfo api = (ApiInfo) analyse.analyse(apiCode, "api");
        assertNotNull(api);
        context.add(api);

        ModuleInfo testFormModule = analyse.analyse(testFormCode, "api.form");
        ModuleInfo testObjectModule = analyse.analyse(testObjectCode, "api.model");
        ModuleInfo userModule = analyse.analyse(userCode, "api.model");

        assertNotNull(testFormModule);
        context.add(testFormModule);

        assertNotNull(testObjectModule);
        context.add(testObjectModule);

        assertNotNull(userModule);
        context.add(userModule);


        ApiDocGenerator apiDocGenerator = new ApiDocGenerator();

        apiDocGenerator.setAmd(true);
        apiDocGenerator.generate(context);

        List<ApiDocApi> apis = apiDocGenerator.getApis();

        //测试数量
        assertEquals(apis.size(), 11);

        ApiDocApi loginApiDoc = apis.stream().filter(r -> r.getName().equals("login")).findAny().get();
        assertEquals(loginApiDoc.getTitle(), "后台管理登录接口");

        //测试login 接口解析结果
        //测试返回值
        List<ApiDocApi.Field> loginResultFields = getResultFields(loginApiDoc);

        ApiDocApi.Field dataField = loginResultFields.stream().filter(getRegDates("data")).findAny().get();

        assertNotNull(dataField);
        assertEquals(dataField.getType(), "String");
        assertEquals(dataField.getDescription(), "token登录令牌");


        List<ApiDocApi.Field> loginParamsFields = getParamsFields(loginApiDoc);

        //测试数组
        ApiDocApi.Field regDatesField = loginParamsFields.stream().filter(getRegDates("regDates")).findAny().get();
        assertEquals(regDatesField.getType(), "Date[]");

        ApiDocApi.Field regDateArrayField = loginParamsFields
                .stream().filter(getRegDates("regDateArray")).findAny().get();
        assertEquals(regDateArrayField.getType(), "Date[]");


//        ApiDocApi create = apis.stream().filter(r -> r.getName().equals("create")).findAny().get();
//        assertNotNull(create);

//        ApiDocApi.Field genericField = create.getParameter().getFields()
//                .get("Parameter")
//                .stream().filter(r -> r.getField().equals("generic")).findAny().get();


        assertNotNull(apis);
    }

    private List<ApiDocApi.Field> getResultFields(ApiDocApi api) {
        return api.getSuccess().getFields().values().stream().findAny().get();
    }

    private List<ApiDocApi.Field> getParamsFields(ApiDocApi api) {
        return api.getParameter().getFields().values().stream().findAny().get();
    }

    private Predicate<ApiDocApi.Field> getRegDates(String fieldName) {
        return r -> r.getField().equals(fieldName);
    }
}