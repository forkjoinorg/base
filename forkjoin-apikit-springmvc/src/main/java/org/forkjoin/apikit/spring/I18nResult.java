package org.forkjoin.apikit.spring;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.forkjoin.apikit.core.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 国际化支持结果集
 * 支持 映射到 Result 的验证错误的国际化解析
 *
 * 有2种错误信息， 一个是带字段名称，一个不带字段名称
 * @author zuoge85 on 15/4/21.
 */
public class I18nResult<T> extends Result<T> {
	@JsonIgnore
	private transient String code;
	@JsonIgnore
	private transient Object[] args;

	@JsonIgnore
	private transient List<ObjectError> fields = new ArrayList<>();;

	/**
	 *
	 * @param code 国际化文件的 key
	 * @param args MessageSourceResolvable之类的数组，实现复杂的解析
	 */
	public static <T> I18nResult<T> create(String code, Object... args) {
		return new I18nResult<>(Result.VALIDATOR, code, args);
	}

	/**
	 *
	 * @param code 国际化文件的 key
	 * @param args 嵌套的国家化文件 key
	 */
	public static <T> I18nResult<T> create(String code, String... args) {
		Object[] objs = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			objs[i] = new DefaultMessageSourceResolvable(args[i]);
		}
		return new I18nResult<>(Result.VALIDATOR, code, objs);
	}

	/**
	 *
	 * @param objectName 字段名称
	 * @param code 国际化文件的 key
	 * @param args MessageSourceResolvable之类的数组，实现复杂的解析
	 */
	public static <T> I18nResult<T> ofObjs(String objectName, String code,
										   Object... args) {
		return new I18nResult<T>(Result.VALIDATOR).addFieldObjs(objectName,
				code, args);
	}

	/**
	 *
	 * @param objectName 字段名称
	 * @param code 国际化文件的 key
	 * @param args 嵌套的国家化文件 key
	 */
	public static <T> I18nResult<T> of(String objectName, String code,
			String... args) {
		return new I18nResult<T>(Result.VALIDATOR).addField(objectName, code,
				args);
	}



	public I18nResult() {
	}

	public I18nResult(int status) {
		super(status, null, null);
	}

	public I18nResult(String code, Object... args) {
		this.code = code;
		this.args = args;
	}

	public I18nResult(int status, String code, Object... args) {
		super(status, null, null);
		this.code = code;
		this.args = args;
	}

	public I18nResult(int status, T data, String code, Object... args) {
		super(status, null, data);
		this.code = code;
		this.args = args;
	}

	@JsonIgnore
	@Transient
	public String getCode() {
		return code;
	}

	@JsonIgnore
	@Transient
	public Object[] getArgs() {
		return args;
	}

	@JsonIgnore
	@Transient
	public List<ObjectError> getFields() {
		return fields;
	}


	/**
	 *
	 * @param objectName 字段名称
	 * @param code 国际化文件的 key
	 * @param args 嵌套的国家化文件 key
	 */
	public I18nResult<T> addField(String objectName, String code,
			String... args) {
		Object[] objs = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			objs[i] = new DefaultMessageSourceResolvable(args[i]);
		}
		return addFieldObjs(objectName, code, objs);
	}

	/**
	 *
	 * @param objectName 字段名称
	 * @param code 国际化文件的 code
	 * @param args MessageSourceResolvable之类的数组，实现复杂的解析
	 */
	public I18nResult<T> addFieldObjs(String objectName, String code,
			Object... args) {
		fields.add(new ObjectError(objectName, new String[]{code}, args,
				objectName));
		return this;
	}

	public I18nResult<T> addField(ObjectError objectError) {
		fields.add(objectError);
		return this;
	}

	@Override
	public String toString() {
		return "I18nResult [code=" + code + ", args=" + Arrays.toString(args)
				+ ", fields=" + fields + "]";
	}

}
