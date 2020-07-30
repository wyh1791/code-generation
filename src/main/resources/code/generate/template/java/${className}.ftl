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
${gg.setOverride(true)}<#t/>
${gg.setOutputFile(javaPath+"/dataobject/"+className+"DO.java")}<#t/>
/**
*@Author: ${author}
*@Date: ${createTime}
*/
package ${basepackage}.dataobject;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @ClassName: ${className}
* @Description:${(table.remarks)!}
* @author ${author}
* @date ${createTime}
*/
@Data
@Accessors(chain = true)
public class ${className}DO implements Serializable{

	<#list table.columns as column>

    /**
     * ${(column.remarks)!}
     */
	private ${column.simpleJavaType} ${column.columnNameLower};
	</#list>

}



