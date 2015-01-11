package com.yyljlyy.sys.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class usrInfoInterceptor implements Interceptor {
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
