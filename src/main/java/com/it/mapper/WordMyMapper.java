package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.Word;
import com.it.entity.WordMy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
/**
 * 数据访问层
 */
public interface WordMyMapper extends BaseMapper<WordMy> {

    int del(@Param(value = "userid") String userid, @Param(value = "wordid") String wordid);

}
