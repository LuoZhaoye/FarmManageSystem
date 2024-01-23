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
@TableName("contests")
public class FContests implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private int id;
    /**
     *
     */
    private String contest_name;
    /**
     *
     */
    private String url;
    /**
     *
     */
    private String type;
    /**
     *
     */
    private String begin_time;
    /**
     *
     */
    private String end_time;
    /**
     *
     */
    private String deleted;
    /**
     *
     */
    private String create_time;
    /**
     *
     */
    private String update_time;
}