package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FAdmin;
import com.it.entity.FContestNotice;
import com.it.entity.Word;
import com.it.mapper.FAdminMapper;
import com.it.mapper.FContestNoticeMapper;
import com.it.service.FAdminService;
import com.it.service.FContestNoticeService;
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
public class FContestNoticeServiceImpl implements FContestNoticeService {
    @Resource
    private FContestNoticeMapper fContestNoticeMapper;

    @Override
    public boolean addFContestNotice(FContestNotice fContestNotice) {
        Integer insert = fContestNoticeMapper.insert(fContestNotice);
        return insert > 0 ;
    }

    @Override
    public List<FContestNotice> getFContestNoticeList(int start, int size) {
        return fContestNoticeMapper.getFContestNoticeList(start,size);
    }

    @Override
    public Page<FContestNotice> selectPage(FContestNotice fContestNotice, int page, int limit) {

        EntityWrapper<FContestNotice> searchInfo = new EntityWrapper<>();
        if (fContestNotice != null) {
            if (ItdragonUtils.stringIsNotBlack(fContestNotice.getContent())) {
                searchInfo.like("content", fContestNotice.getContent());
            }
//            if (ItdragonUtils.stringIsNotBlack( fContestNotice.getUpdateTime() )) {
//                searchInfo.between("update_time", fContestNotice.getUpdateTime());
//            }
        }
        searchInfo.orderBy("update_time desc");
        Page<FContestNotice> pageInfo = new Page<>(page, limit);
        List<FContestNotice> wordList = fContestNoticeMapper.selectPage(pageInfo, searchInfo);
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
            result = fContestNoticeMapper.deleteById(Integer.valueOf(s));
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    public FContestNotice getOne(String id) {
        FContestNotice word = fContestNoticeMapper.selectById(Integer.valueOf(id));
        return word;
    }



    @Override
    public boolean editById(FContestNotice entity) {
        FContestNotice word = fContestNoticeMapper.selectById(Integer.valueOf(entity.getId()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        word.setUpdateTime(format);
        word.setContent(entity.getContent());
        Integer integer = fContestNoticeMapper.updateById(word);
        return integer > 0;
    }
}