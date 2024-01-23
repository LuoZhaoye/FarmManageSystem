package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.FAdmin;
import com.it.entity.FContests;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 数据访问层
 */
public interface FContestsMapper extends BaseMapper<FContests> {
//    public boolean addUser(FAdmin fAdmin);
}
