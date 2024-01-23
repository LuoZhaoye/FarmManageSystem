package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FFarmProduces;
import com.it.entity.FFarms;
import com.it.mapper.FFarmProducesMapper;
import com.it.service.FFarmProducesService;
import com.it.service.FFarmsService;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static com.it.util.DateUtil.dateToString;

/**
 * 〈实列接口〉<br>
 *
 * @author
 */
@Controller
@RequestMapping("/farmProduces")
public class FFarmProducesController {


    @Autowired
    private FFarmProducesService fFarmProducesService;

    @Autowired
    private FFarmsService fFarmsService;

    /**
     * 公告管理首页页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("systemSet/FFarmsProduceManager");
        return mv;
    }



    /**
     * 异步加载评论信息列表
     *
     * @param word
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("farmProducesList.do")
    public TableResultResponse reloadTable(int page, int limit, FFarmProduces word) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<FFarmProduces> pageInfo = fFarmProducesService.selectPage(word, page, limit);

        for (FFarmProduces record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("produceId", record.getProduceId());
            resultMap.put("produceName", record.getProduceName());
            resultMap.put("produceDiscribe", record.getProduceDiscribe());
            resultMap.put("producePictureId", record.getProducePictureId());
            resultMap.put("produceAvgPrice", record.getProduceAvgPrice());
            resultMap.put("farmId", record.getFarmId());
            resultMap.put("farmName", record.getFarmName());
            resultMap.put("createTime", record.getCreateTime() );
            resultMap.put("updateTime", record.getUpdateTime() );
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增农场产品 页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/openAddFarmProduce.do")
    public ModelAndView openAddFarm(ModelAndView mv) {
        // 获取所有农场
        Page<FFarms> fFarmsPage = fFarmsService.selectPage(new FFarms(), 0, 10000);
        List<FFarms> records = fFarmsPage.getRecords();
        mv.addObject("word", records);
        mv.setViewName("systemSet/addFarmsProduces");
        return mv;
    }

    // 新增产品
    @ResponseBody
    @PostMapping("/addFarmProduce.do")
    public ResultResponse addContestNotice(FFarmProduces fFarms) {
        String s = dateToString(new Date());
        fFarms.setCreateTime(s);
        fFarms.setUpdateTime(s);
        fFarms.setDeleted("0");
        boolean result = fFarmProducesService.addFFarmProduces(fFarms);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }





    /**
     * 打开修改弹框
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editOpenDisabledFarmProduces.do")
    public ModelAndView editOpenDisabledFarmProduces(ModelAndView mv, String id) {
        FFarmProduces one = fFarmProducesService.getOne(id);
        mv.addObject("word", one);
        // 获取所有农场
        Page<FFarms> fFarmsPage = fFarmsService.selectPage(new FFarms(), 0, 10000);
        List<FFarms> records = fFarmsPage.getRecords();
        mv.addObject("word2", records);
        mv.setViewName("systemSet/editFarmDisabledProduces");
        return mv;
    }

    /**
     * 打开修改弹框
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editOpenFarmProduces.do")
    public ModelAndView editOpenFarmProduces(ModelAndView mv, String id) {
        FFarmProduces one = fFarmProducesService.getOne(id);
        mv.addObject("word", one);
        // 获取所有农场
        Page<FFarms> fFarmsPage = fFarmsService.selectPage(new FFarms(), 0, 10000);
        List<FFarms> records = fFarmsPage.getRecords();
        mv.addObject("word2", records);
        mv.setViewName("systemSet/editFarmProduces");
        return mv;
    }

    /**
     * 修改公告
     *
     * @param word
     * @return
     */
    @ResponseBody
    @RequestMapping("/editFarmProduces.do")
    public ResultResponse editFarmProduces(FFarmProduces word) {
        boolean result = fFarmProducesService.editById(word);
        if (!result) {
            return Result.resuleError("修改失败");
        }
        return Result.resuleSuccess();
    }





    // 删除
    @ResponseBody
    @DeleteMapping("/delFarmsProduces.do")
    public ResultResponse delFarms(String ids) {
        boolean result = fFarmProducesService.deleteById(ids);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }


}