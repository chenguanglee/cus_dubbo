package com.dubbo.protocol.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenguangli
 * @date 2019/12/18 23:44
 */
@Data
@AllArgsConstructor
public class Invocation implements Serializable {

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class[] paramTypes;

    /**
     * 参数列表
     */
    private Object[] params;

    /**
     * 方法返回类型
     */
    private Class returnType;

}
