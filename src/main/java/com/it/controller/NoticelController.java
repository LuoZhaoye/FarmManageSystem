package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Notice;
import com.it.service.NoticeService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈实列接口〉<br>
 *
 * @author
 */
@Controller
@RequestMapping("/notice")
public class NoticelController {
    @Autowired
    private NoticeService NoticeService;
    @Autowired
    private ItdragonUtils itdragonUtils;

    /**
     * 管理界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("notice/index");
        return mv;
    }

    /**
     * 管理界面列表数据异步加载接口
     *
     * @param entity
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("notice.do")
    public TableResultResponse reloadTable(Notice entity, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Notice> pageInfo = NoticeService.selectPage(entity, page, limit);
        for (Notice record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("title", record.getTitle());
            resultMap.put("content", record.getContent());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 19));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/addPage.do")
    public ModelAndView addPage(ModelAndView mv) {
        mv.setViewName("notice/addPage");
        return mv;
    }

    /**
     * 新增数据接口
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @PostMapping("/notice.do")
    public ResultResponse insert(Notice entity) {
        boolean result = NoticeService.insert(entity);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 编辑界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editPage.do")
    public ModelAndView editPage(ModelAndView mv, String id) {
        Notice notice = NoticeService.getOne(id);
        mv.addObject("notice", notice);
        mv.setViewName("notice/editPage");
        return mv;
    }

    /**
     * 编辑数据接口
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @PutMapping("/notice.do")
    public ResultResponse edit(Notice entity) {
        boolean result = NoticeService.editById(entity);
        if (!result) {
            return Result.resuleError("编辑失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除数据接口
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/notice.do")
    public ResultResponse delete(String ids) {
        boolean result = NoticeService.deleteById(ids);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }


}