package com.it.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FFarmProduces;
import com.it.entity.FFarms;

import java.util.List;

public interface FFarmProducesService {

    boolean addFFarmProduces(FFarmProduces fFarmProduces);

    List<FFarmProduces> getFFarmsProducesList(int start, int size, String workName);

    Page<FFarmProduces> selectPage(FFarmProduces fFarms, int page, int limit);

    boolean deleteById(String ids);

    FFarmProduces getOne(String id);

    boolean editById(FFarmProduces fFarms);
}
