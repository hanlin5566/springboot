package com.hzcf.operation.base.log;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.helpers.LogLog;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzcf.operation.base.util.JsonUtils;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * Create by hanlin on 2017年11月15日
 **/
public class MongoDBAppender extends AppenderBase<LoggingEvent> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String host = "127.0.0.1";
	private int port = 27017;
	private String db = "db";
	private Document hostInfo = new Document();
	private MongoClient mongoClient;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	private void setupNetworkInfo() {
		hostInfo.put("process", ManagementFactory.getRuntimeMXBean().getName());
		try {
			hostInfo.put("name", InetAddress.getLocalHost().getHostName());
			hostInfo.put("ip", InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			LogLog.warn(e.getMessage());
		}
	}

	public MongoDBAppender() {
		setupNetworkInfo();
	}

	@Override
	protected void append(LoggingEvent event) {
		Document mongoDocument = new Document();
		mongoDocument.put("aspect", event.getLoggerName());
		mongoDocument.put("timestamp", new Date(event.getTimeStamp()));
		mongoDocument.put("level", event.getLevel().toString());
		mongoDocument.put("thread", event.getThreadName());
		mongoDocument.put("loggerName", event.getLoggerName());
		mongoDocument.put("message", event.getMessage());
		// host
		mongoDocument.put("host", hostInfo);
		Object[] args = event.getArgumentArray();
		for (Object obj : args) {
			if (obj instanceof String) {
				BehaviorLog behaviorLog = JsonUtils.json2Bean(obj.toString(), BehaviorLog.class);
				mongoDocument.put("detailMsg", JsonUtils.json2Map(obj.toString()));
				mongoDocument.put("fileName", behaviorLog.getClassName());
				mongoDocument.put("interfaceName", behaviorLog.getInterfaceName());
				mongoDocument.put("usedTime", behaviorLog.getUsedTime());
				mongoDocument.put("method", behaviorLog.getMethodName());
			} else if (obj instanceof BehaviorLog) {
				BehaviorLog behaviorLog = (BehaviorLog)obj;
				mongoDocument.put("detailMsg", behaviorLog);
				mongoDocument.put("fileName", behaviorLog.getClassName());
				mongoDocument.put("interfaceName", behaviorLog.getInterfaceName());
				mongoDocument.put("usedTime", behaviorLog.getUsedTime());
				mongoDocument.put("method", behaviorLog.getMethodName());
			}else if (obj instanceof Map){
				//异常通过map传递
				mongoDocument.put("errorInfo",obj);
			}else if(obj instanceof Exception){
				Exception e = (Exception)obj;
				Map<String,Object> exOutPut = new HashMap<String,Object>();
				StackTraceElement stackTraceElement= e.getStackTrace()[0]; 
				exOutPut.put("fileName",stackTraceElement.getFileName()); 
				exOutPut.put("lineNumber",stackTraceElement.getLineNumber()); 
				exOutPut.put("methodName",stackTraceElement.getMethodName()); 
				exOutPut.put("stackTrace", e.fillInStackTrace());
				exOutPut.put("exMessage", e.getMessage());
				exOutPut.put("localizedMessage", e.getLocalizedMessage());
				mongoDocument.put("exception",exOutPut);
			}
		}
		String collectionName = "operation"+event.getLevel().levelStr;
		MongoCollection<Document> collection = mongoClient.getDatabase(db).getCollection(collectionName);
		try {
			collection.insertOne(mongoDocument);
		} catch (Exception e) {
			logger.error("日志写入mongo异常", e);
		}
	}

	@Override
	public void start() {
		// 初始化mongoClient
		ServerAddress serverAddress = new ServerAddress(host, port);
		mongoClient = new MongoClient(serverAddress);
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
	}
}
