package com.it.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FWorks;
import java.util.List;

public interface FWorksService {


    boolean addFWorks(FWorks fWorks);

    List<FWorks> getFWorksList(int start, int size, String workName);

    List<FWorks> getFWorksList(String name, int start, int size, String workName);

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    Page<FWorks> selectPage(FWorks fWorks, int page, int limit);

    boolean deleteById(String ids);

    FWorks getOne(String id);

    boolean editById(FWorks fWorks);
}
