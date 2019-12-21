package com.dubbo.impl;


import com.dubbo.service.HelloService;

/**
 * @author chenguangli
 * @date 2019/12/18 23:01
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String userName) {
        return "hello" + userName;
    }
}
