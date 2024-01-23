package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FContestNotice;
import com.it.entity.FFarms;
import com.it.service.FContestNoticeService;
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
@RequestMapping("/farms")
public class FFarmsController {

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
        mv.setViewName("systemSet/FFarmsManager");
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
    @GetMapping("farmsList.do")
    public TableResultResponse reloadTable(int page, int limit, FFarms word) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<FFarms> pageInfo = fFarmsService.selectPage(word, page, limit);

        for (FFarms record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("farmId", record.getFarmId());
            resultMap.put("farmName", record.getFarmName());
            resultMap.put("farmer", record.getFarmer());
            resultMap.put("farmPictureId", record.getFarmPictureId());
            resultMap.put("farmTel", record.getFarmTel());
            resultMap.put("farmAddress", record.getFarmAddress());
            resultMap.put("produceId", record.getProduceId());
            resultMap.put("createTime", record.getCreateTime() );
            resultMap.put("updateTime", record.getUpdateTime() );
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }


    /**
     * 新增农场 页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/openAddFarm.do")
    public ModelAndView openAddFarm(ModelAndView mv) {
        mv.setViewName("systemSet/addFarm");
        return mv;
    }

    // 新增
    @ResponseBody
    @PostMapping("/addFarm.do")
    public ResultResponse addContestNotice(FFarms fFarms) {
        String s = dateToString(new Date());
        fFarms.setCreateTime(s);
        fFarms.setUpdateTime(s);
        fFarms.setDeleted("0");
        boolean result = fFarmsService.addFFarms(fFarms);
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
    @RequestMapping("/editOpenDisabledFarms.do")
    public ModelAndView editOpenDisabledFarms(ModelAndView mv, String id) {
        FFarms one = fFarmsService.getOne(id);
        mv.addObject("word", one);
        mv.setViewName("systemSet/editDisabledFarm");
        return mv;
    }
    /**
     * 打开修改弹框
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editOpenFarms.do")
    public ModelAndView editOpenFarms(ModelAndView mv, String id) {
        FFarms one = fFarmsService.getOne(id);
        mv.addObject("word", one);
        mv.setViewName("systemSet/editFarm");
        return mv;
    }

    /**
     * 修改公告
     *
     * @param word
     * @return
     */
    @ResponseBody
    @RequestMapping("/editFarms.do")
    public ResultResponse editFarms(FFarms word) {
        boolean result = fFarmsService.editById(word);
        if (!result) {
            return Result.resuleError("修改失败");
        }
        return Result.resuleSuccess();
    }


    // 删除
    @ResponseBody
    @DeleteMapping("/delFarms.do")
    public ResultResponse delFarms(String ids) {
        boolean result = fFarmsService.deleteById(ids);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }


}