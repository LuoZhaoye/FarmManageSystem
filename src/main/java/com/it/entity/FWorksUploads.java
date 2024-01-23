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
@TableName("works_uploads")
public class FWorksUploads implements Serializable {
    /**
     * 自增长主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private int id;
    /**
     *上传的文件名称
     */
    private String filename;
    /**
     *上传的文件路径
     */
    private String path;
    /**
     *上传的文件的格式
     */
    private String extension;
    /**
     *作者id
     */
    private int author_id;
    /**
     *作品id
     */
    private int works_id;
    /**
     *文件上传状态
     */
    private int staus;
    /**
     *作品类型id
     */
    private int is_deleted;
}