package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Article;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ArticleService {
    /**
     * 分页查询
     *
     * @param entity
     * @param page
     * @param limit
     * @return
     */
    Page<Article> selectPage(Article entity, int page, int limit);

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    boolean insert(Article entity);

    /**
     * 编辑
     *
     * @param entity
     * @return
     */
    boolean editById(Article entity);

    /**
     * 删除
     *
     * @param ids
     * @return
     */

    boolean deleteById(String ids);

    /**
     * 获取集合
     *
     * @param entity
     * @return
     */
    List<Article> getList(Article entity);


    /**
     * 通过id查询单个对象
     *
     * @param id
     * @return
     */
    Article getOne(String id);

    /**
     * 通过id生成下载资料链接
     *
     * @param id
     * @return
     */
    String downLoadOne(String id, String projectPath) throws UnsupportedEncodingException;

    /**
     * 通过文件绝对路径响应视频流
     *
     * @param response
     * @param fileName
     * @param type
     */
    void show(HttpServletResponse response, String fileName, String type) throws UnsupportedEncodingException;

}
