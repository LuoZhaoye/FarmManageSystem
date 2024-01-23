package com.it.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Role;
import com.it.entity.User;
import com.it.entity.Word;
import com.it.entity.WordMy;
import com.it.mapper.UserMapper;
import com.it.mapper.WordMapper;
import com.it.mapper.WordMyMapper;
import com.it.service.WordService;
import com.it.util.ItdragonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 *
 */
@Service
public class WordServiceImpl implements WordService {
    @Resource
    private WordMapper wordMapper;
    @Resource
    private WordMyMapper wordMyMapper;
    @Autowired
    private ItdragonUtils itdragonUtils;

    @Override
    public Page<Word> selectPage(Word word, int page, int limit) {

        EntityWrapper<Word> searchInfo = new EntityWrapper<>();
        if (word != null) {
            if (ItdragonUtils.stringIsNotBlack(word.getWord())) {
                searchInfo.like("word", word.getWord());
            }
            if (ItdragonUtils.stringIsNotBlack( word.getWorddesc() )) {
                searchInfo.like("worddesc", word.getWorddesc());
            }
            if (ItdragonUtils.stringIsNotBlack( word.getExample() )) {
                searchInfo.like("example", word.getExample()  );
            }
        }
        searchInfo.orderBy("createdate desc");
        Page<Word> pageInfo = new Page<>(page, limit);
        List<Word> wordList = wordMapper.selectPage(pageInfo, searchInfo);
        if (!wordList.isEmpty()) {
            pageInfo.setRecords(wordList);
        }
        return pageInfo;


//        return null;
    }

    @Override
    public boolean insert(Word word) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        word.setCreatedate(format);
        int result = wordMapper.insert(word);
        return result > 0;
    }

    @Override
    public boolean editById(Word entity) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        entity.setCreatedate(format);
        Integer integer = wordMapper.updateById(entity);
        return integer > 0;
    }

    @Override
    public boolean deleteById(String ids) {
        String[] idList = ids.split(",");
        int result = 0;
        for (String s : idList) {
            result = wordMapper.deleteById(s);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Word> getList(Word entity) {
        return wordMapper.getList("");
    }

    @Override
    public List<Word> getList3() {
        User userInfo = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        String id = userInfo.getId();
        return wordMapper.getList3(id);
    }

    @Override
    public List<Word> getList2(int start, int size) {
        User userInfo = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        String id = userInfo.getId();
        return wordMapper.getList2(id, start, size);
    }

    @Override
    public List<Word> getList4(int start, int size) {
        User userInfo = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        String id = userInfo.getId();
        return wordMapper.getList4(id, start, size);
    }

    @Override
    public Word getOne(String id) {
        Word word = wordMapper.selectById(id);
        return word;
    }

    @Override
    public boolean wordMy(String id) {
        User userInfo = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        String userid = userInfo.getId();
        WordMy wordMy = new WordMy();
        wordMy.setWordid(id);
        wordMy.setUserid(userid);
        int result = wordMyMapper.insert(wordMy);
        return result > 0;
    }

    @Override
    public boolean cancelMyWord(String id) {
        User userInfo = (User) itdragonUtils.getShiroSession().getAttribute("userInfo");
        String userid = userInfo.getId();
        WordMy wordMy = new WordMy();
        wordMy.setWordid(id);
        wordMy.setUserid(userid);
        int del = wordMyMapper.del(userid, id);
        return del > 0;
    }


}