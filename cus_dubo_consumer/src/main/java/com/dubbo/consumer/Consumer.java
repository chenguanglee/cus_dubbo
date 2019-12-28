package com.dubbo.consumer;

import com.alibaba.fastjson.JSONObject;
import com.dubbo.protocol.framework.ProxyFactory;
import com.dubbo.service.HelloService;
import com.dubbo.service.Person;

/**
 * @author chenguangli
 * @date 2019/12/18 23:42
 */
public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("leee");
        System.out.println(result);

        Person person = helloService.getPerson("1");
        System.out.println(JSONObject.toJSONString(person));
    }
}
