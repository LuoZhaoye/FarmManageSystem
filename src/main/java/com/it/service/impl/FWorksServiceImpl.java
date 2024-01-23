package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FWorks;
import com.it.entity.FWorks;
import com.it.mapper.FWorksMapper;
import com.it.service.FWorksService;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 *
 */
@Service
public class FWorksServiceImpl implements FWorksService {
    @Resource
    private FWorksMapper fWorksMapper;

    @Override
    public boolean addFWorks(FWorks fWorks) {
        Integer insert = fWorksMapper.insert(fWorks);
        return insert > 0 ;
    }



    @Override
    public List<FWorks> getFWorksList(int start, int size, String workName) {
        return fWorksMapper.getFWorksList(start,size, workName);
    }
    @Override
    public List<FWorks> getFWorksList(String name, int start, int size, String workName) {
        return fWorksMapper.getFWorksList2(name,start,size, workName);
    }

    @Override
    public Page<FWorks> selectPage(FWorks fWorks, int page, int limit) {

        EntityWrapper<FWorks> searchInfo = new EntityWrapper<>();
        if (fWorks != null) {
//            if (ItdragonUtils.stringIsNotBlack(fWorks.getContent())) {
//                searchInfo.like("content", fWorks.getContent());
//            }
//            if (ItdragonUtils.stringIsNotBlack( fWorks.getUpdateTime() )) {
//                searchInfo.between("update_time", fWorks.getUpdateTime());
//            }
//            if (ItdragonUtils.stringIsNotBlack( String.valueOf(fWorks.getStatus() ))) {
//                searchInfo.eq("status", 1);
//            }
        }
        searchInfo.orderBy("update_time desc");
        Page<FWorks> pageInfo = new Page<>(page, limit);
        List<FWorks> wordList = fWorksMapper.selectPage(pageInfo, searchInfo);
        if (!wordList.isEmpty()) {
            pageInfo.setRecords(wordList);
        }
        return pageInfo;


//        return null;
    }

    @Override
    public boolean deleteById(String ids) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = fWorksMapper.deleteById(Integer.valueOf(s));
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    public FWorks getOne(String id) {
        FWorks word = fWorksMapper.selectById(Integer.valueOf(id));
        return word;
    }



    @Override
    public boolean editById(FWorks entity) {
        Integer integer = fWorksMapper.updateById(entity);
        return integer > 0;
    }
    
    
}