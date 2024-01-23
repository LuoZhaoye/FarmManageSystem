package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.it.entity.Collect;
import com.it.entity.Praise;
import com.it.mapper.CollectMapper;
import com.it.mapper.PraiseMapper;
import com.it.service.CollectService;
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
public class CollectServiceImpl implements CollectService {

    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private PraiseMapper praiseMapper;

    @Override
    public boolean insert(Collect entity) {
        entity.setUserId(itdragonUtils.getSessionUserId());
        entity.setTime(DateUtil.getNowDateSS());
        Integer insert = collectMapper.insert(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(String ids) {
        EntityWrapper<Collect> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", itdragonUtils.getSessionUserId());
        wrapper.eq("itemId", ids);
        Integer delete = collectMapper.delete(wrapper);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Collect> getList() {
        EntityWrapper<Collect> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", itdragonUtils.getSessionUserId());
        List<Collect> resultList = collectMapper.selectList(wrapper);
        return resultList;
    }

    @Override
    public boolean isCollect(String itemId) {
        EntityWrapper<Collect> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", itdragonUtils.getSessionUserId());
        wrapper.eq("itemId", itemId);
        List<Collect> resultList = collectMapper.selectList(wrapper);
        if (resultList.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(Praise entity) {
        entity.setUserId(itdragonUtils.getSessionUserId());
        Integer insert = praiseMapper.insert(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePraiseById(String id) {
        EntityWrapper<Praise> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", itdragonUtils.getSessionUserId());
        wrapper.eq("itemId", id);
        Integer delete = praiseMapper.delete(wrapper);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isPraise(String itemId) {
        EntityWrapper<Praise> wrapper = new EntityWrapper<>();
        wrapper.eq("userId", itdragonUtils.getSessionUserId());
        wrapper.eq("itemId", itemId);
        List<Praise> resultList = praiseMapper.selectList(wrapper);
        if (resultList.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Integer getPraiseNum(String itemId) {
        EntityWrapper<Praise> wrapper = new EntityWrapper<>();
        wrapper.eq("itemId", itemId);
        List<Praise> resultList = praiseMapper.selectList(wrapper);
        return resultList.size();
    }
}