package com.georlegacy.crypto.eirscos.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParseUtil {

    public static Map<String, String> jsonToMap(String json) {
        JSONObject object = new JSONObject(json);
        if (object.getInt("status") != 200)
            return null;
        JSONArray array = object.getJSONArray("data");

        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < array.length(); i++) {
            map.put(array.getJSONObject(i).getString("original"), array.getJSONObject(i).getString("final"));
        }
        return map;
    }

}
