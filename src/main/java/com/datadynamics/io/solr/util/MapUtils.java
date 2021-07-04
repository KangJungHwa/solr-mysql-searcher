package com.datadynamics.io.solr.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Map Utility.
 */
public class MapUtils {

    /**
     * 지정한 Key Value를 가진 Map을 생성한다.
     *
     * @param key   Map 생성시 초기 key
     * @param value Map 생성시 초기 Key에 대한 Value
     * @return 새로 생성한 Map
     */
    public static Map<String, Object> map(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    /**
     * 지정한 Key Value를 가진 Map을 생성한다.
     *
     * @param key1   Map 생성시 초기 key
     * @param value1 Map 생성시 초기 Key에 대한 Value
     * @param key2   Map 생성시 초기 key
     * @param value2 Map 생성시 초기 Key에 대한 Value
     * @return 새로 생성한 Map
     */
    public static Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> map = new HashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }

    /**
     * 지정한 Key Value를 가진 Map을 생성한다.
     *
     * @param key1   Map 생성시 초기 key
     * @param value1 Map 생성시 초기 Key에 대한 Value
     * @param key2   Map 생성시 초기 key
     * @param value2 Map 생성시 초기 Key에 대한 Value
     * @param key3   Map 생성시 초기 key
     * @param value3 Map 생성시 초기 Key에 대한 Value
     * @return 새로 생성한 Map
     */
    public static Map<String, Object> map(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        Map<String, Object> map = new HashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        return map;
    }

    /**
     * 지정한 Key Value를 가진 Map을 생성한다.
     *
     * @param key      Map 생성시 초기 key
     * @param paramMap Map 생성시 초기 Key에 대한 Map
     * @return 새로 생성한 Map
     */
    public static Map map(String key, Map paramMap) {
        Map map = new HashMap<>();
        map.put(key, paramMap);
        return map;
    }

    /**
     * 지정한 Key Value를 가진 Map을 생성한다.
     *
     * @param key   Map 생성시 초기 key
     * @param value Map 생성시 초기 Key에 대한 Value
     * @return 새로 생성한 Map
     */
    public static Map map(String key, Boolean value) {
        Map map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
