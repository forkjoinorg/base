package org.forkjoin.apikit.example;

import org.forkjoin.apikit.client.AbstractHttpClientAdapter;
import org.forkjoin.apikit.example.client.api.AccountApi;
import org.forkjoin.apikit.example.client.api.PageApi;
import org.forkjoin.apikit.example.client.api.TestApi;
import org.forkjoin.apikit.example.client.api.TestNoResultApi;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@Import({TestsContext.class})
@SpringBootTest(classes = ExampleApplication.class)
//@ActiveProfiles("remote")
@ActiveProfiles("mock")
public abstract class BaseControllerTest {

//    protected ApiManager apiManager;

    @Autowired
    protected AbstractHttpClientAdapter httpClientAdapter;

    @Autowired
    protected AccountApi accountApi;

    @Autowired
    protected PageApi pageApi;

    @Autowired
    protected TestApi testApi;

    @Autowired
    protected TestNoResultApi testNoResultApi;

    @Before
    public void init() {
//        apiManager = new ApiManager(httpClientAdapter);
    }
}