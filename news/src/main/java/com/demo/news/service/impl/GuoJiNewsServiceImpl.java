package com.demo.news.service.impl;

import com.demo.news.entity.News;
import com.demo.news.mapper.GuoJiNewsMapper;
import com.demo.news.service.GuoJiNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("guoJiNewsService")
public class GuoJiNewsServiceImpl implements GuoJiNewsService {

    @Autowired
    private GuoJiNewsMapper guoJiNewsMapper;

    @Override
    public void insertNews(News news) {
        guoJiNewsMapper.insertNews(news);
    }

    @Override
    public void insertNewsWithImg(News news) {
        guoJiNewsMapper.insertNewsWithImg(news);
    }

    @Override
    public void deleteNews() {
        guoJiNewsMapper.deleteNews();
    }

    @Override
    public List<News> queryNews(int type, int end) {
        return guoJiNewsMapper.queryNews(type,end);
    }

    @Override
    public List<News> queryAllNews() {
        return guoJiNewsMapper.queryAllNews();
    }

    @Override
    public News queryNewsWithImg(int type) {
        return guoJiNewsMapper.queryWithImg(type);
    }


}
