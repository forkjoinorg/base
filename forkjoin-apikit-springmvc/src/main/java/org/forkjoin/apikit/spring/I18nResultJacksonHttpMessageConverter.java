package org.forkjoin.apikit.spring;

import com.fasterxml.jackson.core.JsonEncoding;
import org.forkjoin.apikit.spring.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

/**
 * @author zuoge85 on 15/4/18.
 */
public class I18nResultJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter implements MessageSourceAware {
    private static final Logger log = LoggerFactory.getLogger(AccountHandlerInterceptor.class);

    private MessageSourceAccessor messageAccessor;
    private JsonEncoding encoding = JsonEncoding.UTF8;

    @Override
    @SuppressWarnings("unchecked")
    protected void writeInternal(Object o, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (o instanceof I18nResult) {
            ResultUtils.handleI18n((I18nResult) o, messageAccessor);
            // {"status":1,"msg":"手机号已经被使用!","data":null}
        }
        JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders()
                .getContentType());
        HttpHeaders headers = outputMessage.getHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, headers.getContentType()
                .toString() + ";charset=" + encoding.getJavaName());
//        headers.set("Pragma", "No-cache");
//        headers.set("Cache-Control", "no-cache");
//        headers.set("Expires", "0");
        super.writeInternal(o, outputMessage);
        if (log.isDebugEnabled()) {
            String serialize = JsonUtils.serialize(o);
            log.debug("响应:{}", serialize.substring(0, Math.min(serialize.length(), 64)));
        }
    }


    protected boolean canWrite(MediaType mediaType) {
        if (mediaType == null || MediaType.ALL.equals(mediaType)) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.isCompatibleWith(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageAccessor = new MessageSourceAccessor(messageSource);
    }

    public void setEncoding(JsonEncoding encoding) {
        this.encoding = encoding;
    }

    public JsonEncoding getEncoding() {
        return encoding;
    }

    protected JsonEncoding getJsonEncoding(MediaType contentType) {
        return encoding;
    }
}
