package com.demo.news.service.impl;

import com.demo.news.entity.News;
import com.demo.news.mapper.JunShiNewsMapper;
import com.demo.news.service.JunShiNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("junShiNewsService")
public class JunShiNewsServiceImpl implements JunShiNewsService {

    @Autowired
    private JunShiNewsMapper junShiNewsMapper;

    @Override
    public void insertNews(News news) {
        junShiNewsMapper.insertNews(news);
    }

    @Override
    public void deleteNews() {
        junShiNewsMapper.deleteNews();
    }

    @Override
    public List<News> queryNews(int type, int end) {
        return junShiNewsMapper.queryNews(type,end);
    }

    @Override
    public News queryNewsWithImg(int type) {
        return junShiNewsMapper.queryWithImg(type);
    }

}
