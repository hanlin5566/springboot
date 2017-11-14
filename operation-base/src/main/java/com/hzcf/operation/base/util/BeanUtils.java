package com.hzcf.operation.base.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.hzcf.operation.base.enums.DerivedVarStatus;
import com.hzcf.operation.gen.entity.DerivedVariable;
import com.hzcf.operation.gen.entity.DerivedVariableExample;

/**
 * Create by hanlin on 2017年11月8日
 **/
public class BeanUtils {
	private static PropertyDescriptor getPropertyDescriptor(PropertyDescriptor[] pds, PropertyDescriptor ref,
			boolean isStrict) {
		if (ref.getDisplayName().equals("class")) {
			return null;
		}

		for (PropertyDescriptor pd : pds) {
			if (isStrict) {
				if (pd.equals(ref)) {
					return pd;
				}
			} else {
				if (ref.getPropertyType().equals(pd.getPropertyType()) && pd.getName().equals(ref.getName())) {
					return pd;
				}
			}
		}
		return null;
	}

	public static void copyProperties(Object fromObj, Object toObj) throws RuntimeException {
		copyProperties(fromObj, toObj, false);
	}

	public static void copyProperties(Object fromObj, Object toObj, boolean ignoreNull) throws RuntimeException {
		if (fromObj == null) {
			return;
		}

		Class<? extends Object> fromClass = fromObj.getClass();
		Class<? extends Object> toClass = toObj.getClass();
		boolean isStrict = (fromClass == toClass);

		try {
			BeanInfo fromBean = Introspector.getBeanInfo(fromClass);
			BeanInfo toBean = Introspector.getBeanInfo(toClass);

			final PropertyDescriptor[] toPds = toBean.getPropertyDescriptors();
			final PropertyDescriptor[] fromPds = fromBean.getPropertyDescriptors();

			for (PropertyDescriptor toPd : toPds) {
				PropertyDescriptor fromPd = getPropertyDescriptor(fromPds, toPd, isStrict);
				if (fromPd != null && fromPd.getDisplayName().equals(toPd.getDisplayName())) {
					Method writeMethod = toPd.getWriteMethod();
					Method readMethod = fromPd.getReadMethod();
					if (writeMethod != null && readMethod != null) {
						Object param = readMethod.invoke(fromObj, (Object[]) null);
						if (ignoreNull && param == null) {
							continue;
						}
						writeMethod.invoke(toObj, param);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T copyProperties(Object from, Class<T> toClass) throws RuntimeException {
		if (from == null) {
			return null;
		}

		T to;
		try {
			to = toClass.newInstance();
			copyProperties(from, to);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return to;
	}

	public static <T> List<T> copyListProperties(Collection<? extends Object> fromList, Class<T> toClass)
			throws RuntimeException {
		if (fromList == null) {
			return null;
		}

		List<T> result = new ArrayList<T>(fromList.size());
		for (Object from : fromList) {
			T to = copyProperties(from, toClass);
			result.add(to);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(Object obj) {
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
			    f.setAccessible(true);
			    if (f.get(obj) != null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
			    	Type type = f.getGenericType();
			    	System.out.println(String.format("字段名:%s,类型:%s,值:%s", f.getName(),type.getTypeName(),f.get(obj)));
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}
	
	public static <T> T example(Object obj,Class<T> exc) {
		T ret = null;
		try {
			//实体类的条件类
			ret = (T) exc.newInstance();
			Method excm = exc.getDeclaredMethod("createCriteria");
			Object criteria = excm.invoke(ret);
			for (Field f : obj.getClass().getDeclaredFields()) {
			    f.setAccessible(true);
			    if (f.get(obj) != null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
			    	Type type = f.getGenericType();
			    	Class<?> typeClazz = Class.forName(type.getTypeName());
//			    	//字符串则用like
			    	if(typeClazz == String.class){
			    		//字符串则用like
			    		String methodName = String.format("and%sLike", StringUtils.toUpperFristChar(f.getName()));
			    		Method method = criteria.getClass().getMethod(methodName, typeClazz);
			    		method.invoke(criteria, f.get(obj)+"%");
			    	}else if(typeClazz == Date.class){
			    		//日期用大于等于今天，小于明天
			    		Date queryDate = (Date)f.get(obj);
			    		Date tomorrow = DateUtils.getTomorrow(queryDate);
			    		//大于等于今天 
			    		String methodName = String.format("and%sGreaterThanOrEqualTo", StringUtils.toUpperFristChar(f.getName()));
			    		Method method = criteria.getClass().getMethod(methodName, typeClazz);
			    		method.invoke(criteria, queryDate);
			    		//小于明天 不能包含明天的00:00:00点 所以不能用between and
			    		methodName = String.format("and%sLessThan", StringUtils.toUpperFristChar(f.getName()));
			    		method = criteria.getClass().getMethod(methodName, typeClazz);
			    		method.invoke(criteria, tomorrow);
			    	}else{
			    		String methodName = String.format("and%sEqualTo", StringUtils.toUpperFristChar(f.getName()));
			    		Method method = criteria.getClass().getMethod(methodName, typeClazz);
			    		method.invoke(criteria, f.get(obj));
			    	}
			    }
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		DerivedVariable d = new DerivedVariable();
		d.setClazzName("a");
		d.setcTime(new Date());
		d.setcUid(1);
		d.setState(DerivedVarStatus.SAVED);
		DerivedVariableExample e = new DerivedVariableExample();
		e.createCriteria().andClazzNameLike("a%").andDeployTimeGreaterThan(new Date()).andDeployTimeLessThan(DateUtils.getTomorrow(new Date())).andCUidEqualTo(1).andStateEqualTo(DerivedVarStatus.SAVED);
		DerivedVariableExample example = BeanUtils.example(d,DerivedVariableExample.class);
		System.out.println(e);
		System.out.println(example);
		
	}
	
}
