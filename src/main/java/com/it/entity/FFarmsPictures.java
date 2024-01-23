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
@TableName("farms_pictures")
public class FFarmsPictures implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "farm_picture_id", type = IdType.ID_WORKER)
    private int farm_picture_id;
    /**
     *
     */
    private String farm_picture_url;
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