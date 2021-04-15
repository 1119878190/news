package com.demo.news.service.impl;

import com.demo.news.entity.News;
import com.demo.news.mapper.GuoNeiNewsMapper;
import com.demo.news.service.GuoNeiNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("guoNeiService")
public class GuoNeiNewsServiceImpl implements GuoNeiNewsService {


    @Autowired
    private GuoNeiNewsMapper guoNeiNewsMapper;

    @Override
    public void insertNews(News news) {
        guoNeiNewsMapper.insertNews(news);
    }

    @Override
    public void deleteNews() {
        guoNeiNewsMapper.deleteNews();
    }

    @Override
    public List<News> queryAllNews() {
        return guoNeiNewsMapper.queryAllNews();
    }

    @Override
    public List<News> queryNews(int type, int end) {
        return guoNeiNewsMapper.queryNews(type,end);
    }

    @Override
    public News queryNewsWithImg(int type) {
        return guoNeiNewsMapper.queryNewsWithImg(type);
    }


}
