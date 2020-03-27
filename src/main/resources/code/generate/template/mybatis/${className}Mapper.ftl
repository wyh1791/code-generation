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
<#macro mapperElPr value>${r"#{"}${value}}</#macro>
${gg.setOverride(true)}<#t/>
${gg.setOutputFile(javaPath+"/${className}Mapper.xml")}<#t/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basepackage}.${className}Dao">

    <resultMap id="BaseResultMap" type="${basepackage}.${className}DO">
		<#list table.pkColumns as pk>
		<id property="${pk.columnNameFirstLower}" column="${pk.sqlName?upper_case}"/><!--${pk.remarks!}-->
        </#list>
		<#list table.notPkColumns as column>
		<result property="${column.columnNameFirstLower}" column="${column.sqlName?upper_case}"/><!--${column.remarks!}-->
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <trim suffixOverrides=",">
		<#list  table.columns as column>
            ${column.sqlName?upper_case},
        </#list>
        </trim>
    </sql>

    <#if table.pkColumns?size != 0>

    <sql id="idCnd">
        <where>
            <@idCndNotNull table.pkColumns/>
            <if test="<@idCnd table.pkColumns/>">1=0</if>
        </where>
    </sql>

    <insert id="insert" parameterType="${basepackage}.${className}DO"
            keyProperty="${table.pkColumns?first}" useGeneratedKeys="true">
        INSERT INTO
        ${table.sqlName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
                <@fieldIf/>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
                <@propertyIf/>
        </trim>
    </insert>

    <insert id="upsert" parameterType="${basepackage}.${className}DO"
            keyProperty="${table.pkColumns?first}" useGeneratedKeys="true">
        INSERT INTO
        ${table.sqlName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <@fieldIf/>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <@propertyIf/>
        </trim>
        ON DUPLICATE KEY UPDATE
        <trim suffixOverrides=",">
        <@fieldEqPropertyUpsert/>
        </trim>
    </insert>

    <delete id="deleteByPrimaryKey">
        DELETE FROM ${table.sqlName}
        <include refid="idCnd"/>
    </delete>

    <update id="updateByPrimaryKey">
        UPDATE ${table.sqlName}
        <set>
            <@fieldEqPropertyIf/>
        </set>
        <include refid="idCnd"/>
    </update>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        ${table.sqlName}
        <include refid="idCnd"/>
    </select>


    <sql id="where_clause">
        <where>
            <@fieldEqPropertyWhere/>
        </where>
    </sql>

    <select id="listByParams" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        ${table.sqlName}
        <include refid="where_clause"/>
    </select>


    <insert id="batchInsert" parameterType="${basepackage}.${className}DO"
            keyProperty="${table.pkColumns?first}" useGeneratedKeys="true">
        INSERT INTO
    ${table.sqlName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <@batchFieldIf/>
        </trim>
        values
        <foreach collection="list" item="item" separator=",">
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <@batchPropertyIf/>
            </trim>
        </foreach>
    </insert>

    <insert id="batchUpsert" parameterType="${basepackage}.${className}DO"
            keyProperty="${table.pkColumns?first}" useGeneratedKeys="true">
        INSERT INTO
    ${table.sqlName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <@batchFieldIf/>
        </trim>
        values
        <foreach collection="list" item="item" separator=",">
            <trim prefix="(" suffix=")" suffixOverrides=",">
                <@batchPropertyIf/>
            </trim>
        </foreach>
        ON DUPLICATE KEY UPDATE
        <trim suffixOverrides=",">
            <@batchFieldEqPropertyUpsert/>
        </trim>
    </insert>

    <update id="batchUpdate" parameterType="${basepackage}.${className}DO">
        update
    ${table.sqlName}
        <trim prefix="set" suffixOverrides=",">
            <@batchFieldEqPropertyUpdate/>
        </trim>
        where id in
        <foreach collection="list" index="index" item="item" separator="," open="(" close=")">
            <@mapperElPr "item.id"/>

        </foreach>
    </update>

    </#if>
</mapper>

<#macro idCnd pkColumns>
    <#compress>
        <#if pkColumns?? >
            <#if pkColumns?size==1 >
                ${pkColumns?first.columnNameFirstLower}==null
            <#else >
                <#assign  index = 0 />
                <#list pkColumns as pk>
                    <#if index == 0>
                        <#assign  index = index+1/>
                        ${pk.columnNameFirstLower}==null
                    <#else >
                    and  ${pk.columnNameFirstLower}
                    </#if>
                </#list>
            </#if>
        </#if>
    </#compress>
</#macro>

<#macro idCndNotNull pkColumns>
    <#if pkColumns?? >
        <#if pkColumns?size==1 >
            <#assign  pk = pkColumns?first />
            <if test="${pk.columnNameFirstLower}!=null">${pk.sqlName?upper_case}=<@mapperEl pk.columnNameFirstLower pk.jdbcSqlTypeName/></if>
        <#else >
            <trim suffixOverrides=" AND ">
            <#list pkColumns as pk>
                <if test="${pk.columnNameFirstLower}!=null">${column.sqlName?upper_case}=<@mapperEl pk.columnNameFirstLower pk.jdbcSqlTypeName/> AND </if>
            </#list>
            </trim>
        </#if>
    </#if>
</#macro>

<#macro isPkColumnCheck column>
    <#assign  isPkColumn = "false"/>
    <#if table.pkColumns?? >
        <#list table.pkColumns as pk>
            <#if pk.sqlName?upper_case == column.sqlName?upper_case>
                <#assign  isPkColumn = "true"/>
            </#if>
        </#list>
    </#if>
</#macro>

<#macro field>
    <#list table.columns as column>
        ${column.sqlName?upper_case},
    </#list>
</#macro>
<#macro property>
    <#list table.columns as column>
        <@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,
    </#list>
</#macro>

<#macro fieldIf>
    <#list table.columns as column>
		<if test="${column.columnNameFirstLower}!=null">${column.sqlName?upper_case},</if>
    </#list>
</#macro>
<#macro propertyIf>
    <#list table.columns as column>
		<if test="${column.columnNameFirstLower}!=null"><@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,</if>
    </#list>
</#macro>



<#macro fieldEqProperty>
    <#list table.columns as column>
        ${column.sqlName?upper_case}=<@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,
    </#list>
</#macro>

<#macro fieldEqPropertyIf>
    <#list table.columns as column>
		<if test="${column.columnNameFirstLower}!=null">${column.sqlName?upper_case}=<@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,</if>
    </#list>
</#macro>
<#macro fieldEqPropertyWhere>
    <#list table.columns as column>
        <if test="${column.columnNameFirstLower}!=null">AND ${column.sqlName?upper_case}=<@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/></if>
    </#list>
</#macro>


<#macro fieldEqPropertyUpsert>
    <#list table.columns as column>
        <@isPkColumnCheck column/>
        <#if isPkColumn == "false">
        <if test="${column.columnNameFirstLower}!=null">${column.sqlName?upper_case}=<@mapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,</if>
        </#if>
    </#list>
</#macro>


<#macro batchFieldIf>
    <#list table.columns as column>
    <if test="list[0].${column.columnNameFirstLower}!=null">${column.sqlName?upper_case},</if>
    </#list>
</#macro>

<#macro batchPropertyIf>
    <#list table.columns as column>
    <if test="list[0].${column.columnNameFirstLower}!=null"><@batchMapperEl column.columnNameFirstLower column.jdbcSqlTypeName/>,</if>
    </#list>
</#macro>


<#macro batchFieldEqPropertyUpsert >
    <#list table.columns as column>
        <@isPkColumnCheck column/>
        <#if isPkColumn == "false">
            <if test="list[0].${column.columnNameFirstLower}!=null">${column.sqlName?upper_case}=values(${column.sqlName?upper_case}),</if>
        </#if>
    </#list>
</#macro>


<#macro batchFieldEqPropertyUpdate>
    <#list table.columns as column>
        <@isPkColumnCheck column/>
        <#if isPkColumn == "false">
        <if test="list[0].${column.columnNameFirstLower}!=null">
            <trim prefix="${column.sqlName?upper_case} = case" suffix="end,">
                <foreach collection="list" item="item" index="index">
                    when id=<@mapperElPr "item.id"></@> then <@batchMapperEl column.columnNameFirstLower column.jdbcSqlTypeName></@>
                </foreach>
            </trim>
        </if>
        </#if>
    </#list>
</#macro>


