package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.FAdmin;
import com.it.entity.FFarmProduces;
import com.it.entity.FWorks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
/**
 * 数据访问层
 */
public interface FWorksMapper extends BaseMapper<FWorks> {
//    public boolean addUser(FAdmin fAdmin);

    List<FWorks> getFWorksList(@Param(value = "start")  int start, @Param(value = "size") int size, @Param(value = "workName") String workName);
    List<FWorks> getFWorksList2(@Param(value = "name")  String name, @Param(value = "start")  int start, @Param(value = "size") int size, @Param(value = "workName") String workName);


}
