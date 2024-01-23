package com.it.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FFarms;

import java.util.List;

public interface FFarmsService {

    boolean addFFarms(FFarms fFarms);



    List<FFarms> getFFarmsList(int start, int size,String workName);

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    Page<FFarms> selectPage(FFarms fFarms, int page, int limit);

    boolean deleteById(String ids);

    FFarms getOne(String id);

    boolean editById(FFarms fFarms);


}
