package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Leave;
import com.it.entity.Reply;
import com.it.service.LeaveService;
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
 * 描述：〈留言讨论相关接口/控制器〉
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private ItdragonUtils itdragonUtils;

    /**
     * 留言信息管理首页页面跳转
     * （将数据库中所有的菜单信息查询出来封装好传给前台，前台通过
     * layui-tree插件现实菜单数据）
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/leave/index");
        return mv;
    }

    /**
     * 回复列表页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/replyIndex.do")
    public ModelAndView replyIndex(ModelAndView mv, String leaveId) {
        mv.addObject("leaveId", leaveId);
        mv.setViewName("/leave/replyIndex");
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
    @GetMapping("leave.do")
    public TableResultResponse reloadTable(Leave entity, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Leave> pageInfo = leaveService.selectPage(entity, page, limit);
        for (Leave record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("userName", record.getUserName());
            resultMap.put("content", record.getContent());
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
    @GetMapping("replyLoad.do")
    public TableResultResponse reloadTable(Reply entity, int page, int limit) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Reply> pageInfo = leaveService.selectPage(entity, page, limit);
        for (Reply record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("userName", record.getUserName());
            resultMap.put("content", record.getContent());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 19));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 回复
     */
    @RequestMapping("/replyPage")
    public ModelAndView replyPage(ModelAndView mv, String leaveId) {
        mv.addObject("leaveId", leaveId);
        mv.setViewName("proscenium/replyPage");
        return mv;
    }

    /**
     * 新增留言
     *
     * @param leave
     * @return
     */
    @PostMapping("/leave.do")
    @ResponseBody
    public ResultResponse insert(Leave leave) {
        boolean result = leaveService.insert(leave);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    @DeleteMapping("/leave.do")
    @ResponseBody
    public ResultResponse delete(String id) {
        boolean result = leaveService.deleteById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 新增回复
     *
     * @param reply
     * @return
     */
    @PostMapping("/reply.do")
    @ResponseBody
    public ResultResponse insert(Reply reply) {
        boolean result = leaveService.insert(reply);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除回复
     *
     * @param id
     * @return
     */
    @DeleteMapping("/reply.do")
    @ResponseBody
    public ResultResponse deleteReply(String id) {
        boolean result = leaveService.delReplyById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }
}