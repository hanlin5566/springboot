package com.hzcf.operation.base.log;

import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.hzcf.operation.base.util.SpringUtils;
import com.mongodb.BasicDBObject;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

/**
 * Create by hanlin on 2017年11月15日
 * TODO:1.mongotemplate集成logback目前有些问题。
 * 	    2.logback传递bean时再转换成bean时有些问题。
 * 	    3.Exception无法传递至logback
 *      4.目前再使用jsonString来回互转，性能会出现问题。并且入mongo时，又把bean转换为了map。
 *      5.ControllerBehaviorAspect有一些描述，有时间需要做到mongodb的配置里去。
 *      6.MongoDBAppender的collection，有时间需要做到mongodb的配置里去。
 **/
public class MongoTemplateAppender extends UnsynchronizedAppenderBase<LoggingEvent> {
	@Override
	protected void append(LoggingEvent event) {
		//由于此类未交由spring管理，所以无法@Autowrited注入MongoTemplate，只能通过applicationContext获取。
		MongoTemplate mongoTemplate = SpringUtils.getBean(MongoTemplate.class);
        if (mongoTemplate != null) {  
            final BasicDBObject doc = new BasicDBObject();  
            doc.append("level", event.getLevel().toString());  
            doc.append("logger", event.getLoggerName());  
            doc.append("thread", event.getThreadName());  
            doc.append("message", event.getFormattedMessage());  
            
            doc.put("timestamp", new Date(event.getTimeStamp()));
            doc.put("level", event.getLevel().toString());
            doc.put("thread", event.getThreadName());
            doc.put("loggerName", event.getLoggerName());
            //
            doc.put("fileName", "UnKnow");
            doc.put("class", "UnKnow");
            doc.put("method", "UnKnow");
            doc.put("lineNumber", "UnKnow");
            doc.put("host", "UnKnow");
            //集合名称由外部loggerName传入。例如logger.info("behavior", log);其中behaviour为collectionName
            String collectionName = event.getLoggerName();
            mongoTemplate.insert(doc, collectionName);  
        }  
	}

}
