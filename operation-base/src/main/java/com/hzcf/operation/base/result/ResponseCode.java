package com.hzcf.operation.base.result;

import com.hzcf.operation.base.enums.EnumType;

/**
 * Create by hanlin on 2017年11月6日
 **/
public enum ResponseCode implements EnumType{
	UNKNOWN(0, "未知"),
	OK(200,"成功"),
	FAILED(100,"失败"),
	ERROR_PARAM(401,"请求参数错误"),
	RESOURCE_NOT_FOUND(404,"请求资源未找到"),
	RESULT_SYSTEM_ERROR(500, "系统内部错误"),
	RESULT_OPERATION_FAILED(2000, "操作错误"),
	;
	private final int code;
    private final String text;

    private ResponseCode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String text() {
        return text;
    }

    public static ResponseCode codeOf(int code) {
        for (ResponseCode value : values()) {
            if (value.code == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid ResponseCode code: " + code);
    }
}
