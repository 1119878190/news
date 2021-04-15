package com.demo.news.service;


import com.demo.news.entity.News;

import java.util.List;


public interface GuoNeiNewsService {


    void insertNews(News news);

    void deleteNews();

    List<News> queryAllNews();

    List<News> queryNews(int type, int end);

    News queryNewsWithImg(int type);
}
