package com.demo.news.service;

import com.demo.news.entity.News;
import com.demo.news.entity.RotationImg;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface NewsService {

    //保存新闻
     void insertNews(News news);

     //保存带有图片的新闻
    void insertNewsWithImg(News news);

     //查询热点新闻
    List<News> queryHotNews();

    List<News> queryAllNews();

    //查询数据库是否有数据
    int queryCount(int type);

    //删除过期数据(昨天零点之前的数据)
    void deleteNews(Date yesterday);

    //查询主页国内新闻左
    List<News> queryIndexOtherNews(int type,int start,int end);

    //查询主页国内新闻,带有图片
    News queryIndexOtherNewsWithImg(int type);

    //删除,通过类型
    void deleteNewsByType(Date twoHourAgo,int type);

    //删除,通过id
    void deleteNewsById(int id);
}
