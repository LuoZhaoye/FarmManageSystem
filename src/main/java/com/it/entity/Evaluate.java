package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@TableName("gm_evaluate")
public class Evaluate implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 条目id
     */
    private String itemId;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 评价人
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userImg;
    /**
     * 评价时间
     */
    private String time;
}