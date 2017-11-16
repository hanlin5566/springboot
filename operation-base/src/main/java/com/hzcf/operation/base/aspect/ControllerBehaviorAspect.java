package com.hzcf.operation.base.aspect;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hzcf.operation.base.exception.CustomException;
import com.hzcf.operation.base.log.BehaviorLog;
import com.hzcf.operation.base.result.ResponseCode;
import com.hzcf.operation.base.util.JsonUtils;

/**
 * Create by hanlin on 2017年11月14日 基于AOP记录controller的行为日志
 **/
@Aspect
@Component
// 加载顺序 需要保证在 spring的事物之前执行，才会进入@AfterThrowing
@Order(1)
public class ControllerBehaviorAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * ~第一个 * 代表任意修饰符及任意返回值.
	 * ~第二个 * 任意包名
	 * ~第三个 * 代表任意方法. 
	 * ~第四个 * 定义子包 
	 * ~第五个(..) 任意方法 ~ ..匹配任意数量的参数.
	 * 定义拦截规则：拦截com.hzcf.operation.controller包下面的所有类中，有@RequestMapping注解的方法。
	 */
	@Pointcut("execution(* com.hzcf.operation.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void behaviorLogAspect() {
	}


	/**
	 * 拦截器具体实现
	 * 
	 * @param joinPoint
	 * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
	 * @throws Throwable 
	 */
	@Around("behaviorLogAspect()") // 指定拦截器规则；也可以直接把"execution(*// com.xjj………)"写进这里
	public Object Interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		Date sTime = new Date();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		String requestURL = request.getRequestURL().toString();
		String requestMethod = request.getMethod();
		String requestIP = request.getRemoteAddr();
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String interfaceName = className +"."+ methodName;
		String errorMsg = String.format("错误日志-访问[ %s ]接口", requestURL);
		String infoMsg = String.format("行为日志-访问[ %s ]接口", requestURL);
		String slowMsg = String.format("慢日志-访问[ %s ]接口", requestURL);
		int code = ResponseCode.UNKNOWN.code();
		//TODO:userToken未判断，在记录行为前需要再判断userToken是否为空。
		String usrToken = "";
		List<String> params = new ArrayList<String>();
		Object ret = null;
		//解析参数
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof Map<?, ?>) {
				// 提取方法中的MAP参数，用于记录进日志中
				params.add(JsonUtils.toJSONString(arg));
			} else if (arg instanceof HttpServletRequest) {
				HttpServletRequest requests = (HttpServletRequest) arg;
				// 获取query string 或 posted form data参数
				Map<String, String[]> paramMap = requests.getParameterMap();
				params.add(JsonUtils.toJSONString(paramMap));
			} else if (arg instanceof HttpServletResponse) {
				// do nothing…
			} else {
				params.add(JsonUtils.toJSONString(arg));
			}
		}
		String param = JsonUtils.toJSONString(params);
		try {
			if (ret == null) {
				// 一切正常的情况下，继续执行被拦截的方法
				ret = joinPoint.proceed();
			}
		} catch (Throwable e) {
			if(e instanceof CustomException){
				//自定义异常则拿出responseCode
				CustomException customException = (CustomException)e;
				code = customException.getCode().code();
				errorMsg +=String.format(" --  自定义异常：%s", customException.getMessage());
			}else {
				code = ResponseCode.FAILED.code();
			}
			Date eTime = new Date();
			long usedTime = eTime.getTime() - sTime.getTime();
			BehaviorLog log = new BehaviorLog(sTime, interfaceName, param, e.getMessage(), eTime, usedTime, usrToken, requestIP, className, methodName, requestURL, requestMethod,code);
			//message error用来当作mongo的collection/后续的参数传入的值
			Map<String,Object> exOutPut = new HashMap<String,Object>();
			StackTraceElement[] stackTraceElements = e.getStackTrace();
			exOutPut.put("case", e.toString());
			//查找到本项目的第一错误
			boolean find = false;
			for (StackTraceElement stackTraceElement : stackTraceElements) {
				if(stackTraceElement.getClassName().indexOf("hzcf") >=0 ){
					exOutPut.put("fileName",stackTraceElement.getFileName()); 
					exOutPut.put("lineNumber",stackTraceElement.getLineNumber()); 
					exOutPut.put("methodName",stackTraceElement.getMethodName()); 
					exOutPut.put("className", stackTraceElement.getClassName());
					exOutPut.put("localizedMessage", e.getLocalizedMessage());
					find = true;
					break;
				}
				
			}
			if(!find){
				//如果未找到，则打印所有堆栈信息
				exOutPut.put("fillInStackTrace", e.fillInStackTrace());
			}
			//错误记录至error
			logger.error(errorMsg,JsonUtils.toJSONString(log),exOutPut);
		}finally{
			//运行日志记录至info
			String result = JsonUtils.toJSONString(ret);
			Date eTime = new Date();
			long usedTime = eTime.getTime() - sTime.getTime();
			BehaviorLog log = new BehaviorLog(sTime, interfaceName, param, result, eTime, usedTime, usrToken, requestIP, className, methodName, requestURL, requestMethod,code);
			logger.info(infoMsg, JsonUtils.toJSONString(log),log);
			//运行超时记录至warn
			if(usedTime > 3000){
				//运行超过三秒记录慢日志
				logger.warn(slowMsg+"运行超过["+usedTime+"]s", JsonUtils.toJSONString(log),log);
			}
		}
		return ret;
	}

	/**
	 * 使用@AfterThrowing 用来处理当切入内容部分抛出异常之后的处理逻辑。
	 * 
	 * @param joinPoint
	 */
	@AfterThrowing(pointcut = "behaviorLogAspect()",throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint,Throwable e) {
		e.printStackTrace();
		logger.error("@doAfterThrowing");
	}
}
