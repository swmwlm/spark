package com.yyljlyy.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class IndexInterceptor implements Interceptor {
	public void intercept(ActionInvocation ai) {
		beforeInvoke(ai);
		ai.invoke();
		afterInvoke(ai);
	}
	
	private void beforeInvoke(ActionInvocation ai) {
	}
    private void afterInvoke(ActionInvocation ai) {
	}
}
