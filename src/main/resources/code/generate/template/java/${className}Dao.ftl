<#assign className=table.className/>
<#assign classNameLower = table.classNameFirstLower/>
<#--  
<#if className?starts_with("WorkOrder")>
	<#assign className = className?substring(8)?cap_first/>
</#if>
<#if classNameLower?starts_with("workorder")>
	<#assign classNameLower = classNameLower?substring(8)?uncap_first/>
</#if>
-->
${gg.setOverride(false)}<#t/>
${gg.setOutputFile(javaPath+"/"+className+"Dao.java")}<#t/>
/**
*@Author: ${author}
*@Date: ${createTime}
*/
package ${basepackage};
import ${basepackage}.${className}DO;
import org.springframework.stereotype.Repository;

/**
* @author ${author}
* @date ${createTime}
*/
@Repository
public interface ${className}Dao extends IBaseDao<${className}DO> {
	
}
