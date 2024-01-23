package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FAdmin;
import com.it.entity.FContestNotice;
import com.it.entity.Word;

import java.util.List;


public interface FContestNoticeService {

    boolean addFContestNotice(FContestNotice fContestNotice);



    List<FContestNotice> getFContestNoticeList(int start, int size);

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    Page<FContestNotice> selectPage(FContestNotice fContestNotice, int page, int limit);

    boolean deleteById(String ids);

    FContestNotice getOne(String id);

    boolean editById(FContestNotice fContestNotice);
}
