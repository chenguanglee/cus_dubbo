package com.dubbo.protocol.socket;

import com.alibaba.fastjson.JSONObject;
import com.dubbo.protocol.framework.Invocation;

import java.io.*;
import java.net.Socket;

/**
 * @author chenguangli
 * @date 2020/1/6 22:20
 */
public class SocketClient {

    public <T> T send(String hostName, int port, Invocation invocation) {
        try {
            Socket socket = new Socket(hostName, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(invocation);
            oos.flush();
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }
            Class returnType = invocation.getReturnType();
            String jsonStr = stringBuffer.toString();

            oos.close();
            inputStream.close();
            socket.close();
            return (T) JSONObject.parseObject(jsonStr, returnType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
