package com.util;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by superfq on 2017/3/20.
 */
public class JsonUtil {

    public static String JsonUtil(Object object) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString=objectMapper.writeValueAsString(object);
        return  jsonString;
    }
}
