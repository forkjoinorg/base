package org.forkjoin.apikit.spring;

import org.forkjoin.apikit.core.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;

/**
 * 验证错误！
 *
 * @author zuoge85@gmail.com on 2016/12/6.
 * @see I18nResult
 */
public class I18nValidationException extends RuntimeException {


    private I18nResult i18nResult;

    public I18nValidationException() {
        this.i18nResult = new I18nResult();
    }

    public I18nValidationException(I18nResult i18nResult) {
        this.i18nResult = i18nResult;
    }


    /**
     * @param objectName 字段名称
     * @param code       国际化文件的 key
     * @param args       嵌套的国家化文件 key
     */
    public I18nValidationException addField(String objectName, String code,
                                            String... args) {
        i18nResult.addField(objectName, code, args);
        return this;
    }

    /**
     * @param objectName 字段名称
     * @param code       国际化文件的 code
     * @param args       MessageSourceResolvable之类的数组，实现复杂的解析
     */
    public I18nValidationException addFieldObjs(String objectName, String code,
                                                Object... args) {
        i18nResult.addFieldObjs(objectName, code, args);
        return this;
    }

    public I18nValidationException addField(ObjectError objectError) {
        i18nResult.addField(objectError);
        return this;
    }

    public I18nResult getI18nResult() {
        return i18nResult;
    }



    /**
     * @param objectName 字段名称
     * @param code       国际化文件的 key
     * @param args       MessageSourceResolvable之类的数组，实现复杂的解析
     */
    public static I18nValidationException ofObjs(String objectName, String code,
                                                 Object... args) {
        I18nResult i18nResult = new I18nResult(Result.VALIDATOR).addFieldObjs(objectName, code, args);
        return new I18nValidationException(i18nResult);
    }

    /**
     * @param objectName 字段名称
     * @param code       国际化文件的 key
     * @param args       MessageSourceResolvable之类的数组，实现复杂的解析
     */
    public static I18nValidationException throwOfObjs(String objectName, String code,
                                                      Object... args) throws I18nValidationException {
        throw ofObjs(objectName, code, args);
    }

    /**
     * @param objectName 字段名称
     * @param code       国际化文件的 key
     * @param args       嵌套的国家化文件 key
     */
    public static I18nValidationException of(String objectName, String code,
                                             String... args) {
        I18nResult i18nResult = new I18nResult(Result.VALIDATOR).addField(objectName, code, args);
        return new I18nValidationException(i18nResult);
    }

    /**
     * @param objectName 字段名称
     * @param code       国际化文件的 key
     * @param args       嵌套的国家化文件 key
     */
    public static I18nValidationException throwOf(String objectName, String code,
                                                  String... args) throws I18nValidationException {
        throw of(objectName, code, args);
    }

    /**
     * @param code 国际化文件的 key
     * @param args MessageSourceResolvable之类的数组，实现复杂的解析
     */
    public static I18nValidationException create(String code, Object... args) {
        return new I18nValidationException(new I18nResult<>(Result.VALIDATOR, code, args));
    }

    /**
     * @param code 国际化文件的 key
     * @param args MessageSourceResolvable之类的数组，实现复杂的解析
     */
    public static I18nValidationException throwCreate(String code, Object... args) throws I18nValidationException {
        throw create(code, args);
    }

    /**
     * @param code 国际化文件的 key
     * @param args 嵌套的国家化文件 key
     */
    public static I18nValidationException create(String code, String... args) {
        Object[] objs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            objs[i] = new DefaultMessageSourceResolvable(args[i]);
        }
        return new I18nValidationException(new I18nResult<>(Result.VALIDATOR, code, objs));
    }

    /**
     * @param code 国际化文件的 key
     * @param args 嵌套的国家化文件 key
     */
    public static I18nValidationException throwCreate(String code, String... args) throws I18nValidationException {
        throw create(code, args);
    }
}
