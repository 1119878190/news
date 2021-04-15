package com.demo.news.mapper;


import com.demo.news.entity.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface GuoNeiNewsMapper {

    @Insert(" insert into guoNeiNews(title,href,saveTime,type,src) values(#{title},#{href},#{saveTime},#{type},#{src})")
    void insertNews(News news);

    //删除两小时前的数据
    @Delete("delete from guoNeiNews where saveTime <= DATE_ADD(now(), Interval -2 hour)")
    void deleteNews();


    @Select(" select * from guoNeiNews where id in(select t.id from (select * from guoNeiNews where type = #{type}  order by id desc limit 0,#{end})as t) ")
    List<News> queryNews(int type, int end);

    @Select(" select * from guoNeiNews")
    List<News> queryAllNews();

    @Select("select * from guoNeiNews where id in(select t.id from (select * from guoNeiNews where type = #{type}  order by id desc limit 0,1)as t) ")
    News queryNewsWithImg(int type);
}
