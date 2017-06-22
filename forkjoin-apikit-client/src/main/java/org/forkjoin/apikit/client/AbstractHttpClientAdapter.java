package org.forkjoin.apikit.client;

import org.forkjoin.apikit.client.core.JsonConvert;
import org.forkjoin.apikit.client.core.TypeConvert;
import org.forkjoin.apikit.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * @author zuoge85 on 15/6/23.
 */
public abstract class AbstractHttpClientAdapter implements HttpClientAdapter {
    protected static final Logger log = LoggerFactory.getLogger(AbstractHttpClientAdapter.class);

    public static final String ENCODING = "UTF-8";
    public static final Charset CHARSET = Charset.forName(ENCODING);

    protected final String serverUrl;
    protected String accountToken;
    protected String suffix;
    private TypeConvert typeConvert;
    private JsonConvert jsonConvert;

    public AbstractHttpClientAdapter(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public AbstractHttpClientAdapter(String serverUrl, TypeConvert typeConvert, JsonConvert jsonConvert) {
        this.serverUrl = serverUrl;
        this.typeConvert = typeConvert;
        this.jsonConvert = jsonConvert;
    }

    protected String createUrl(String uri) {
        return suffix == null ? serverUrl + uri : serverUrl + uri + "." + suffix;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getAccountToken() {
        return accountToken;
    }

    protected String toString(Object entry) {
        return typeConvert.convert(entry);
    }

    protected <R extends Result<T>, T> R handlerResult(Type type, boolean isSuccess, String json, Exception ex) {
        if (isSuccess) {
            log.debug("解析json:{},type:{}", json, type);
            return jsonConvert.deserialize(json, type);
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<R> rawType = (Class<R>) parameterizedType.getRawType();
            try {
                R result = rawType.newInstance();
                result.setException(ex);
                result.setStatus(Result.ERROR);
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
