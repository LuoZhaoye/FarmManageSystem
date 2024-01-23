package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.FAdmin;
import com.it.entity.FFarmProduces;
import com.it.entity.FFarms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
/**
 * 数据访问层
 */
public interface FFarmProducesMapper extends BaseMapper<FFarmProduces> {
//    public boolean addUser(FAdmin fAdmin);


    List<FFarmProduces> getFFarmsProducesList(@Param(value = "start")  int start, @Param(value = "size") int size, @Param(value = "workName") String workName);

}
