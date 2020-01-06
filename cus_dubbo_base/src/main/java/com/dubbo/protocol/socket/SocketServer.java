package com.dubbo.protocol.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenguangli
 * @date 2020/1/6 22:20
 */
public class SocketServer {

    public void start(String hostName, int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        // (1) 接收新连接线程
        while (true) {
            try {
                // (1) 阻塞方法获取新的连接
                Socket socket = serverSocket.accept();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            OutputStream outputStream = socket.getOutputStream();
                            new SocketHandler().handler(inputStream, outputStream);
                        } catch (IOException e) {
                        }
                    }
                });

            } catch (IOException e) {
            }
        }
    }

}
