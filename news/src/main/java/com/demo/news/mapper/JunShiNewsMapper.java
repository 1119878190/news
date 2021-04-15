package com.demo.news.mapper;


import com.demo.news.entity.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JunShiNewsMapper {

    @Insert(" insert into junShiNews(title,href,saveTime,type,src) values(#{title},#{href},#{saveTime},#{type},#{src})")
    void insertNews(News news);

    @Delete("delete from junShiNews where saveTime <= DATE_ADD(now(), Interval -2 hour)")
    void deleteNews();

    @Select("select * from junShiNews where id in(select t.id from (select * from junShiNews where type = #{type}  order by id desc limit 0,#{end})as t) ")
    List<News> queryNews(int type, int end);

    @Select("select * from junShiNews where id in(select t.id from (select * from junShiNews where type = #{type}  order by id desc limit 0,1)as t) ")
    News queryWithImg(int type);

}
