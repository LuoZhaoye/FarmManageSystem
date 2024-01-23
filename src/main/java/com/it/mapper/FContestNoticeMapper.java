package com.it.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.it.entity.FAdmin;
import com.it.entity.FContestNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
/**
 * 数据访问层
 */
public interface FContestNoticeMapper extends BaseMapper<FContestNotice> {
//    public boolean addUser(FAdmin fAdmin);

    List<FContestNotice> getFContestNoticeList(@Param(value = "start")  int start,  @Param(value = "size") int size);

}
