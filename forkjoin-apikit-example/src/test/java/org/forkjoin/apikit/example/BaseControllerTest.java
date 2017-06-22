package org.forkjoin.apikit.example;

import org.forkjoin.apikit.example.client.ApiManager;
import org.forkjoin.apikit.client.AbstractHttpClientAdapter;
import org.junit.After;
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

    protected ApiManager apiManager;

    @Autowired
    protected AbstractHttpClientAdapter httpClientAdapter;

    @Before
    public void init(){
        apiManager = new ApiManager(httpClientAdapter);
    }
}