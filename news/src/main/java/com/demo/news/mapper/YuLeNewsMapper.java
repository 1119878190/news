package com.demo.news.mapper;

import com.demo.news.entity.News;
import com.demo.news.entity.Songs;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface YuLeNewsMapper {


    @Insert(" insert into yuLeNews(title,href,saveTime,type,src) values(#{title},#{href},#{saveTime},#{type},#{src})")
    void insertNews(News news);

    @Delete("delete from yuLeNews where saveTime <= DATE_ADD(now(), Interval -2 hour)")
    void deleteNews();

    @Select("select * from yuLeNews where id in(select t.id from (select * from yuLeNews where type = #{type}  order by id desc limit 0,#{end})as t) ")
    List<News> queryNews(int type, int end);

    @Select("select * from yuLeNews where id in(select t.id from (select * from yuLeNews where type = #{type}  order by id desc limit 0,1)as t) ")
    News queryWithImg(int type);

    @Select("select * from songs where id in(select t.id from (select * from songs where type = #{type}  order by id desc limit 0,#{end })as t) ")
    List<Songs> querySongs(int type,int end);

    @Insert("insert into songs(name,nameHref,singer,singerHref,saveTime,type) values(#{name},#{nameHref},#{singer},#{singerHref},#{saveTime},#{type})")
    void insertSongs(Songs songs);
}
