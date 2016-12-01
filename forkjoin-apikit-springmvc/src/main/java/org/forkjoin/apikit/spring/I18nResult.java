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
 * @author zuoge85 on 15/4/21.
 */
public class I18nResult<T> extends Result<T> {
	@JsonIgnore
	private transient String code;
	@JsonIgnore
	private transient Object[] args;

	@JsonIgnore
	private transient List<ObjectError> fields = new ArrayList<>();;

	public static <T> I18nResult<T> create(String code, Object... args) {
		return new I18nResult<>(Result.VALIDATOR, code, args);
	}

	public static <T> I18nResult<T> create(String code, String... args) {
		Object[] objs = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			objs[i] = new DefaultMessageSourceResolvable(args[i]);
		}
		return new I18nResult<>(Result.VALIDATOR, code, objs);
	}

	// @Deprecated
	// public static <T> I18nResult<T> createValidator(String code, String...
	// args) {
	// Object[] objs = new Object[args.length];
	// for (int i = 0; i < args.length; i++) {
	// objs[i] = new DefaultMessageSourceResolvable(args[i]);
	// }
	// return new I18nResult<>(Result.VALIDATOR, code, objs);
	// }
	//
	// @Deprecated
	// public static <T> I18nResult<T> createValidatorObjs(String code,
	// Object... args) {
	// return new I18nResult<>(Result.VALIDATOR, code, args);
	// }

	public static <T> I18nResult<T> of(String objectName, String codes,
			String... args) {
		return new I18nResult<T>(Result.VALIDATOR).addField(objectName, codes,
				args);
	}

	public static <T> I18nResult<T> ofObjs(String objectName, String codes,
			Object... args) {
		return new I18nResult<T>(Result.VALIDATOR).addFieldObjs(objectName,
				codes, args);
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

	public I18nResult<T> addField(String objectName, String code,
			String... args) {
		Object[] objs = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			objs[i] = new DefaultMessageSourceResolvable(args[i]);
		}
		return addFieldObjs(objectName, code, objs);
	}

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
