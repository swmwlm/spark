<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

<#include "/java_imports.include"/>

import com.jfinal.plugin.activerecord.Model;


/**
 * ${table.tableAlias}
 * @author 
 *
 */
public class ${className} extends Model<${className}>{
	public static ${className} dao = new ${className}();
	/**
	 * The mapper table of this class
	 */
	public static final String tableName = "${table.sqlName}";
	
	<@generateFields/>
	<@generateCompositeIdConstructorIfis/>
	<@generateProperties/>
}

<#macro generateFields>
	<#list table.columns as column>
	/**
	 * ${column.columnAlias}
	 */
	public static final String ${column.columnNameLower} = "${column.sqlName}";
	</#list>
	
</#macro>

<#macro generateCompositeIdConstructorIfis>
	public ${className}(){
	}
</#macro>



<#macro generateProperties>
	<#list table.columns as column>
	/**
	 * Get ${column.columnAlias}
	 */
	public ${column.javaType} get${column.columnName}() {
		return get(${column.columnNameLower});
	}
	
	/**
	 * Set ${column.columnAlias}
	 */
	public ${className} set${column.columnName}(${column.javaType} value) {
		set(${column.columnNameLower}, value);
		return this;
	}
	</#list>
</#macro>

