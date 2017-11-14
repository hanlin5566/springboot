package com.hzcf.operation.base.util;
/**
 * Create by hanlin on 2017年11月8日
 **/
public class StringUtils {
	public static String toUpperFristChar(String string) {  
	    char[] charArray = string.toCharArray();  
	    charArray[0] -= 32;  
	    return String.valueOf(charArray);  
	} 
}
