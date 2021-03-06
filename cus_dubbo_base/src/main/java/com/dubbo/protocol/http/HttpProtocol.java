package com.dubbo.protocol.http;


import com.dubbo.protocol.framework.Invocation;
import com.dubbo.protocol.framework.Protocol;
import com.dubbo.protocol.framework.URL;
import com.dubbo.protocol.register.RemoteMapRegister;

/**
 * @author chenguangli
 * @date 2019/12/19 23:52
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostName(), url.getPort());
    }

    @Override
    public <T> T send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHostName(), url.getPort(), invocation);
    }
}
