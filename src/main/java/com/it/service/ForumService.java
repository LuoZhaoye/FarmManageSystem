package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Forum;

import java.util.List;

public interface ForumService {
    /**
     * 分页查询
     *
     * @param entity
     * @param page
     * @param limit
     * @return
     */
    Page<Forum> selectPage(Forum entity, int page, int limit);

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    boolean insert(Forum entity);

    /**
     * 编辑
     *
     * @param entity
     * @return
     */
    boolean editById(Forum entity);

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
    List<Forum> getList(Forum entity);

    /**
     * 通过id查询单个对象
     *
     * @param id
     * @return
     */
    Forum getOne(String id);

}
