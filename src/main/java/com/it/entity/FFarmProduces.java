package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@TableName("farms_produces")
public class FFarmProduces implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "produce_id", type = IdType.ID_WORKER)
    private int produceId;
    /**
     *
     */
    @TableField(value = "produce_name")
    private String produceName;
    /**
     *
     */
    @TableField(value = "produce_discribe")
    private String produceDiscribe;
    /**
     *
     */
    @TableField(value = "produce_picture_id")
    private String producePictureId;

    /**
     *
     */
    @TableField(value = "produce_avg_price")
    private String produceAvgPrice;

    /**
     *
     */
    @TableField(value = "farm_id")
    private String farmId;

    /**
     *
     */
    @TableField(value = "farm_name")
    private String farmName;

    /**
     *
     */
    private String deleted;

    /**
     *
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     *
     */
    @TableField(value = "update_time")
    private String updateTime;
}