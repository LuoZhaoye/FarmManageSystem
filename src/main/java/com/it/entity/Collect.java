package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏实体类
 */
@Data
@TableName("gm_collect")
public class Collect implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * itemId
     */
    private String itemId;
    /**
     * 名称
     */
    private String title;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 时间
     */
    private String time;
}