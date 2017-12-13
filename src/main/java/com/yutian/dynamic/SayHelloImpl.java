package com.yutian.dynamic;

public class SayHelloImpl implements SayHello {

	public String name ;
	
	public SayHelloImpl(String name) {
		this.name = name;
	}
	
	
	@Override
	public void sayHello() {
		System.out.println("Hello "+this.name);
		
	}

	@Override
	public void sayGoodBye() {
		
		System.out.println("GoodBye"+this.name);
		
	}

	
	
}
