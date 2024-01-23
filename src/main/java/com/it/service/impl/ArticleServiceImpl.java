package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Article;
import com.it.mapper.ArticleMapper;
import com.it.service.ArticleService;
import com.it.util.DateUtil;
import com.it.util.ItdragonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ItdragonUtils itdragonUtils;
    @Resource
    private ArticleMapper ArticleMapper;

    @Override
    public Page<Article> selectPage(Article entity, int page, int limit) {
        EntityWrapper<Article> searchInfo = new EntityWrapper<>();
        Page<Article> pageInfo = new Page<>(page, limit);
        if (ItdragonUtils.stringIsNotBlack(entity.getTitle())) {
            searchInfo.eq("title", entity.getTitle());
        }
        if (ItdragonUtils.stringIsNotBlack(entity.getType())) {
            searchInfo.eq("type", entity.getType());
        }
        if (ItdragonUtils.stringIsNotBlack(entity.getTime())) {
            searchInfo.like("time", entity.getTime());
        }
        List<Article> resultList = ArticleMapper.selectPage(pageInfo, searchInfo);
        if (!resultList.isEmpty()) {
            pageInfo.setRecords(resultList);
        }
        return pageInfo;
    }

    @Override
    public boolean insert(Article entity) {
        entity.setTime(DateUtil.getNowDateSS());
        Integer insert = ArticleMapper.insert(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editById(Article entity) {
        Integer insert = ArticleMapper.updateById(entity);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(String ids) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = ArticleMapper.deleteById(s);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Article> getList(Article entity) {
        EntityWrapper<Article> wrapper = new EntityWrapper<>();
        if (ItdragonUtils.stringIsNotBlack(entity.getType())) {
            wrapper.eq("type", entity.getType());
        }
        List<Article> resultList = ArticleMapper.selectList(wrapper);
        return resultList;
    }


    @Override
    public Article getOne(String id) {
        Article Article = ArticleMapper.selectById(id);
        if (Article != null) {
            return Article;
        } else {
            return new Article();
        }
    }

    @Override
    public String downLoadOne(String id, String projectPath) {
        Article article = ArticleMapper.selectById(id);
        if (article != null) {
            return writeArticleToTxt(article, projectPath);
        } else {
            return null;
        }
    }

    public String writeArticleToTxt(Article article, String projectPath) {
        String filePath = projectPath + article.getId();
        String title = article.getTitle();
        String info = article.getInfo();
        String contentOrigin = article.getContent();
        String time = article.getTime();
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(filePath);
            fw.write(title);
            fw.write(delHTMLTag(contentOrigin));
            fw.write(time);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    /**
     * 响应文件
     * @param response
     * @param fileName  文件全路径
     * @param type  响应流类型
     */
    public void show(HttpServletResponse response, String fileName, String type) {
        try {
            fileName = delHTMLTag(fileName);
            FileInputStream fis = new FileInputStream(fileName); // 以byte流的方式打开文件
            int i = fis.available(); //得到文件大小
            byte[] data = new byte[i];
            fis.read(data);  //读数据
            response.setContentType(type + "/*"); //设置返回的文件类型
            OutputStream toClient = response.getOutputStream(); //得到向客户端输出二进制数据的对象
            toClient.write(data);  //输出数据
            toClient.flush();
            toClient.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件不存在");
        }
    }
}