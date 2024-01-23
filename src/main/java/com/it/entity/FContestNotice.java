package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@TableName("contest_notice")
public class FContestNotice implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private int id;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private String createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time" )
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String updateTime;

    /**
     * 是否删除1:已删除，0:未删除
     */
    private int is_deleted;
}