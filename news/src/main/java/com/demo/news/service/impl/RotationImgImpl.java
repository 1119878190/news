package com.demo.news.service.impl;

import com.demo.news.entity.RotationImg;
import com.demo.news.mapper.RotationImgMapper;
import com.demo.news.service.RotationImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("rotationImg")
public class RotationImgImpl implements RotationImgService {

    @Autowired
    private RotationImgMapper rotationImgMapper;


    //保存轮播图
    @Override
    public void insertRotationImg(RotationImg rotationImg) {
        rotationImgMapper.insertRotationImg(rotationImg);
    }

    //查询数据库中是否有轮播图
    @Override
    public int selectRotationImgCount() {
        return rotationImgMapper.selectRotationImgCount();
    }

    //删除 两小时前的轮播图
    @Override
    public void deleteRotationImg(Date date) {
        rotationImgMapper.deleteRotationImg(date);
    }




    //查找主页轮播图
    @Override
    public List<RotationImg> queryRotationImg(int type,int end) {
        return rotationImgMapper.queryRotationImg(type,end);
    }
}
