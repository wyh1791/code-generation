/**
*@Author: wyh
*@Date: 2020-08-18
*/
package com.clubfactory.item.center.core.dataobject;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @ClassName: Item
* @Description:
* @author wyh
* @date 2020-08-18
*/
@Data
@Accessors(chain = true)
public class ItemDO implements Serializable{


    /**
     * 
     */
	private Long id;

    /**
     * 
     */
	private Date createTime;

    /**
     * 
     */
	private Date updateTime;

    /**
     * 逻辑删除标示 1：删除
     */
	private Integer isDelete;

    /**
     * 分表按照product_id进行
     */
	private Long itemId;

    /**
     * b端货品id
     */
	private Long productId;

    /**
     * 货品货品
     */
	private String productNo;

    /**
     * 商品标题
     */
	private String title;

    /**
     * 商品状态
     */
	private Integer status;

    /**
     * 商品库存状态,0:不售罄;1:售罄
     */
	private Integer inventoryStatus;

    /**
     * item商品版本号
     */
	private Long version;

    /**
     * 后台叶子类目id
     */
	private Integer categoryId;

    /**
     * 业务模式
     */
	private Integer bizModel;

    /**
     * 商品主图
     */
	private String mainImage;

    /**
     * 轮播图
     */
	private String images;

    /**
     * 视频url
     */
	private String videoUrl;

    /**
     * 头图url
     */
	private String videoCover;

    /**
     * 确认总销量
     */
	private Integer orders;

    /**
     * 详细描述
     */
	private String specification;

    /**
     * 商品属性
     */
	private String itemProperty;

    /**
     * 
     */
	private String searchKeywords;

    /**
     * 
     */
	private Integer sellerId;

    /**
     * seller名称
     */
	private String sellerName;

    /**
     * 店铺id
     */
	private Integer storeId;

    /**
     * C端商品标签，主要用于活动
     */
	private String features;

    /**
     * b端spu维度feature
     */
	private String feature;

    /**
     * C端商品标签，主要用于活动
     */
	private String tags;

    /**
     * B端禁运禁售标签
     */
	private String illegalTags;

    /**
     * sku最高价
     */
	private BigDecimal skuMaxPrice;

    /**
     * sku最低价
     */
	private BigDecimal skuMinPrice;

    /**
     * 币种
     */
	private String currency;

    /**
     * 禁售国家
     */
	private String forbiddenSaleNations;

    /**
     * 图文详情
     */
	private String description;

    /**
     * 商品对应的销售渠道
     */
	private String channel;

}



