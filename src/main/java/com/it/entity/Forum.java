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
@TableName("gm_forum")
public class Forum implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 时间
     */
    private String time;
    /**
     * img
     */
    private String img;
    /**
     * 用户
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userImg;
    /**
     * 内容
     */
    private String content;
    /**
     * 指定教师
     */
    private String teacher;

}