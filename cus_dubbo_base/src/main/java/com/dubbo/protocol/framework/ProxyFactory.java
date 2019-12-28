package com.dubbo.protocol.framework;

import com.dubbo.protocol.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenguangli
 * @date 2019/12/19 23:27
 */
public class ProxyFactory {

    public static <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Protocol protocol = ProtocolFactory.getProtocol();
                Invocation invocation = new Invocation(clazz.getName(), method.getName(), method.getParameterTypes(), args, method.getReturnType());
                //从注册中心获取调用一个远端服务的地址信息
                URL url = RemoteMapRegister.getUrlRandom(clazz.getName());
                return protocol.send(url, invocation);
            }
        });
    }
}
