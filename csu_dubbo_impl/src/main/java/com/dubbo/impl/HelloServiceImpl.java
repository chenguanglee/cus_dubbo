package com.dubbo.impl;


import com.dubbo.service.HelloService;
import com.dubbo.service.Person;

/**
 * @author chenguangli
 * @date 2019/12/18 23:01
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String userName) {
        return "hello" + userName;
    }

    @Override
    public Person getPerson(String id) {
        return new Person().setAge(25).setName("陈虎").setSex(true);
    }
}
