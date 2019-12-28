package com.dubbo.service;

/**
 * @author chenguangli
 * @date 2019/12/18 22:59
 */
public interface HelloService {

    String sayHello(String userName);

    Person getPerson(String id);
}
