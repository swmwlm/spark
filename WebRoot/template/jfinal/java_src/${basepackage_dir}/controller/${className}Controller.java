<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.controller;

<#include "/java_imports.include"/>

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.koomii.base.BaseController;

/**
 * ${table.tableAlias}
 * @author 
 *
 */
public class ${className}Controller extends BaseController{
	
	public void index(){
			renderText(""HelloIndex");
	}

}