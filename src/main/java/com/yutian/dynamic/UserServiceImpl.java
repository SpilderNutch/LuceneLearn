package com.yutian.dynamic;


public class UserServiceImpl implements UserService {  
    public String getName(int id) {  
        System.out.println("------getName------");  
        return "Tom";  
    }  
  
    public Integer getAge(int id) {  
        System.out.println("------getAge------");  
        return 10;  
    }  
} 