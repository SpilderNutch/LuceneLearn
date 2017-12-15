package com.yutian.dynamic;

public class SayHelloImpl implements SayHello {

	public String name ;
	
	public SayHelloImpl(String name) {
		this.name = name;
	}
	
	
	public void sayHello() {
		System.out.println("Hello "+this.name);
		
	}

	public void sayGoodBye() {
		
		System.out.println("GoodBye"+this.name);
		
	}

	
	
}
