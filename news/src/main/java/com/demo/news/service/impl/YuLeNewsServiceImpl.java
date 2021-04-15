package com.demo.news.service.impl;

import com.demo.news.entity.News;
import com.demo.news.entity.Songs;
import com.demo.news.mapper.YuLeNewsMapper;
import com.demo.news.service.YuLeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("yuLeNewsService")
public class YuLeNewsServiceImpl implements YuLeNewsService {

    @Autowired
    private YuLeNewsMapper yuLeNewsMapper;

    @Override
    public void insertNews(News news) {
        yuLeNewsMapper.insertNews(news);
    }

    @Override
    public void deleteNews() {
        yuLeNewsMapper.deleteNews();
    }

    @Override
    public List<News> queryNews(int type, int end) {
        return yuLeNewsMapper.queryNews(type,end);
    }

    @Override
    public News queryNewsWithImg(int type) {
        return yuLeNewsMapper.queryWithImg(type);
    }

    @Override
    public void insertSongs(Songs songs) {
        yuLeNewsMapper.insertSongs(songs);
    }

    @Override
    public List<Songs> querySongs(int type, int end) {
        return yuLeNewsMapper.querySongs(type,end);
    }
}
