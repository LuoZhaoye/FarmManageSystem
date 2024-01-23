package com.it.controller;


import com.it.entity.*;
import com.it.service.*;
import com.it.util.ItdragonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 前台相关操作接口
 *
 * @version:
 * @Description:
 */
@Controller
public class ProesceniumController {
    private static final String GENERAL_USER = "035e6cd6738c42e5a4112d34e85e0832";//普通用户id
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private LogService logService;
    @Autowired
    private WbeParameterService wbeParameterService;
    @Autowired
    private ItdragonUtils itdragonUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private WordService wordService;


    @Autowired
    private FContestNoticeService fContestNoticeService;


    @Autowired
    private FFarmsService fFarmsService;


    @Autowired
    private FFarmProducesService fFarmProducesService;


    @Autowired
    private FWorksService worksService;


//*******************************************************************************************************************
//***** 公告数据 *****************************************************************************************************
//*******************************************************************************************************************
    @RequestMapping("/contestNotice")
    public ModelAndView contestNotice(ModelAndView mv, Integer curr, Integer size) {
        CommonMethods(mv);
        mv = getContestNotices(mv, curr, size);
        mv.setViewName("proscenium/contestNotice");
        return mv;
    }

    @RequestMapping("/contestNotices")
    public ModelAndView contestNotices(ModelAndView mv, Integer curr, Integer size) {
        CommonMethods(mv);
        mv = getContestNotices(mv, curr, size);
        mv.setViewName("proscenium/contestNotices");
        return mv;
    }


    public ModelAndView getContestNotices(ModelAndView mv,Integer curr, Integer size){
        if(curr == null){
            curr = 1;
            size = 6;
        }
        int start = (curr - 1) * 6;
        List<FContestNotice> list = fContestNoticeService.getFContestNoticeList(0,10000);
        int all = list.size();
        int i = all / 6;
        int i1 = all % 6;
        if ( i1 != 0 ){
            i ++;
        }
        List<FContestNotice> wordList = fContestNoticeService.getFContestNoticeList(start,size);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", all);
        map.put("pages", curr);
        map.put("size", 6);

        mv.addObject("pageUtil", map);
        mv.addObject("wordList", wordList);
        return mv;
    }


//*******************************************************************************************************************
//***** 农场数据 *****************************************************************************************************
//*******************************************************************************************************************
    @RequestMapping("/fFarmPage")
    public ModelAndView fFarmPage(ModelAndView mv,Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getFarmPage(mv, curr, size, workName);
        mv.setViewName("proscenium/fFarmPage");
        return mv;
    }

    @RequestMapping("/fFarmPages")
    public ModelAndView fFarmPages(ModelAndView mv,Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getFarmPage(mv, curr, size, workName);
        mv.setViewName("proscenium/fFarmPages");
        return mv;
    }


    public ModelAndView getFarmPage(ModelAndView mv,Integer curr, Integer size, String workName){
        if(curr == null){
            curr = 1;
            size = 6;
        }
        int start = (curr - 1) * 6;
        List<FFarms> list = fFarmsService.getFFarmsList(0,10000, workName);
        int all = list.size();
        int i = all / 6;
        int i1 = all % 6;
        if ( i1 != 0 ){
            i ++;
        }
        List<FFarms> wordList = fFarmsService.getFFarmsList(start,size, workName);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", all);
        map.put("pages", curr);
        map.put("size", 6);

        mv.addObject("workName", workName);
        mv.addObject("pageUtil", map);
        mv.addObject("wordList", wordList);
        return mv;
    }


//*******************************************************************************************************************
//***** 产品数据 *****************************************************************************************************
//*******************************************************************************************************************
    @RequestMapping("/fProductPage")
    public ModelAndView fProductPage(ModelAndView mv, Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getProductPage(mv, curr, size, workName);
        mv.setViewName("farm/fProductPage");
        return mv;
    }/**
     * 产品数据
     */
    @RequestMapping("/fProductPages")
    public ModelAndView fProductPages(ModelAndView mv, Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getProductPage(mv, curr, size, workName);
        mv.setViewName("farm/fProductPages");
        return mv;
    }


