package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Word;
import java.util.List;

public interface WordService {
    /**
     * 分页查询
     *
     * @param entity
     * @param page
     * @param limit
     * @return
     */
    Page<Word> selectPage(Word entity, int page, int limit);

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    boolean insert(Word entity);

    /**
     * 编辑
     *
     * @param entity
     * @return
     */
    boolean editById(Word entity);

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
    List<Word> getList(Word entity);

    /**
     * 获取集合
     *
     * @param entity
     * @return
     */
    List<Word> getList3();

    /**
     * 获取集合
     *
     * @param entity
     * @return
     */
    List<Word> getList2(int start, int size);

    /**
     * 获取集合
     *
     * @param entity
     * @return
     */
    List<Word> getList4(int start, int size);


    /**
     * 通过id查询单个对象
     *
     * @param id
     * @return
     */
    Word getOne(String id);

    /**
     * 添加收藏
     * @param id
     * @return
     */
    boolean wordMy(String id);


    /**
     * 添加收藏
     * @param id
     * @return
     */
    boolean cancelMyWord(String id);


}
