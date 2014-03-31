package com.himanshugupta53.triggeraction.utility;

public class Utility {

	public static String getClassName(Object obj){
		Class<?> enclosingClass = obj.getClass().getEnclosingClass();
		if (enclosingClass != null) {
			return enclosingClass.getName();
		} else {
			return obj.getClass().getName();
		}
	}
	
}
