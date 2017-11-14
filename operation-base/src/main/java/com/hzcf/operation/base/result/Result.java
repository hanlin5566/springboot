package com.hzcf.operation.base.result;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Result<T> extends ResultResponse {



	private T data = null;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String location;

	public Result() {
	}

	public Result(ResponseCode code) {
		this(code, null, null);
	}
	
	public Result(ResponseCode code, String message, T data) {
		super(code, message);
		this.data = data;
	}

	public Result(ResponseCode code, T data) {
		super(code, null);
		this.data = data;
	}

	public Result<T> setResponseCode(ResponseCode code) {
		super.setResponseCode(code);

		return this;
	}

	public Result<T> copyResponseCode(ResultResponse result) {
		super.copyResponseCode(result);
		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T listData() {
		if (data == null) {
			return (T)new ArrayList();
		}
		
		return data;
	}

	public T getData() {
		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;

		return this;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}