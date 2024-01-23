package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Forum;
import com.it.service.ForumService;
import com.it.service.UserService;
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
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService ForumService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private UserService userService;

    /**
     * 管理界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("forum/index");
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
    @GetMapping("forum.do")
    public TableResultResponse reloadTable(Forum entity, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        if ("267b5a862b534ffea61b72f90bdcf6cc".equals(itdragonUtils.getSessionUser().getRoleId())) {
            entity.setTeacher(itdragonUtils.getSessionUserName());
        }
        Page<Forum> pageInfo = ForumService.selectPage(entity, page, limit);
        for (Forum record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("title", record.getTitle());
            resultMap.put("userName", record.getUserName());
            resultMap.put("img", record.getImg());
            resultMap.put("userImg", record.getUserImg());
            resultMap.put("content", record.getContent());
            resultMap.put("teacher", ItdragonUtils.stringIsNotBlack(record.getTeacher()) ? record.getTeacher() : " -未指定教师-");
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 19));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
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
    @GetMapping("forumPro.do")
    public TableResultResponse forumPro(Forum entity, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        entity.setUserName(itdragonUtils.getSessionUserName());
        Page<Forum> pageInfo = ForumService.selectPage(entity, page, limit);
        for (Forum record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("title", record.getTitle());
            resultMap.put("userName", record.getUserName());
            resultMap.put("img", record.getImg());
            resultMap.put("userImg", record.getUserImg());
            resultMap.put("content", record.getContent());
            resultMap.put("teacher", ItdragonUtils.stringIsNotBlack(record.getTeacher()) ? record.getTeacher() : " -未指定教师-");
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
        mv.setViewName("forum/addPage");
        return mv;
    }

    /**
     * 新增数据接口
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @PostMapping("/forum.do")
    public ResultResponse insert(Forum entity) {
        entity.setUserImg(userService.selectByPrimaryKey(itdragonUtils.getSessionUserId()).getImgUrl());
        boolean result = ForumService.insert(entity);
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
        Forum forum = ForumService.getOne(id);
        mv.addObject("forum", forum);
        mv.setViewName("forum/editPage");
        return mv;
    }

    /**
     * 编辑界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/replyPage.do")
    public ModelAndView replyPage(ModelAndView mv, String id) {
        Forum forum = ForumService.getOne(id);
        mv.addObject("forum", forum);
        mv.setViewName("forum/replyPage");
        return mv;
    }

    /**
     * 编辑数据接口
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @PutMapping("/forum.do")
    public ResultResponse edit(Forum entity) {
        boolean result = ForumService.editById(entity);
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
    @DeleteMapping("/forum.do")
    public ResultResponse delete(String ids) {
        boolean result = ForumService.deleteById(ids);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 详情信息界面跳转接口
     *
     * @param mv
     * @return
     */
    @RequestMapping("/details.do")
    public ModelAndView details(ModelAndView mv, String id) {
        Forum forum = ForumService.getOne(id);
        mv.addObject("forum", forum);
        mv.setViewName("forum/details");
        return mv;
    }
}