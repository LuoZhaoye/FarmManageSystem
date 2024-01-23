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
@TableName("gm_word")
public class Word implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 单词
     */
    private String word;
    /**
     * 单词描述
     */
    private String worddesc;
    /**
     * 例子
     */
    private String example;
    /**
     * 创建时间
     */
    private String createdate;
    /**
     * 预留字段
     */
    private String mid;
}