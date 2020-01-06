package com.dubbo.protocol.socket;

import com.dubbo.protocol.framework.Invocation;
import com.dubbo.protocol.framework.Protocol;
import com.dubbo.protocol.framework.URL;

/**
 * @author chenguangli
 * @date 2020/1/6 22:29
 */
public class SocketProtocol implements Protocol {

    @Override
    public void start(URL url) {
        new SocketServer().start(url.getHostName(), url.getPort());
    }

    @Override
    public <T> T send(URL url, Invocation invocation) {
        return new SocketClient().send(url.getHostName(), url.getPort(), invocation);
    }
}
