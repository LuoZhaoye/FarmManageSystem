package com.it.service.impl;

import com.it.entity.FProducesPicture;
import com.it.mapper.FProducesPictureMapper;
import com.it.service.FProducesPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 *
 */
@Service
public class FProducesPictureServiceImpl implements FProducesPictureService {
    @Resource
    private FProducesPictureMapper fProducesPictureMapper;

    @Override
    public boolean addFProducesPicture(FProducesPicture fProducesPicture) {
        Integer insert = fProducesPictureMapper.insert(fProducesPicture);
        return insert > 0 ;
    }
}