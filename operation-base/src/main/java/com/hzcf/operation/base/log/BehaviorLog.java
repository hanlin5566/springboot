package com.hzcf.operation.base.log;

import java.util.Date;

/**
 * Create by hanlin on 2017年11月14日
 * 行为日志输出到mongodb 包含  开始时间 接口名称 接口入参 接口回传（数据与状态） 回传时间/用时 规则日志ID 流程日志ID 用户身份证
 **/
public class BehaviorLog{
	/**
	 * 开始时间
	 */
	private Date sTime;
	/**
	 * 接口名称
	 */
	private String interfaceName;
	/**
	 * 接口入参
	 */
	private String param;
	/**
	 * 接口回传
	 */
	private String result;
	/**
	 * 回传时间
	 */
	private Date eTime;
	/**
	 * 耗时
	 */
	private long usedTime;
	/**
	 * 操作用户
	 */
	private String usrToken;
	/**
	 * 访问IP
	 */
	private String requestIP;
	/**
	 * 访问的类名
	 */
	private String className;
	/**
	 * 访问的方法
	 */
	private String methodName;
	/**
	 * 访问的URL
	 */
	private String requestURL;
	/**
	 * 访问的Method
	 */
	private String requestMethod;
	/**
	 * 返回状态
	 */
	private int code;
	
	public BehaviorLog() {
		super();
	}
	public BehaviorLog(Date sTime, String interfaceName, String param, String result, Date eTime, long usedTime,
			String usrToken, String requestIP, String className, String methodName, String requestURL,
			String requestMethod, int code) {
		super();
		this.sTime = sTime;
		this.interfaceName = interfaceName;
		this.param = param;
		this.result = result;
		this.eTime = eTime;
		this.usedTime = usedTime;
		this.usrToken = usrToken;
		this.requestIP = requestIP;
		this.className = className;
		this.methodName = methodName;
		this.requestURL = requestURL;
		this.requestMethod = requestMethod;
		this.code = code;
	}
	public String getRequestIP() {
		return requestIP;
	}
	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Date getsTime() {
		return sTime;
	}
	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date geteTime() {
		return eTime;
	}
	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}
	public long getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(long usedTime) {
		this.usedTime = usedTime;
	}
	public String getUsrToken() {
		return usrToken;
	}
	public void setUsrToken(String usrToken) {
		this.usrToken = usrToken;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
}
