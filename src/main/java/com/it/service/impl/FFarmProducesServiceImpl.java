package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FFarmProduces;
import com.it.entity.FFarms;
import com.it.mapper.FFarmProducesMapper;
import com.it.service.FFarmProducesService;
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
public class FFarmProducesServiceImpl implements FFarmProducesService {
    @Resource
    private FFarmProducesMapper fFarmProducesMapper;

    @Override
    public boolean addFFarmProduces(FFarmProduces fFarmProduces) {
        Integer insert = fFarmProducesMapper.insert(fFarmProduces);
        return insert > 0 ;
    }

    @Override
    public List<FFarmProduces> getFFarmsProducesList(int start, int size, String workName) {
        return fFarmProducesMapper.getFFarmsProducesList(start, size, workName);
    }

    @Override
    public Page<FFarmProduces> selectPage(FFarmProduces fFarms, int page, int limit) {
        EntityWrapper<FFarmProduces> searchInfo = new EntityWrapper<>();
        if (fFarms != null) {
            if (ItdragonUtils.stringIsNotBlack(fFarms.getFarmName())) {
                searchInfo.like("farm_name", fFarms.getFarmName());
            }
            if (ItdragonUtils.stringIsNotBlack(fFarms.getProduceName())) {
                searchInfo.like("produce_name", fFarms.getProduceName());
            }
            if (ItdragonUtils.stringIsNotBlack(fFarms.getProduceDiscribe())) {
                searchInfo.like("produce_discribe", fFarms.getProduceDiscribe());
            }
        }
        searchInfo.orderBy("update_time desc");
        Page<FFarmProduces> pageInfo = new Page<>(page, limit);
        List<FFarmProduces> wordList = fFarmProducesMapper.selectPage(pageInfo, searchInfo);
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
            result = fFarmProducesMapper.deleteById(Integer.valueOf(s));
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FFarmProduces getOne(String id) {
        FFarmProduces fFarmProduces = fFarmProducesMapper.selectById(id);
        return fFarmProduces;
    }

    @Override
    public boolean editById(FFarmProduces fFarms) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        fFarms.setUpdateTime(format);
        Integer integer = fFarmProducesMapper.updateById(fFarms);
        return integer > 0;
    }


}