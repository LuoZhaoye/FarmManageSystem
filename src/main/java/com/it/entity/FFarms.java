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
@TableName("farms")
public class FFarms implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "farm_id", type = IdType.ID_WORKER)
    private int farmId;
    /**
     *
     */
    @TableField(value = "farm_name")
    private String farmName;
    /**
     *
     */
    private String farmer;
    /**
     *
     */
    @TableField(value = "farm_picture_id")
    private String farmPictureId;

    /**
     *
     */
    @TableField(value = "farm_tel")
    private String farmTel;

    /**
     *
     */
    @TableField(value = "farm_address")
    private String farmAddress;

    /**
     *
     */
    @TableField(value = "produce_id")
    private String produceId;

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