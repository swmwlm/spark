package com.yyljlyy.ext;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRender;

/**
 * 继承BeetlRender，实现视图耗时的计算
 */
public class MyBeetlRender extends BeetlRender {
	public static final String renderTimeKey = "renderTime";
	
	public MyBeetlRender(GroupTemplate gt, String view) {
		super(gt, view);
	}

	public void render() {
		long start = System.currentTimeMillis();
		super.render();
		long end = System.currentTimeMillis();
		long renderTime = end - start;
		request.setAttribute(MyBeetlRender.renderTimeKey, renderTime);
	}

}
