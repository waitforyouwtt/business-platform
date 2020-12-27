package com.business.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-27
 */
public class MapStringUtils {

    public static Map<String, Object> json2map(String str) {
        int index = str.indexOf("?",0);
        str = str.substring(index + 1);
        String [] strs = str.split("&");
        Map map = new HashMap();
        for(String s:strs){
            String [] s1 = s.split("=");
            JSONArray js = JSONUtil.parseArray(s1);
            String key = (String) js.get(0);
            String value = (String) js.get(1);
            map.put(key,value);
        }
        return map;
    }

    public static String mapToString(Map<String, String> map) {
        StringBuffer content = new StringBuffer();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value == null || value.equals("")) {
                continue;
            }

            content.append("&").append(key).append("=").append(map.get(key));
        }

        return content.substring(1);
    }
}
