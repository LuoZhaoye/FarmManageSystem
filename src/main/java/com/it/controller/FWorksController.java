package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.FFarmProduces;
import com.it.entity.FFarms;
import com.it.entity.FWorks;
import com.it.entity.User;
import com.it.service.FFarmProducesService;
import com.it.service.FWorksService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

import static com.it.util.DateUtil.dateToString;

/**
 * 〈实列接口〉<br>
 *
 * @author
 */
@Controller
@RequestMapping("/works")
public class FWorksController {

    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private FWorksService fWorksService;


    /**
     * 上传作品-页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/openWork.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("farm/openWork");
        return mv;
    }

    // 新增作品
    @ResponseBody
    @PostMapping("/addWork.do")
    public ResultResponse addWork(FWorks fWorks) {
        User userInfo = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        String name = userInfo.getUserName();
        String s = dateToString(new Date());
        fWorks.setCreateTime(s);
        fWorks.setUpdateTime(s);
        fWorks.setAuthorId(name);
        boolean result = fWorksService.addFWorks(fWorks);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    // 新增作品
    @ResponseBody
    @PostMapping("/editWork.do")
    public ResultResponse editWork(FWorks fWorks) {

        FWorks one = fWorksService.getOne(String.valueOf(fWorks.getId()));

        one.setUpdateTime(dateToString(new Date()));
        one.setWorkName(fWorks.getWorkName());

        one.setWorksTypeId(fWorks.getWorksTypeId());

        one.setImage(fWorks.getImage());
        one.setStatus(fWorks.getStatus());
        one.setIdea(fWorks.getIdea());

        boolean result = fWorksService.editById(one);
        if (!result) {
            return Result.resuleError("修改失败");
        }
        return Result.resuleSuccess();
    }


    /**
     * 打开 查看作品 弹框
     *
     * @param mv
     * @return
     */
    @RequestMapping("/openDisabledMatch.do")
    public ModelAndView openDisabledMatch(ModelAndView mv, String id) {
        FWorks one = fWorksService.getOne(id);
        mv.addObject("word", one);
        mv.setViewName("farm/openDisabledMatch");
        return mv;
    }

    /**
     * 打开 编辑作品 弹框
     *
     * @param mv
     * @return
     */
    @RequestMapping("/openMatch.do")
    public ModelAndView openMatch(ModelAndView mv, String id) {
        FWorks one = fWorksService.getOne(id);
        mv.addObject("word", one);
        mv.setViewName("farm/openMatch");
        return mv;
    }

}