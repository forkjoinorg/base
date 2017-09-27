package org.forkjoin.apikit.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.forkjoin.apikit.core.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zuoge85@gmail.com on 2017/8/14.
 */
@RestController
public class ApiDocController {
    private String apiDataLocation = "classpath:apidoc/api_data.json";
    private String apiProjectLocation = "classpath:apidoc/api_project.json";
    private String fileEncoding = "utf8";

    private JsonNode apiDataNode;
    private JsonNode apiProjectNode;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ObjectMapper objectMapper;

    public ApiDocController() {

    }

    @PostConstruct
    public void init() throws IOException {
        Resource apiDataResource = resourceLoader.getResource(apiDataLocation);
        try (InputStream inputStream = apiDataResource.getInputStream()) {
            String apiDataJson = IOUtils.toString(inputStream, fileEncoding);
            apiDataNode = objectMapper.readTree(apiDataJson);
        }

        Resource apiProjectResource = resourceLoader.getResource(apiProjectLocation);
        try (InputStream inputStream = apiProjectResource.getInputStream()) {
            String apiProjectJson = IOUtils.toString(inputStream, fileEncoding);
            apiProjectNode = objectMapper.readTree(apiProjectJson);
        }
    }


    @RequestMapping(value = "apidoc/api_data", method = RequestMethod.GET)
    @Account(false)
    public JsonNode apiData() throws Exception {
        return apiDataNode;
    }

    @RequestMapping(value = "apidoc/api_project", method = RequestMethod.GET)
    @Account(false)
    public JsonNode apiProject() throws Exception {
        return apiProjectNode;
    }

    public String getApiDataLocation() {
        return apiDataLocation;
    }

    public void setApiDataLocation(String apiDataLocation) {
        this.apiDataLocation = apiDataLocation;
    }

    public String getApiProjectLocation() {
        return apiProjectLocation;
    }

    public void setApiProjectLocation(String apiProjectLocation) {
        this.apiProjectLocation = apiProjectLocation;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public JsonNode getApiDataNode() {
        return apiDataNode;
    }

    public void setApiDataNode(JsonNode apiDataNode) {
        this.apiDataNode = apiDataNode;
    }

    public JsonNode getApiProjectNode() {
        return apiProjectNode;
    }

    public void setApiProjectNode(JsonNode apiProjectNode) {
        this.apiProjectNode = apiProjectNode;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}