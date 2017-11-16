package com.hzcf.operation.base.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/**
 * Create by hanlin on 2017年11月16日
 **/
public class JsonUtils {
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static <T> String bean2Json(T bean) {
		try {
			return objectMapper.writeValueAsString(bean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "";
	}
	
	public static String map2Json(Map<String,Object> map) {
		try {
			return objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "";
	}
	
	public static String list2Json(List<Object> list) {
		try {
			return objectMapper.writeValueAsString(list);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "";
	}
	
	public static <T> T json2Bean(String json, Class<T> beanClass) {
		try {
			return objectMapper.readValue(json, beanClass);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> json2List(String json, Class<T> beanClass) {
		try {
			return (List<T>)objectMapper.readValue(json, getCollectionType(List.class, beanClass));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String json) {
		try {
			return (Map<String,Object>)objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	} 
	
	public static String toJSONString(Object obj){
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}



