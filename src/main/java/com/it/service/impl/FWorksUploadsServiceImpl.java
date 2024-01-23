package com.it.service.impl;

import com.it.entity.FWorksUploads;
import com.it.mapper.FWorksUploadsMapper;
import com.it.service.FWorksUploadsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 *
 */
@Service
public class FWorksUploadsServiceImpl implements FWorksUploadsService {
    @Resource
    private FWorksUploadsMapper fWorksUploadsMapper;

    @Override
    public boolean addUploads(FWorksUploads fWorksUploads) {
        Integer insert = fWorksUploadsMapper.insert(fWorksUploads);
        return insert > 0 ;
    }
}