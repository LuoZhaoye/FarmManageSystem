package com.it.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@TableName("works")
public class FWorks implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private int id;
    /**
     *作品名称
     */
    @TableField(value = "work_name")
    private String workName;
    /**
     *作者id
     */
    @TableField(value = "author_id")
    private String authorId;
    /**
     *作品类型id
     */
    @TableField(value = "works_type_id")
    private String worksTypeId;
    /**
     *大体想法
     */
    private String idea;

    /**
     *创建时间
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     *修改时间
     */
    @TableField(value = "update_time")
    private String updateTime;

    /**
     *作品状态(0:未完成 1：已经完成）
     */
    @TableField(value = "status")
    private int status;

    /**
     *作品头图
     */
    @TableField(value = "image")
    private String image;

    /**
     *第二奖项
     */
    @TableField(value = "second_vote_award_id")
    private String secondVoteAwardId;

    /**
     *
     */
    @TableField(value = "award_code")
    private String awardCode;

    /**
     *
     */
    @TableField(value = "company_code")
    private String companyCode;

    /**
     *
     */
    @TableField(value = "proposition_company_id")
    private int propositionCompanyId;


    /**
     *
     */
    @TableField(value = "works_type_code")
    private String worksTypeCode;


    /**
     *逻辑删除(1:已删除，0:未删除
     */
    @TableField(value = "is_deleted")
    private int isDeleted;
}