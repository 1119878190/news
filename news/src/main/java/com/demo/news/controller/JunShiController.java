package com.demo.news.controller;

import com.demo.news.entity.News;
import com.demo.news.entity.RotationImg;
import com.demo.news.service.JunShiNewsService;
import com.demo.news.service.RotationImgService;
import com.demo.news.utils.Constant;
import com.demo.news.utils.JunShiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class JunShiController implements Constant, JunShiConstant {

    @Autowired
    private JunShiNewsService junShiNewsService;

    @Autowired
    private RotationImgService rotationImgService;

    @GetMapping("/junShi")
    public String junShiNews(Model model){

        //主页轮播图
        List<RotationImg> homepageRotationImg = rotationImgService.queryRotationImg(HomePage,8);
        model.addAttribute("homepageRotationImg",homepageRotationImg);

        //军事轮播图
        List<RotationImg> junShiRotationImg = rotationImgService.queryRotationImg(JunShiRotationImg,3);
        model.addAttribute("junShiRotationImg",junShiRotationImg);

        //即时新闻
        List<News> jiSHiNews = junShiNewsService.queryNews(JISHINEWS, 8);
        model.addAttribute("jiSHiNews",jiSHiNews);

        //焦点新闻
        List<News> jiaoDianNews = junShiNewsService.queryNews(JIAODIANNEWS, 12);
        model.addAttribute("jiaoDianNews",jiaoDianNews);

        //焦点新闻 图片
        News jiaoDianTopImg = junShiNewsService.queryNewsWithImg(JIAODIANTOPIMG);
        News jiaoDianBottomImg = junShiNewsService.queryNewsWithImg(JIAODIANBOTTOMIMG);
        model.addAttribute("jiaoDianTopImg",jiaoDianTopImg);
        model.addAttribute("jiaoDianBottomImg",jiaoDianBottomImg);

        //军事视频
        List<News> junShiVideo = junShiNewsService.queryNews(JUNSHINVIDEO, 4);
        model.addAttribute("junShiVideo",junShiVideo);

        //中国军情
        List<News> chinaMilitary = junShiNewsService.queryNews(ZHONGGUOJUNQING, 12);
        model.addAttribute("chinaMilitary",chinaMilitary);

        //中国军情 图片
        News chinaMilitaryTopImg = junShiNewsService.queryNewsWithImg(CHINAMILITARYTOPIMG);
        News chinaMilitaryBottomImg = junShiNewsService.queryNewsWithImg(CHINAMILITARYBOTTOM);
        model.addAttribute("chinaMilitaryTopImg",chinaMilitaryTopImg);
        model.addAttribute("chinaMilitaryBottomImg",chinaMilitaryBottomImg);

        //台湾聚焦 图片
        News juJiaoImg = junShiNewsService.queryNewsWithImg(JUJIAOIMG);
        model.addAttribute("juJiaoImg",juJiaoImg);

        //台湾聚焦
        List<News> juJiaoNews = junShiNewsService.queryNews(TAIWANJUJIAO, 7);
        model.addAttribute("juJiaoNews",juJiaoNews);

        //国际军情
        List<News> worldMilitaryNews = junShiNewsService.queryNews(GUOJIJUNQING, 12);
        model.addAttribute("worldMilitaryNews",worldMilitaryNews);

        //国际军情  图片
        News worldMilitaryTopImg = junShiNewsService.queryNewsWithImg(WORLDMILITARYTOPIMG);
        News worldMilitaryBottomImg = junShiNewsService.queryNewsWithImg(WORLDMILITARYBOTTOMIMG);
        model.addAttribute("worldMilitaryTopImg",worldMilitaryTopImg);
        model.addAttribute("worldMilitaryBottomImg",worldMilitaryBottomImg);


        //热点新闻
        List<News> hotNews = junShiNewsService.queryNews(HOTNEWS, 8);
        model.addAttribute("hotNews",hotNews);

        //军事图片
        List<News> junShiPictures = junShiNewsService.queryNews(JUNSHIPICTURES, 12);
        model.addAttribute("junShiPictures",junShiPictures);

        //最新新闻
        List<News> latestNews = junShiNewsService.queryNews(LATESTNEWS, 10);
        model.addAttribute("latestNews",latestNews);

        model.addAttribute("activeUri","junShi");
        return "junShi";

    }
}
