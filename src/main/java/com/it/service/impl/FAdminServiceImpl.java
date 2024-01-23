package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Actual;
import com.it.entity.FAdmin;
import com.it.mapper.ActualMapper;
import com.it.mapper.FAdminMapper;
import com.it.service.ActualService;
import com.it.service.FAdminService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *
 */
@Service
public class FAdminServiceImpl implements FAdminService {
    @Resource
    private FAdminMapper fAdminMapper;

    @Override
    public boolean addUser(FAdmin fAdmin) {
        Integer insert = fAdminMapper.insert(fAdmin);
        return insert > 0 ;
    }
}