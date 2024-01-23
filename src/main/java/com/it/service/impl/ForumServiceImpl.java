package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Forum;
import com.it.mapper.ForumMapper;
import com.it.service.ForumService;
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
public class ForumServiceImpl implements ForumService {

    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private ForumMapper ForumMapper;

    @Override
    public Page<Forum> selectPage(Forum entity, int page, int limit) {
        EntityWrapper<Forum> searchInfo = new EntityWrapper<>();
        Page<Forum> pageInfo = new Page<>(page, limit);
        if (ItdragonUtils.stringIsNotBlack(entity.getTitle())) {
            searchInfo.like("title", entity.getTitle());
        }
        if (ItdragonUtils.stringIsNotBlack(entity.getUserName())) {
            searchInfo.eq("userName", entity.getUserName());
        }
        if (ItdragonUtils.stringIsNotBlack(entity.getTeacher())) {
            searchInfo.eq("teacher", entity.getTeacher()).or();
            searchInfo.isNull("teacher").clone();
        }
        searchInfo.orderBy("time desc");
        List<Forum> resultList = ForumMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(Forum entity) {
        entity.setTime(DateUtil.getNowDateSS());
        entity.setUserName(itdragonUtils.getSessionUserName());
        Integer insert = ForumMapper.insert(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editById(Forum entity) {
        Integer insert = ForumMapper.updateById(entity);
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
            result = ForumMapper.deleteById(s);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Forum> getList(Forum entity) {
        EntityWrapper<Forum> wrapper = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(entity.getTitle())) {
            wrapper.eq("title", entity.getTitle());
        }
        if (ItdragonUtils.stringIsNotBlack(entity.getUserName())) {
            wrapper.eq("userName", entity.getUserName());
        }
        wrapper.orderBy("time desc");
        List<Forum> resultList = ForumMapper.selectList(wrapper);
        return resultList;
    }

    @Override
    public Forum getOne(String id) {
        return ForumMapper.selectById(id);
    }


}