    public ModelAndView getProductPage(ModelAndView mv,Integer curr, Integer size, String workName){
        if(curr == null){
            curr = 1;
            size = 6;
        }
        int start = (curr - 1) * 6;
        List<FFarmProduces> list = fFarmProducesService.getFFarmsProducesList(0,10000, workName);
        int all = list.size();
        int i = all / 6;
        int i1 = all % 6;
        if ( i1 != 0 ){
            i ++;
        }
        List<FFarmProduces> wordList = fFarmProducesService.getFFarmsProducesList(start,size,workName);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", all);
        map.put("pages", curr);
        map.put("size", 6);

        mv.addObject("workName", workName);
        mv.addObject("pageUtil", map);
        mv.addObject("wordList", wordList);
        return mv;
    }



//*******************************************************************************************************************
//***** 比赛数据 *****************************************************************************************************
//*******************************************************************************************************************
    @RequestMapping("/fMatchPage")
    public ModelAndView fMatchPage(ModelAndView mv, Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getMatchPage(mv, curr, size, workName);
        mv.setViewName("farm/fMatchPage");
        return mv;
    }
    /**
     * 比赛数据
     */
    @RequestMapping("/fMatchPages")
    public ModelAndView fMatchPages(ModelAndView mv, Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getMatchPage(mv, curr, size, workName);
        mv.setViewName("farm/fMatchPages");
        return mv;
    }


    public ModelAndView getMatchPage(ModelAndView mv,Integer curr, Integer size, String workName){
        if(curr == null){
            curr = 1;
            size = 6;
        }
        int start = (curr - 1) * 6;
        List<FWorks> list = worksService.getFWorksList(0, 10000, workName);
//        List<FContestNotice> list = fContestNoticeService.getFContestNoticeList(0,10000);
        int all = list.size();
        int i = all / 6;
        int i1 = all % 6;
        if ( i1 != 0 ){
            i ++;
        }
        List<FWorks> wordList = worksService.getFWorksList(start,size,workName);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", all);
        map.put("pages", curr);
        map.put("size", 6);

        mv.addObject("workName", workName);
        mv.addObject("pageUtil", map);
        mv.addObject("wordList", wordList);
        return mv;
    }



//*******************************************************************************************************************
//***** 我的作品 *****************************************************************************************************
//*******************************************************************************************************************
    @RequestMapping("/fMyMatchPage")
    public ModelAndView fMyMatchPage(ModelAndView mv, Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getMyMatchPage(mv, curr, size, workName);
        mv.setViewName("farm/fMyMatchPage");
        return mv;
    }
    /**
     * 比赛数据
     */
    @RequestMapping("/fMyMatchPages")
    public ModelAndView fMyMatchPages(ModelAndView mv, Integer curr, Integer size, String workName) {
        CommonMethods(mv);
        mv = getMyMatchPage(mv, curr, size, workName);
        mv.setViewName("farm/fMyMatchPages");
        return mv;
    }

    public ModelAndView getMyMatchPage(ModelAndView mv,Integer curr, Integer size, String workName){

        User userInfo = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        String name = userInfo.getUserName();

        if(curr == null){
            curr = 1;
            size = 6;
        }
        int start = (curr - 1) * 6;
        List<FWorks> list = worksService.getFWorksList(name,0, 10000, workName);
        int all = list.size();
        int i = all / 6;
        int i1 = all % 6;
        if ( i1 != 0 ){
            i ++;
        }
        List<FWorks> wordList = worksService.getFWorksList(name,start,size, workName);
        for (int j = 0; j < wordList.size(); j++) {
            if( wordList.get(j).getStatus() == 0 ){
                wordList.get(j).setAwardCode("未完成");
            }else{
                wordList.get(j).setAwardCode("已完成");
            }
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("total", all);
        map.put("pages", curr);
        map.put("size", 6);

        mv.addObject("workName", workName);
        mv.addObject("pageUtil", map);
        mv.addObject("wordList", wordList);
        return mv;
    }


//    ********************************************************************************************************8
//    ********************************************************************************************************8
//    ********************************************************************************************************8
//    ********************************************************************************************************8



    /**
     * 前台注册页面跳转
     */
    @RequestMapping("/prosceniumReg")
    public ModelAndView prosceniumReg(ModelAndView mv) {
        CommonMethods(mv);
        mv.setViewName("prosceniumReg");
        return mv;
    }

    /**
     * 前台登录页面跳转
     */
    @RequestMapping("/loginShiro")
    public ModelAndView prosceniumLogin(ModelAndView mv) {
        CommonMethods(mv);
        mv.setViewName("prosceniumLogin");
        return mv;
    }

    /**
     * 页面有一些公用需求在这里抽取出来
     *
     * @param mv
     */
    public void CommonMethods(ModelAndView mv) {
        //网站参数
        WbeParameter wbeParameter = wbeParameterService.getWbeParameter();
        mv.addObject("wbeParameter", wbeParameter);
        //查询用户是否登录
        boolean gogin = itdragonUtils.isGogin();
        boolean is_general = false;
        if (gogin) {
            //查询用户是否非后台管理用户
            is_general = itdragonUtils.getSessionUser().getRoleId().equals(GENERAL_USER);
            mv.addObject("user", userService.selectByPrimaryKey(itdragonUtils.getSessionUserId()));
        }
        mv.addObject("is_loin", gogin);
        mv.addObject("is_general", is_general);

    }
}