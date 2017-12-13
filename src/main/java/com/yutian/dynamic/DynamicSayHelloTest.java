package com.yutian.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class DynamicSayHelloTest {

	@Test
	public void testSynamicSayHello(){
		
		SayHello hello = new SayHelloImpl("David");
		System.out.println(hello);
		
		SayHello o = (SayHello) (new DynamicSayHello(hello).bind());
		o.sayGoodBye();
		
		System.out.println("T Or F:"+Proxy.isProxyClass(o.getClass()));
		
		System.out.println(o);
		
	}
	
	@Test
	public void testUserServiceDynmic(){
		UserService userService = new UserServiceImpl();  
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);  
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),  
                userService.getClass().getInterfaces(), invocationHandler); 
        System.out.println(userServiceProxy);
        System.out.println(userServiceProxy.getName(1));  
        System.out.println(userServiceProxy.getAge(1)); 
	}
	
}
