package com.it.service.impl;

import com.it.entity.FAdmin;
import com.it.entity.FContests;
import com.it.mapper.FAdminMapper;
import com.it.mapper.FContestsMapper;
import com.it.service.FAdminService;
import com.it.service.FContestsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 *
 */
@Service
public class FContestsServiceImpl implements FContestsService {
    @Resource
    private FContestsMapper fContestsMapper;

    @Override
    public boolean addFContests(FContests fContests) {
        Integer insert = fContestsMapper.insert(fContests);
        return insert > 0 ;
    }
}