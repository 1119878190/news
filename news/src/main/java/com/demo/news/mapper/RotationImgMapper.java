package com.demo.news.mapper;


import com.demo.news.entity.RotationImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface RotationImgMapper {


    //查询数据库中是否有轮播图
    @Select(" select count(*) from rotationImg")
    int selectRotationImgCount();


    //添加轮播图
    @Insert("insert into rotationImg(href,src,type,showInfo,saveTime) values(#{href},#{src},#{type},#{showInfo},#{saveTime})")
    void insertRotationImg(RotationImg rotationImg);

    //查询轮播图
    @Select(" select * from rotationImg where id in(select t.id from (select * from rotationImg where type = #{type}  order by id desc limit 0,#{end})as t)")
    List<RotationImg> queryRotationImg(int type,int end);


    //删除2小时之前的轮播图数据
    @Delete("delete from rotationImg where saveTime <= #{date}")
    void deleteRotationImg(Date date);

}
