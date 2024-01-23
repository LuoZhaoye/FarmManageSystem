package com.it.service;

import com.it.entity.Collect;
import com.it.entity.Praise;

import java.util.List;

public interface CollectService {


    /**
     * 新增
     *
     * @param entity
     * @return
     */
    boolean insert(Collect entity);


    /**
     * 删除
     *
     * @param ids
     * @return
     */

    boolean deleteById(String ids);

    /**
     * 获取收藏列表
     *
     * @return
     */
    List<Collect> getList();

    /**
     * 验证当前用户是否收藏
     *
     * @param itemId
     * @return
     */
    boolean isCollect(String itemId);
    /**
     * 点赞
     *
     * @param entity
     * @return
     */
    boolean insert(Praise entity);

    /**
     * 删除点赞
     *
     * @param id
     * @return
     */
    boolean deletePraiseById(String id);
    /**
     * 验证当前用户是否点赞该条目
     *
     * @param itemId
     * @return
     */
    boolean isPraise(String itemId);

    Integer getPraiseNum(String itemId);

}
