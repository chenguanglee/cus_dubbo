package com.dubbo.protocol.framework;




import com.dubbo.protocol.http.HttpProtocol;
import com.dubbo.protocol.netty.NettyProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author chenguangli
 * @date 2019/12/19 23:55
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {


        //java spi
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();

        //dubbo spi

        //工厂模式
//        String name = System.getProperty("protocolName");
//        if (name == null || name.length() == 0) {
//            name = "http";
//        }
//        Protocol protocol;
//        switch (name) {
//            case "http":
//                protocol = new HttpProtocol();
//                break;
//            case "dubbo":
//                protocol = new NettyProtocol();
//                break;
//            default:
//                protocol = new HttpProtocol();
//
//        }
//        return protocol;
    }
}
