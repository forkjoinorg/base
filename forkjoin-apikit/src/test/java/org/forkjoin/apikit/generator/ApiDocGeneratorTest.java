package org.forkjoin.apikit.generator;

import org.forkjoin.apikit.Analyse;
import org.forkjoin.apikit.Context;
import org.forkjoin.apikit.generator.apidoc.ApiDocApi;
import org.forkjoin.apikit.impl.BaseTest;
import org.forkjoin.apikit.info.ApiInfo;
import org.forkjoin.apikit.info.ModuleInfo;
import org.junit.Test;

import java.util.List;

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

        apiDocGenerator.generate(context);

        List<ApiDocApi> apis = apiDocGenerator.getApis();
        ApiDocApi create = apis.stream().filter(r -> r.getName().equals("create")).findAny().get();
        assertNotNull(create);

        ApiDocApi.Field genericField = create.getParameter().getFields()
                .get("Parameter")
                .stream().filter(r -> r.getField().equals("generic")).findAny().get();


        assertNotNull(apis);
    }
}