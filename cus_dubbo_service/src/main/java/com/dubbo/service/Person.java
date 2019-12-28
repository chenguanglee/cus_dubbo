package com.dubbo.service;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenguangli
 * @date 2019/12/28 0:19
 */
@Data
@Accessors(chain = true)
public class Person {

    private String name;

    private int age;

    private Boolean sex;
}
