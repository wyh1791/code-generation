
package com.clubfactory.item.center.core.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wyh
 * @ClassName: ItemSku
 * @Description:
 * @date 2020-08-18
 */
@Data
@Accessors(chain = true)
public class ItemSkuDO implements Serializable {

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
     * 关联的item_id
     */
    private Long itemId;
    /**
     * item_sku_id
     */
    private Long itemSkuId;
    /**
     * b端货品id
     */
    private Long productId;
    /**
     * b端sku id
     */
    private Long productSkuId;
    /**
     * item_sku版本号
     */
    private Long version;
    /**
     * sku状态
     */
    private Integer status;
    /**
     * 库存状态,默认不售罄 dto不透出
     */
    private Integer inventoryStatus;
    /**
     * sku图片
     */
    private String imageUrl;
    /**
     * b端sku维度feature
     */
    private String feature;
    /**
     * 可信赖的重量, 不管重量来源
     */
    private Long weight;
    /**
     * seller_sku编码,大卖家才有
     */
    private String merchantSkuCode;
    /**
     * 多国hs_code, 税率
     */
    private String hsTable;
    /**
     * sku属性kv串
     */
    private String attributeInfo;

}



