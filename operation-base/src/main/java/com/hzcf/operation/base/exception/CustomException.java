package com.hzcf.operation.base.exception;

import com.hzcf.operation.base.result.ResponseCode;

/**
 * Create by hanlin on 2017年11月16日
 **/
public class CustomException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private ResponseCode code;
	
	public CustomException(Exception e) {
		super(e);
	}

	public CustomException(ResponseCode code,String message) {
		super(message);
		this.code = code;
	}

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
	}
}
