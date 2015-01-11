package com.yyljlyy.common;

import java.io.File;

import util.ChristStringUtil;

import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;


public class HtmlController extends Controller {
	public void index(){

		String html = getAttrForStr("html");
		if(ChristStringUtil.isNotEmpty(html) && new File(JFinal.me().getServletContext().getRealPath(html)).exists()){
			render(html);
		}else{
			//不存在404
			redirect("html/404.html");
		}
		
	}
}
