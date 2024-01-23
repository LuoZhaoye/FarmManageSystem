package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FAdmin;
import com.it.entity.FContestNotice;
import com.it.entity.Word;
import com.it.service.FAdminService;
import com.it.service.FContestNoticeService;
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
@RequestMapping("/contestNotice")
public class FContestNoticeController {

    @Autowired
    private FContestNoticeService fContestNoticeService;

    /**
     * 公告管理首页页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("systemSet/contestNoticeManager");
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
    @GetMapping("contestNoticeList.do")
    public TableResultResponse reloadTable(int page, int limit, FContestNotice word) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<FContestNotice> pageInfo = fContestNoticeService.selectPage(word, page, limit);

        for (FContestNotice record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("content", record.getContent());
            resultMap.put("create_time", record.getCreateTime() );
            resultMap.put("update_time", record.getUpdateTime() );
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }


    /**
     * 新增公告 页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/openAddContestNotice.do")
    public ModelAndView openAddContestNotice(ModelAndView mv) {
        mv.setViewName("systemSet/addContestNotice");
        return mv;
    }

    // 新增
    @ResponseBody
    @PostMapping("/addContestNotice.do")
    public ResultResponse addContestNotice(FContestNotice fContestNotice) {
        String s = dateToString(new Date());
        fContestNotice.setCreateTime(s);
        fContestNotice.setUpdateTime(s);
        boolean result = fContestNoticeService.addFContestNotice(fContestNotice);
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
    @RequestMapping("/editOpenContestNotice.do")
    public ModelAndView editOpenContestNotice(ModelAndView mv, String id) {
        FContestNotice one = fContestNoticeService.getOne(id);
        mv.addObject("word", one);
        mv.setViewName("systemSet/editContestNotice");
        return mv;
    }

    /**
     * 修改公告
     *
     * @param word
     * @return
     */
    @ResponseBody
    @RequestMapping("/editContestNotice.do")
    public ResultResponse editContestNotice(FContestNotice word) {
        boolean result = fContestNoticeService.editById(word);
        if (!result) {
            return Result.resuleError("修改失败");
        }
        return Result.resuleSuccess();
    }

    // 修改


    // 删除
    @ResponseBody
    @DeleteMapping("/delContestNotice.do")
    public ResultResponse delContestNotice(String ids) {
        boolean result = fContestNoticeService.deleteById(ids);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }



}