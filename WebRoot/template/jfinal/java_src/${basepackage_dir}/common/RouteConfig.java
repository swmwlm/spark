<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.common;

import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import ${basepackage}.controller.*;

/**
 * Routers
 */
public class RouteConfig{
	
	public static void config(Routes me){
		me.add("/${classNameLower}", ${className}.class);
	}
	
	/**
	 * start JFinal Jetty with main 
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}

}