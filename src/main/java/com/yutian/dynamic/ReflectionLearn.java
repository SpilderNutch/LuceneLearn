package com.yutian.dynamic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionLearn {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		Class stringClass = Class.forName("java.lang.String");
		
		System.out.println("stringClass toGenericString:"+stringClass.toGenericString());
		
//		Constructor constructors = stringClass.getConstructor(null);
//		Method[] methods = stringClass.getDeclaredMethods();
//		for(int i=0; i<methods.length; i++){
//			System.out.println("methodName:"+methods[i].getName()+",methodModify:"+methods[i].getParameterCount());
//		}
//		System.out.println("class methods:"+stringClass.getMethods());
//		
//		System.out.println("stringClass:"+stringClass.toGenericString());
		
		Object gg = stringClass.newInstance();
		Method equalMethod = stringClass.getMethods()[0];//equal
		Object kkk = equalMethod.invoke(gg,"");
		System.out.println("kkkk:"+kkk);
		
	}
	
	
}
