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
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuoge85 on 15/4/18.
 */
public class NoCacheMappingJacksonHttpMessageConverter
        extends
        MappingJackson2HttpMessageConverter implements MessageSourceAware {
    private static final Logger log = LoggerFactory
            .getLogger(AccountHandlerInterceptor.class);

    private MessageSourceAccessor messageAccessor;
    private JsonEncoding encoding = JsonEncoding.UTF8;

    @Override
    @SuppressWarnings("unchecked")
    protected void writeInternal(Object o, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (o instanceof I18nResult) {
            I18nResult r = (I18nResult) o;
            // {"error":"参数验证错误:[密码不能为空!;性别格式错误:^[f|m]$!]!","data":{"password":"密码不能为空!","gender":"性别格式错误:^[f|m]$!"},"status":1}
            Object[] args = r.getArgs();

            Map<String, String> messageMap = new HashMap<>();

            List<ObjectError> fields = r.getFields();
            for (ObjectError field : fields) {
                String key = field.getObjectName();
                String message = messageAccessor.getMessage(field);
                messageMap.put(key, message);
            }

            r.setMsgMap(messageMap);
            if (null != r.getCode()) {
                r.setMsg(messageAccessor.getMessage(r.getCode(), args));
            }
            // {"status":1,"msg":"手机号已经被使用!","data":null}
        }
        JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders()
                .getContentType());
        HttpHeaders headers = outputMessage.getHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, headers.getContentType()
                .toString() + ";charset=" + encoding.getJavaName());
        headers.set("Pragma", "No-cache");
        headers.set("Cache-Control", "no-cache");
        headers.set("Expires", "0");
        super.writeInternal(o, outputMessage);
        if (log.isDebugEnabled()) {
            String serialize = JsonUtils.serialize(o);
            log.debug("响应:{}", serialize.substring(0, Math.min(serialize.length(), 64)));
        }
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
