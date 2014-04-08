package com.himanshugupta53.triggeraction.utility;

import java.util.List;

public class Utility {

	public static String getClassName(Object obj){
		Class<?> enclosingClass = obj.getClass().getEnclosingClass();
		if (enclosingClass != null) {
			return enclosingClass.getName();
		} else {
			return obj.getClass().getName();
		}
	}
	
	public static boolean areListsEqual(List<String> l1, List<String> l2){
		if ((l1 == null || l1.size() == 0) && (l2 == null || l2.size() == 0)){
			return true;
		}
		for (String str : l1){
			if (!l2.contains(str)){
				return false;
			}
		}
		for (String str : l2){
			if (!l1.contains(str)){
				return false;
			}
		}
		return true;
	}
	
}
