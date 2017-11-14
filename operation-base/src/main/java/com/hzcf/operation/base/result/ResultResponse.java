package com.hzcf.operation.base.result;

public class ResultResponse {

	
	private int code = 0;
	
	private String codeText = null;
	
	private String message = null;

    public ResultResponse() {
    	this(ResponseCode.OK);
    }
    
    
    public static ResultResponse init(ResponseCode code) {
    	ResultResponse result = new ResultResponse();
    	result.setResponseCode(code);
    	return result;
    }
    
    public static ResultResponse ok() {
    	return new ResultResponse();
    }
    
    public static ResultResponse ok(String message) {
    	ResultResponse result = new ResultResponse();
    	result.setMessage(message);
    	return result;
    }

    public ResultResponse(ResponseCode code) {
        this(code, null);
    }
    
    public ResultResponse(ResponseCode code, String message) {
        this.code = code.code();
        this.codeText = code.name();
        if (message == null) {
        	this.message = code.text();
        } else {
        	this.message = message;
        }
    }

    public ResultResponse setResponseCode(ResponseCode code) {
        this.code = code.code();
        this.codeText = code.name();
        this.message = code.text();
    	return this;    	
    }

    public ResultResponse copyResponseCode(ResultResponse result) {
        this.code = result.getCode();
        this.codeText = result.getCodeText();
        this.message = result.getMessage();
    	return this;    	
    }
    
    public String getMessage() {
        return message;
    }

    public ResultResponse setMessage(String message) {
        this.message = message;
        return this;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodeText() {
		return codeText;
	}

	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}
	
	public boolean isSuccess() {
		return (this.code == ResponseCode.OK.code());
	}
}