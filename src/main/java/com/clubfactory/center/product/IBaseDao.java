package com.clubfactory.center.product;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 基础类 MAPPER
 */
public interface IBaseDao<T> {

    /**
     * 插入记录
     * @param obj DO对象
     * @return 成功数量
     */
    int insert(T obj);

    /**
     * description: 插入数据,  主键重复则更新数据
     *
     * @author wyh
     * @date 2019/10/9 11:41
     * @param obj DO对象
     * @return 成功数量
     */
    int upsert(T obj);

    /**
     * 物理删除记录
     * @param seq 主键
     * @return 成功数量
     */
    <K> int deleteByPrimaryKey(@Param("id") K seq);

    /**
     * 更新记录(有效字段,即非空字段)
     * @param obj DO对象
     * @return 修改成功数量
     */
    int updateByPrimaryKey(T obj);

    /**
     * 根据主键 返回记录
     * @param seq 主键
     * @return 查询数据
     */
    <K> T selectByPrimaryKey(@Param("id") K seq);

    /**
     * 根据 条件返回记录
     * @param params 参数map
     * @return 数据列表
     */
    List<T> listByParams(Map<String, Object> params);


    /**
     * 批量插入数据
     *
     * 缺陷: list中, 每个对象是否为空需要保持一致,  按照第一个对象中字段是否为空判断, 非空才进行插入
     *
     * @param list 插入列表
     * @return 成功数量
     */
    int batchInsert(@Param("list") List<T> list);


    /**
     * 批量插入数据, 主键重复进行更新
     *
     * 缺陷: list中, 每个对象是否为空需要保持一致,  按照第一个对象中字段是否为空判断, 非空才进行插入/更新
     *
     * @param list 插入列表
     * @return 成功数量
     */
    int batchUpsert(@Param("list") List<T> list);


    /**
     * 批量更新
     *
     * 缺陷: list中, 每个对象是否为空需要保持一致,  按照第一个对象中字段是否为空判断, 非空才进行更新
     *
     * @param list 跟新列表  批量数量建议小于1000, 数量太大销量会很低
     * @return 成功数量
     */
    int batchUpdate(@Param("list") List<T> list);
}
