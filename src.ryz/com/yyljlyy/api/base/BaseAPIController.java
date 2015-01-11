package com.yyljlyy.api.base;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.JsonKit;
import com.yyljlyy.base.BaseController;



public class BaseAPIController extends BaseController {

	public void toAPIResponse(String code,String message,Map<String,Object> result){
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("code", code);
		jsonMap.put("message",message);
		jsonMap.put("result", result);
		
		String jsonStr = JsonKit.toJson(jsonMap);
		
		renderJson(jsonStr);
	}
}
