<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.common;

import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import ${basepackage}.model.*;

/**
 * Models
 */
public class ModelConfig{
	
	public static void config(ActiveRecordPlugin arp){
		arp.addMapping(${className}.tableName,${className}.class);
	}
	
	/**
	 * start JFinal Jetty with main 
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}

}