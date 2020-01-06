package com.dubbo.protocol.socket;

import com.alibaba.fastjson.JSONObject;
import com.dubbo.protocol.framework.Invocation;
import com.dubbo.protocol.register.LocalRegister;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @author chenguangli
 * @date 2020/1/6 22:31
 */
public class SocketHandler {
    public void handler(InputStream inputStream, OutputStream outputStream) {
        //处理请求, 返回结果
        try {
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) ois.readObject();

            Class implClass = LocalRegister.get(invocation.getInterfaceName());
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            Object result = method.invoke(implClass.newInstance(), invocation.getParams());
            String resultStr = JSONObject.toJSONString(result);
            outputStream.write(resultStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
