package com.demo.news.service;

import com.demo.news.entity.News;

import java.util.List;

public interface GuoJiNewsService {


    void insertNews(News news);

    void insertNewsWithImg(News news);

    void deleteNews();

    List<News> queryNews(int type,int end);

    List<News> queryAllNews();

    News queryNewsWithImg(int type);
}
