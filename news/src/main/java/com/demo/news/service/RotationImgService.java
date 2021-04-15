package com.demo.news.service;

import com.demo.news.entity.RotationImg;

import java.util.Date;
import java.util.List;

//轮播图
public interface RotationImgService {


    //保存轮播图信息
    void insertRotationImg(RotationImg rotationImg);

    //查询数据库中是否有轮播图
    int selectRotationImgCount();

    //删除两小时前所有轮播图
    void deleteRotationImg(Date date);


    //查找轮播图
    List<RotationImg> queryRotationImg(int type,int end);


}
