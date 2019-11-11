/**
*@Author: wyh
*@Date: 2019-10-11
*/
package com.clubfactory.center.product;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: BizEnum
* @Description:
* @author wyh
* @date 2019-10-11
*/
@Data
@Accessors(chain = true)
public class BizEnumDO implements Serializable{


    /**
     * 主键
     */
	private Integer id;

    /**
     * 创建时间
     */
	private Date createTime;

    /**
     * 更新时间
     */
	private Date updateTime;

    /**
     * 逻辑删除标示 1：删除
     */
	private Integer isDelete;

    /**
     * 枚举key
     */
	private String mainKey;

    /**
     * 枚举名称
     */
	private String mainValue;

    /**
     * 枚举子项key
     */
	private String itemKey;

    /**
     * 枚举子项value
     */
	private String itemValue;

    /**
     * 排序
     */
	private Integer sequence;

}



