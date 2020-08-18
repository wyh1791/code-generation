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

<#macro mapperEl value type>${r"#{"}${value},jdbcType=${type}}</#macro>
<#macro batchMapperEl value type>${r"#{item."}${value},jdbcType=${type}}</#macro>
<#macro mapperElPr value>${r"${"}${value}}</#macro>
${gg.setOverride(true)}<#t/>
${gg.setOutputFile(mybatisXMLPath+"/manual/${className}Mapper.xml")}<#t/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basepackage}.mapper.${className}Mapper">


</mapper>



