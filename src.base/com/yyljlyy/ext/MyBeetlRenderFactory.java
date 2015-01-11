package com.yyljlyy.ext;

import org.beetl.ext.jfinal.BeetlRender;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.jfinal.render.Render;

/**
 * 继承BeetlRenderFactory，调用MyBeetlRender，实现视图耗时计算
 */
public class MyBeetlRenderFactory extends BeetlRenderFactory {
	public Render getRender(String view) {
		BeetlRender render = new MyBeetlRender(groupTemplate, view);
		return render;
	}
}
