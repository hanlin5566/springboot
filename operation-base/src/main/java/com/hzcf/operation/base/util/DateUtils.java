package com.hzcf.operation.base.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Create by hanlin on 2017年11月9日
 **/
public class DateUtils {
	public static final String ISO_DATE = "yyyy-MM-dd";
	public static Date getTomorrow(Date date){
		Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        c.add(Calendar.DAY_OF_MONTH, 1);// 明天
        Date tomorrow = c.getTime();
		return tomorrow; 
	}
}
