package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Article;
import com.it.entity.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
/**
 * 数据访问层
 */
public interface WordMapper extends BaseMapper<Word> {


    /**
     * 通过角色Id查询到角色用户关系表中的permissionId集合
     *
     * @param roleId
     * @return
     */
    List<Word> getList(@Param(value = "roleId") String roleId);

    /**
     * 通过角色Id查询到角色用户关系表中的permissionId集合
     * @return
     */
    List<Word> getList3(@Param(value = "userid") String userid);

    /**
     * 通过角色Id查询到角色用户关系表中的permissionId集合
     * @return
     */
    List<Word> getList2(@Param(value = "userid") String userid, @Param(value = "start") int start, @Param(value = "size") int size);

    /**
     * 通过角色Id查询到角色用户关系表中的permissionId集合
     * @return
     */
    List<Word> getList4(@Param(value = "userid") String userid, @Param(value = "start") int start, @Param(value = "size") int size);

}
