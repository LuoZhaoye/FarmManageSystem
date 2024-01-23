package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Evaluate;
import com.it.service.EvaluateService;
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
public class EvaluateServiceImpl implements EvaluateService {
    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private com.it.mapper.EvaluateMapper EvaluateMapper;


    @Override
    public Page<Evaluate> selectPage(Evaluate entity, int page, int limit) {
        EntityWrapper<Evaluate> searchInfo = new EntityWrapper<>();
        Page<Evaluate> pageInfo = new Page<>(page, limit);
        if (ItdragonUtils.stringIsNotBlack(entity.getItemId())) {
            searchInfo.like("itemId", entity.getItemId());
        }
        searchInfo.orderBy("time desc");
        List<Evaluate> resultList = EvaluateMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(Evaluate entity) {
        entity.setTime(DateUtil.getNowDateSS());
        entity.setUserName(itdragonUtils.getSessionUserName());
        Integer insert = EvaluateMapper.insert(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Evaluate entity) {
        Integer insert = EvaluateMapper.updateById(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteById(String ids) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = EvaluateMapper.deleteById(s);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Evaluate> getList(Evaluate entity) {
        EntityWrapper<Evaluate> wrapper = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(entity.getItemId())) {
            wrapper.like("itemId", entity.getItemId());
        }
        wrapper.orderBy("time desc");
        List<Evaluate> resultList = EvaluateMapper.selectList(wrapper);
        return resultList;
    }

    @Override
    public List<Evaluate> getListByItemId(String itemId) {
        EntityWrapper<Evaluate> wrapper = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(itemId)) {
            wrapper.like("itemId", itemId);
        }
        wrapper.orderBy("time desc");
        List<Evaluate> resultList = EvaluateMapper.selectList(wrapper);
        return resultList;

    }

    @Override
    public Integer num(String itemId) {
        EntityWrapper<Evaluate> wrapper = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(itemId)) {
            wrapper.like("itemId", itemId);
        }
        List<Evaluate> resultList = EvaluateMapper.selectList(wrapper);
        return resultList.size();

    }

    @Override
    public Evaluate getOne(String id) {
        Evaluate Evaluate = EvaluateMapper.selectById(id);
        if (Evaluate != null) {
            return Evaluate;
        } else {
            return new Evaluate();
        }
    }
}