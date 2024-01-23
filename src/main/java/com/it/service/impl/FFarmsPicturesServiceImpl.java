package com.it.service.impl;

import com.it.entity.FFarmsPictures;
import com.it.mapper.FFarmsPicturesMapper;
import com.it.service.FFarmsPicturesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 *
 */
@Service
public class FFarmsPicturesServiceImpl implements FFarmsPicturesService {
    @Resource
    private FFarmsPicturesMapper fFarmsPicturesMapper;

    @Override
    public boolean addFFarmsPictures(FFarmsPictures fFarmsPictures) {
        Integer insert = fFarmsPicturesMapper.insert(fFarmsPictures);
        return insert > 0 ;
    }
}