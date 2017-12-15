package com.yutian.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicSayHello implements InvocationHandler{
	
	private Object hello;
	
	public DynamicSayHello(Object hello){
		this.hello = hello;
	}
	
	public  Object bind(){
		return Proxy.newProxyInstance(hello.getClass()
                .getClassLoader(), hello.getClass().getInterfaces(),
                this);
	}
	
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Before run method .....");
		
		return method.invoke(hello, args);
	}

}
