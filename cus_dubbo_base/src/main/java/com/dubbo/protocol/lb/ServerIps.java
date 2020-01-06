package com.dubbo.protocol.lb;

import java.util.*;

/**
 * @author chenguangli
 * @date 2019/12/28 11:12
 */
public class ServerIps {

    public static final List<String> LIST = Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5",
            "192.168.0.6"
    );


    public static final Map<String, Integer> WEIGHT_MAP = new LinkedHashMap<>();

    public static final Map<String, Integer> WEIGHT_MAP_1 = new HashMap<>();

    public static final Map<String, Integer> WEIGHT_MAP_2 = new TreeMap<>();


    static {
        WEIGHT_MAP.put("192.168.0.1", 5);
        WEIGHT_MAP.put("192.168.0.2", 3);
        WEIGHT_MAP.put("192.168.0.3", 1);


    }

}
