package org.forkjoin.apikit.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author zuoge85 on 15/12/17.
 */
public class ResultMappingJackson2JsonView extends MappingJackson2JsonView {
    protected void writeContent(OutputStream stream, Object object)
            throws IOException {
        JsonGenerator generator = this.getObjectMapper()
                .getFactory().createGenerator(stream, this.getEncoding());

        writePrefix(generator, object);
        Class<?> serializationView = null;
        Object value = object;

        if (value instanceof MappingJacksonValue) {
            MappingJacksonValue container = (MappingJacksonValue) value;
            value = container.getValue();
            serializationView = container.getSerializationView();
        }
        if (serializationView != null) {
            this.getObjectMapper().writerWithView(serializationView).writeValue(generator, value);
        } else {
            if (value instanceof Map) {
                Map map = (Map) value;
                Object result = ResultUtils.handler(map, getMessageSourceAccessor());
                this.getObjectMapper().writeValue(generator, result);
            } else {
                this.getObjectMapper().writeValue(generator, value);
            }

        }
        writeSuffix(generator, object);
        generator.flush();
    }

    @Override
    protected Object filterModel(Map<String, Object> model) {
        return model;
    }
}
