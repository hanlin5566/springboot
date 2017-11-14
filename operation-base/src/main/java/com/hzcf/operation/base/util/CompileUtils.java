package com.hzcf.operation.base.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.hzcf.operation.base.result.ResponseCode;
import com.hzcf.operation.base.result.Result;

/**
 * Create by hanlin on 2017年11月10日
 **/
public class CompileUtils {
	public static Result<String> javaCodeToObject(String fullClassName) throws Exception {
		
		Result<String> ret = new Result<String>();
		// 获取系统编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 建立DiagnosticCollector对象
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		// 建立用于保存被编译文件名的对象
		// 每个文件被保存在一个从JavaFileObject继承的类中
		StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);

		// 创建javacode对象
		Iterable<? extends JavaFileObject> compilationUnits = standardFileManager.getJavaFileObjectsFromStrings(Arrays.asList(fullClassName));
		// 使用编译选项可以改变默认编译行为。编译选项是一个元素为String类型的Iterable集合
		List<String> options = new ArrayList<String>();
		options.add("-encoding");
		options.add("UTF-8");

		JavaCompiler.CompilationTask task = compiler.getTask(null, standardFileManager, diagnostics, options, null,
				compilationUnits);

		// 编译源程序
		boolean success = task.call();
		if (success) {
			//编译成功则发布到衍生变量接口
			ret.setMessage("build success");
			ret.setResponseCode(ResponseCode.OK);
		} else {
			// 如果想得到具体的编译错误，可以对Diagnostics进行扫描
			String error = "";
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				error += getCompileInfo(diagnostic);
			}
			ret.setMessage(error);
			ret.setResponseCode(ResponseCode.RESULT_SYSTEM_ERROR);
		}

		return ret;
	}

	// 获取编译失败原因
	private static String getCompileInfo(Diagnostic<?> diagnostic) {
		StringBuffer errorLog = new StringBuffer();
		errorLog.append("Code:[" + diagnostic.getCode() + "]\n");
		errorLog.append("Kind:[" + diagnostic.getKind() + "]\n");
		errorLog.append("Position:[" + diagnostic.getPosition() + "]\n");
		errorLog.append("Start Position:[" + diagnostic.getStartPosition() + "]\n");
		errorLog.append("End Position:[" + diagnostic.getEndPosition() + "]\n");
		errorLog.append("Source:[" + diagnostic.getSource() + "]\n");
		errorLog.append("Message:[" + diagnostic.getMessage(null) + "]\n");
		errorLog.append("LineNumber:[" + diagnostic.getLineNumber() + "]\n");
		errorLog.append("ColumnNumber:[" + diagnostic.getColumnNumber() + "]\n");
		return errorLog.toString();
	}
}
