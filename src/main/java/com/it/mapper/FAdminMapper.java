package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.FAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 数据访问层
 */
public interface FAdminMapper extends BaseMapper<FAdmin> {
//    public boolean addUser(FAdmin fAdmin);
}
