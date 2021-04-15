package com.demo.news.service;


import com.demo.news.entity.News;
import com.demo.news.entity.Songs;

import java.util.List;

public interface YuLeNewsService {

    void insertNews(News news);

    void deleteNews();

    List<News> queryNews(int type, int end);

    News queryNewsWithImg(int type);

    void insertSongs(Songs songs);

    List<Songs> querySongs(int type,int end);
}
