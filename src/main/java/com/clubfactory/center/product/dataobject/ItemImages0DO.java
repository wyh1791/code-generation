/**
*@Author: wyh
*@Date: 2020-07-30
*/
package com.clubfactory.center.product.dataobject;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @ClassName: ItemImages0
* @Description:
* @author wyh
* @date 2020-07-30
*/
@Data
@Accessors(chain = true)
public class ItemImages0DO implements Serializable{


    /**
     * id
     */
	private Long id;

    /**
     * 更新时间精确到毫秒
     */
	private Date updateTime;

    /**
     * 
     */
	private Date createTime;

    /**
     * item_id
     */
	private Long itemId;

    /**
     * 图片url
     */
	private String url;

    /**
     * 图片序号, 从大到小排列
     */
	private Integer sequence;

}



