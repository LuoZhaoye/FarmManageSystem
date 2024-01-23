package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FContestNotice;
import com.it.entity.FFarms;
import com.it.mapper.FFarmsMapper;
import com.it.service.FFarmsService;
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
public class FFarmsServiceImpl implements FFarmsService {
    @Resource
    private FFarmsMapper fFarmsMapper;

    @Override
    public boolean addFFarms(FFarms fFarms) {
        Integer insert = fFarmsMapper.insert(fFarms);
        return insert > 0 ;
    }

    @Override
    public List<FFarms> getFFarmsList(int start, int size,String workName) {
        return fFarmsMapper.getFFarmsList(start, size, workName);
    }

    @Override
    public Page<FFarms> selectPage(FFarms fFarms, int page, int limit) {

        EntityWrapper<FFarms> searchInfo = new EntityWrapper<>();
        if (fFarms != null) {
            if (ItdragonUtils.stringIsNotBlack(fFarms.getFarmName())) {
                searchInfo.like("farm_name", fFarms.getFarmName());
            }
            if (ItdragonUtils.stringIsNotBlack(fFarms.getFarmer())) {
                searchInfo.like("farmer", fFarms.getFarmer());
            }
            if (ItdragonUtils.stringIsNotBlack(fFarms.getFarmAddress())) {
                searchInfo.like("farm_address", fFarms.getFarmAddress());
            }
        }
        searchInfo.orderBy("update_time desc");
        Page<FFarms> pageInfo = new Page<>(page, limit);
        List<FFarms> wordList = fFarmsMapper.selectPage(pageInfo, searchInfo);
        if (!wordList.isEmpty()) {
            pageInfo.setRecords(wordList);
        }
        return pageInfo;
    }

    @Override
    public boolean deleteById(String ids) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = fFarmsMapper.deleteById(Integer.valueOf(s));
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FFarms getOne(String id) {
        FFarms word = fFarmsMapper.selectById(Integer.valueOf(id));
        return word;
    }

    @Override
    public boolean editById(FFarms fFarms) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        fFarms.setUpdateTime(format);
        Integer integer = fFarmsMapper.updateById(fFarms);
        return integer > 0;
    }
}