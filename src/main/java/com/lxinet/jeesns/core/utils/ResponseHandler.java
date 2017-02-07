package com.lxinet.jeesns.core.utils;

import net.sf.json.JSONObject;
import java.util.Map;

/**
 * 操作之后返回类
 * Created by zchuanzhao on 2016/9/26.
 */
public class ResponseHandler {
	public static Map<String, Object> returnJson(int code) {
		JSONObject json = new JSONObject();
		json.put("code",code);
		if(code == 0) {
			json.put("msg", "操作成功");
		} else if (code == -1) {
			json.put("msg", "操作失败，请重试");
		} else if (code == -2) {
			json.put("msg", "参数错误");
		}
		return json;
	}
	
	public static Map<String, Object> returnJson(int code, String msg) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		return json;
	}

	public static Map<String, Object> returnJson(int code, String msg, String href) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		json.put("href", href);
		return json;
	}
}
