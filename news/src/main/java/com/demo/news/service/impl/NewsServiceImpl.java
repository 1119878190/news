package com.demo.news.service.impl;

import com.demo.news.entity.News;
import com.demo.news.mapper.NewsMapper;
import com.demo.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    //保存新闻
    @Override
    public void insertNews(News news) {
        newsMapper.insertNews(news);
    }

    //插入带图片的新闻
    @Override
    public void insertNewsWithImg(News news) {
        newsMapper.insertNewsWithImg(news);
    }

    //查询热点新闻
    @Override
    public List<News> queryHotNews() {
        return newsMapper.queryHotNews();
    }

    @Override
    public List<News> queryAllNews() {
        return newsMapper.queryAllNews();
    }

    //查询数据库是否有数据
    @Override
    public int queryCount(int type) {
        return newsMapper.queryCount(type);
    }

    //删除过期数据(昨天零点之前的数据)
    @Override
    public void deleteNews(Date yesterday) {
        newsMapper.deleteNews(yesterday);
    }

    //查询主页国内新闻左
    @Override
    public List<News> queryIndexOtherNews(int homepageOtherNewsType,int start,int end) {
       return newsMapper.queryIndexOtherNews(homepageOtherNewsType,start,end);
    }

    //查询主页国内新闻,带有图片
    @Override
    public News queryIndexOtherNewsWithImg(int type) {
        return newsMapper.queryIndexOtherNewsWithImg(type);
    }

    //通过类型删除
    @Override
    public void deleteNewsByType(Date twoHourAgo,int type) {
        newsMapper.deleteNewsByType(twoHourAgo,type);
    }

    //通过id删除数据
    @Override
    public void deleteNewsById(int id) {
        newsMapper.deleteNewsById(id);
    }


}
