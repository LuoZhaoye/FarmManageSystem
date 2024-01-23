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
@TableName("admin")
public class FAdmin implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private int id;
    /**
     * 电话
     */
    private String phone_number;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 头像
     */
    private String profile_photo;
    /**
     * 是否删除
     */
    private int deleted;
}