package com.yutian.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{
	
	
	private Object target;  
	  
    MyInvocationHandler() {  
        super();  
    }  
  
    MyInvocationHandler(Object target) {  
        super();  
        this.target = target;  
    }  
  
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {  
        if("getName".equals(method.getName())){  
            System.out.println("++++++before " + method.getName() + "++++++");  
            Object result = method.invoke(target, args);  
            System.out.println("++++++after " + method.getName() + "++++++");  
            return result;  
        }else{  
            Object result = method.invoke(target, args);  
            return result;  
        }  
  
    }  
}
