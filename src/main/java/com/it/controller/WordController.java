package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Word;
import com.it.service.UserService;
import com.it.service.WordService;
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
 * 描述：〈评论管理相关接口/控制器〉
 */
@Controller
@RequestMapping("/onlineLearning")
public class WordController {

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    /**
     * 评论管理首页页面跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        //查询出所有角色列表
        List<Word> roleList = wordService.getList(new Word());
        mv.addObject("roleList", roleList);
        mv.setViewName("systemSet/wordManager");
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
    @GetMapping("word.do")
    public TableResultResponse reloadTable(int page, int limit, Word word) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Word> pageInfo = wordService.selectPage(word, page, limit);
        for (Word record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("word", record.getWord());
            resultMap.put("worddesc", record.getWorddesc());
            resultMap.put("example", record.getExample() );
            resultMap.put("createdate", record.getCreatedate() );
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增单词 跳转界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/openWord.do")
    public ModelAndView addUserHouser(ModelAndView mv) {
        mv.setViewName("systemSet/addWord");
        return mv;
    }

    /**
     * 新增单词 保存
     *
     * @param word
     * @return
     */
    @ResponseBody
    @RequestMapping("/addWord.do")
    public ResultResponse addWord(Word word) {
        boolean result = wordService.insert(word);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }


    /**
     * 新增单词 保存
     *
     * @param mv
     * @return
     */
    @RequestMapping("/editOpenWord.do")
    public ModelAndView editWord(ModelAndView mv, String id) {
        Word one = wordService.getOne(id);
        mv.addObject("word", one);
        mv.setViewName("systemSet/editWord");
        return mv;
    }

    /**
     * 新增单词 保存
     *
     * @param word
     * @return
     */
    @ResponseBody
    @RequestMapping("/editWord.do")
    public ResultResponse editWord(Word word) {
        boolean result = wordService.editById(word);
        if (!result) {
            return Result.resuleError("修改失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除单词
     * 批量删除,单个删除通用
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/delWord.do")
    public ResultResponse delWord(String ids) {
        boolean result = wordService.deleteById(ids);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除单词
     * 批量删除,单个删除通用
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateTime.do")
    public ResultResponse updateTime(String ids) {
        boolean result = userService.updateTime();
        if (!result) {
            return Result.resuleError("更新失败");
        }
        return Result.resuleSuccess();
    }

    // ==========================收藏==========================


    /**
     * 添加收藏
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/wordMy.do")
    public ResultResponse wordMy(String ids) {
        boolean result = wordService.wordMy(ids);
        if (!result) {
            return Result.resuleError("收藏失败");
        }
        return Result.resuleSuccess();
    }


    /**
     * 添加收藏
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/cancelMyWord.do")
    public ResultResponse cancelMyWord(String ids) {
        boolean result = wordService.cancelMyWord(ids);
        if (!result) {
            return Result.resuleError("取消收藏失败");
        }
        return Result.resuleSuccess();
    }


    @RequestMapping("/getList3")
    public ModelAndView onlineLearning(ModelAndView mv, Integer curr, Integer size) {
        Word word = new Word();
        if(curr == null){
            curr = 1;
            size = 6;
        }
        int start = (curr - 1) * 6;
        List<Word> list = wordService.getList(word);
        int all = list.size();
        int i = all / 6;
        int i1 = all % 6;
        if ( i1 != 0 ){
            i ++;
        }
        List<Word> wordList = wordService.getList2(start,size);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", all);
        map.put("pages", i);
        map.put("size", 6);

        mv.addObject("pageUtil", map);
        mv.addObject("wordList", wordList);
        mv.setViewName("onlineLearn2");
        return mv;
    }



}