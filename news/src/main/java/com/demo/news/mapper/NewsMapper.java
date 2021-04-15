package com.demo.news.mapper;


import com.demo.news.entity.News;
import com.demo.news.entity.RotationImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface NewsMapper {

    //添加新闻
    int insertNews(News news);

    //查询所有新闻
    List<News> queryAllNews();


    void insertNewsWithImg(News news);

    //查询热点新闻
    List<News> queryHotNews();

    //查询数据库中是否有数据
    int queryCount(int type);

    //删除过期数据(昨天零点之前的数据)
    void deleteNews(Date yesterday);

    //查询主页国内新闻 左
    List<News> queryIndexOtherNews(int type,int start,int end);

    //查询主页国内新闻,带有图片
    News queryIndexOtherNewsWithImg(int type);

    //通过类型删除数据
    void deleteNewsByType(Date twoHourAgo,int type);

    //通过id删除新闻
    void deleteNewsById(int id);
}
