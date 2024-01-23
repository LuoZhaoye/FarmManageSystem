package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.config.ITDragonShiroRealm;
import com.it.entity.Article;
import com.it.service.ArticleService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：〈文章管理相关接口/控制器〉
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService ArticleService;
    @Autowired
    private ItdragonUtils itdragonUtils;

    private static final transient Logger log = LoggerFactory.getLogger(ArticleController.class);
    /**
     * 文章管理首页页面跳转
     * （将数据库中所有的菜单信息查询出来封装好传给前台，前台通过
     * layui-tree插件现实菜单数据）
     *
     * @param mv
     * @return
     */
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/article/index");
        return mv;
    }

    /**
     * 异步加载文章信息列表
     *
     * @param article
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("article.do")
    public TableResultResponse reloadTable(int page, int limit, Article article) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        Page<Article> pageInfo = ArticleService.selectPage(article, page, limit);
        for (Article record : pageInfo.getRecords()) {
            Map<String, Object> resultMap = new HashMap<>(16);
            resultMap.put("id", record.getId());
            resultMap.put("title", record.getTitle());
            resultMap.put("info", record.getInfo());
            resultMap.put("type", record.getType());
            resultMap.put("content", record.getContent());
            resultMap.put("time", record.getTime() == null ? "" : record.getTime().substring(0, 11));
            infoList.add(resultMap);
        }
        return Result.tableResule(pageInfo.getTotal(), infoList);
    }

    /**
     * 新增文章页面弹窗
     *
     * @return
     */
    @RequestMapping("/addPage.do")
    public ModelAndView addPage(ModelAndView mv) {
        mv.setViewName("/article/addPage");
        return mv;
    }

    /**
     * 新增文章
     *
     * @param article
     * @return
     */
    @PostMapping("/article.do")
    @ResponseBody
    public ResultResponse insert(Article article) {
        boolean result = ArticleService.insert(article);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 编辑文章页面弹窗
     *
     * @param id
     * @return
     */
    @RequestMapping("/editPage.do")
    public ModelAndView editPage(String id, ModelAndView mv) {
        Article article = ArticleService.getOne(id);
        mv.addObject("article", article);
        mv.setViewName("/article/editPage");
        return mv;
    }

    /**
     * 编辑文章
     *
     * @param article
     * @return
     */
    @PutMapping("/article.do")
    @ResponseBody
    public ResultResponse edit(Article article) {
        boolean result = ArticleService.editById(article);
        if (!result) {
            return Result.resuleError("编辑失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @DeleteMapping("/article.do")
    @ResponseBody
    public ResultResponse delete(String id) {
        boolean result = ArticleService.deleteById(id);
        if (!result) {
            return Result.resuleError("删除失败");
        }
        return Result.resuleSuccess();
    }

    /**
     * 下载文章
     *
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping("/articleDetailDownLoad")
    public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            String projectPath = request.getServletContext().getRealPath("/");
            String filePath = ArticleService.downLoadOne(id, projectPath);
            File file = new File(filePath);
            String encoderstr = URLEncoder.encode(file.getName(), "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + encoderstr + "\"");
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/x-msdownload;");
            response.setCharacterEncoding("UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            InputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close(); //关闭文件流很关键
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    /**
     * 根据本地视频全路径，响应给浏览器1个视频
     */
    @RequestMapping("/showVideo")
    public void showVideo(HttpServletResponse response, @RequestParam("fileName")String fileName) throws UnsupportedEncodingException {
        System.out.println(fileName);
        ArticleService.show(response, fileName, "video");
    }
}