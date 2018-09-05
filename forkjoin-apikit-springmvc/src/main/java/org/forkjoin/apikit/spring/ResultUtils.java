package org.forkjoin.apikit.spring;

import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.core.ResultField;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author zuoge85 on 15/12/17.
 */
public class ResultUtils {
    public static final String RESULT_ATTRIBUTE_NAME = "result";

    public static void handleI18n(I18nResult result, MessageSourceAccessor messageSourceAccessor) {
        Object[] headers = result.getArgs();
        List<ResultField> serialize = new ArrayList<>();
        List fields = result.getFields();

        for (Object field1 : fields) {
            ObjectError field = (ObjectError) field1;
            String key = field.getObjectName();
            String message = messageSourceAccessor.getMessage(field);
            serialize.add(new ResultField(key, message));
        }

        result.setErrors(serialize);
        if (null != result.getCode()) {
            result.setMessage(messageSourceAccessor.getMessage(result.getCode(), headers));
        }
    }

    public static <T> Result<T> transform(BindingResult bindingResult, MessageSourceAccessor messageSourceAccessor) {
        Result<T> result = Result.createError(Result.VALIDATOR, null);

        StringBuilder sb = new StringBuilder();
        List<ResultField> serialize = new ArrayList<>();

        for (ObjectError error : bindingResult.getAllErrors()) {
            String key = (error instanceof FieldError
                    ? ((FieldError) error).getField()
                    : error.getObjectName());
            String message = messageSourceAccessor.getMessage(error);

            serialize.add(new ResultField(key, message));
            if (sb.length() > 0) {
                sb.append(";");
            }
            sb.append(message);
        }

        String message = messageSourceAccessor.getMessage(
                "server.validator", new Object[]{sb});

        result.setErrors(serialize);
        return result;
    }


    public static Object handler(Map map, MessageSourceAccessor messageSourceAccessor) {
        Object result = map.get(RESULT_ATTRIBUTE_NAME);
        if (result instanceof I18nResult) {
            ResultUtils.handleI18n((I18nResult) result, messageSourceAccessor);
        } else {
            for (Object obj : map.values()) {
                if (obj instanceof BindingResult) {
                    BindingResult bindingResult = (BindingResult) obj;
                    if (bindingResult.hasErrors()) {
                        result = ResultUtils.transform(bindingResult, messageSourceAccessor);
                        break;
                    }
                }
            }
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    public static <T> Result<T> create(Result result) {
        if (result.isSuccess()) {
            throw new RuntimeException("错误的转换!,类型不合适");
        }
        return result;
    }
}
