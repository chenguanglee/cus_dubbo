package com.dubbo;


import com.dubbo.impl.HelloServiceImpl;
import com.dubbo.protocol.framework.Protocol;
import com.dubbo.protocol.framework.ProtocolFactory;
import com.dubbo.protocol.framework.URL;
import com.dubbo.protocol.http.HttpProtocol;
import com.dubbo.protocol.register.LocalRegister;
import com.dubbo.protocol.register.RemoteMapRegister;
import com.dubbo.service.HelloService;

/**
 * @author chenguangli
 * @date 2019/12/18 23:02
 */
public class Provider {

    public static void main(String[] args) {
        //1.本地注册
        //{服务名: 实现类}

        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        //2.远程注册
        //{服务名: List<URL>}
        URL url = new URL();
        url.setHostName("localhost");
        url.setPort(8080);
        RemoteMapRegister.registe(HelloService.class.getName(), url);

        //3.启动tomcat
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
