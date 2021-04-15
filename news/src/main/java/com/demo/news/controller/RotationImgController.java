package com.demo.news.controller;

//轮播图

import com.demo.news.entity.RotationImg;
import com.demo.news.service.RotationImgService;
import com.demo.news.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rotationImg")
public class RotationImgController implements Constant {


    @Autowired
    private RotationImgService rotationImgService;

    //查询主页轮播图
    @GetMapping("/queryHomePage")
    public void queryHomePage(Model model){

        List<RotationImg> rotationImgs = rotationImgService.queryRotationImg(IndexRotationImg,8);//主页轮播图
        int count = rotationImgService.selectRotationImgCount();
        model.addAttribute("rotationImgs",rotationImgs);
        model.addAttribute(count);

    }

}
