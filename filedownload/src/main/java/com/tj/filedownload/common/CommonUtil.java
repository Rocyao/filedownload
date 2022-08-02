package com.tj.filedownload.common;


import com.alibaba.fastjson.JSONObject;

/**
 * @author peng
 * @createDate 2022/8/2 9:43
 */
public class CommonUtil {

    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    public static JSONObject successJson(Object info) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("info", info);
        return resultJson;
    }
}
