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
@TableName("produces_picture")
public class FProducesPicture implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "picture_id", type = IdType.ID_WORKER)
    private int picture_id;
    /**
     *
     */
    private String produce_picture;
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