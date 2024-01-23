package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 点赞记录实体类
 */
@Data
@TableName("gm_praise")
public class Praise implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 被点赞条目id
     */
    private String itemId;
    /**
     * 点赞用户id
     */
    private String userId;
}