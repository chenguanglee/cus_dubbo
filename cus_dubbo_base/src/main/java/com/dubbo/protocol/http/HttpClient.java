package com.dubbo.protocol.http;


import com.alibaba.fastjson.JSONObject;
import com.dubbo.protocol.framework.Invocation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author chenguangli
 * @date 2019/12/18 23:43
 */
public class HttpClient {

    public <T> T send(String hostName, int port, Invocation invocation) {
        try {
            URL url = new URL("http", hostName, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }
            Class returnType = invocation.getReturnType();
            String jsonStr = stringBuffer.toString();
            return (T) JSONObject.parseObject(jsonStr, returnType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
