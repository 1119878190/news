package com.demo.news.controller;


import com.demo.news.entity.News;
import com.demo.news.entity.RotationImg;
import com.demo.news.service.GuoNeiNewsService;
import com.demo.news.service.RotationImgService;
import com.demo.news.utils.Constant;
import com.demo.news.utils.GuoNeiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class GuoNeiController implements Constant, GuoNeiConstant {


    @Autowired
    private RotationImgService rotationImgService;

    @Autowired
    private GuoNeiNewsService guoNeiNewsService;

    @RequestMapping("/guoNei")
    public String guoNei(Model model){


        //主页轮播图
        List<RotationImg> homepageRotationImg = rotationImgService.queryRotationImg(HomePage,8);
        model.addAttribute("homepageRotationImg",homepageRotationImg);
        //国内轮播图
        List<RotationImg> guoNeiRotationImg = rotationImgService.queryRotationImg(GuoNeiRotationImg,3);
        model.addAttribute("guoNeiRotationImg",guoNeiRotationImg);

        //即时新闻
        List<News> jiShiNewsGuoNei = guoNeiNewsService.queryNews(JISHINEWS,8);
        model.addAttribute("jiShiNewsGuoNei",jiShiNewsGuoNei);

        //焦点新闻
        List<News> jiaoDianNewsGuoNei = guoNeiNewsService.queryNews(JIAODIANNEWS,12);
        model.addAttribute("jiaoDianNewsGuoNei",jiaoDianNewsGuoNei);

        //视频新闻
        List<News> shiPingNewsGuoNei = guoNeiNewsService.queryNews(SHIPINGNEWS,4);
        model.addAttribute("shiPingNewsGuoNei",shiPingNewsGuoNei);

        //港澳台新闻
        List<News> taiWanNews = guoNeiNewsService.queryNews(TAIWANNEWS,5);
        List<News> gangAoNews = guoNeiNewsService.queryNews(GANGAONEWS,5);
        model.addAttribute("taiWanNews",taiWanNews);
        model.addAttribute("gangAoNews",gangAoNews);

        //港澳台中图片
        News gangAoTopImg = guoNeiNewsService.queryNewsWithImg(GANGAOTAITOPIMG);
        News gangAoBottomImg = guoNeiNewsService.queryNewsWithImg(GANGAOTAIBOTTOMIMG);
        model.addAttribute("gangAoTopImg",gangAoTopImg);
        model.addAttribute("gangAoBottomImg",gangAoBottomImg);

        //名声图片
        News mingshengImg = guoNeiNewsService.queryNewsWithImg(MINGSHENGIMG);
        model.addAttribute("mingshengImg",mingshengImg);

        //名声新闻
        List<News> mingshengNews = guoNeiNewsService.queryNews(MINGSHENGNEWS,7);
        model.addAttribute("mingshengNews",mingshengNews);

        //时政要闻
        List<News> shizhengNews = guoNeiNewsService.queryNews(SHIZHENGNEWS,12);
        model.addAttribute("shizhengNews",shizhengNews);

        //时政要闻图片
        News shizhengImgTop = guoNeiNewsService.queryNewsWithImg(SHIZHENGTOPIMG);
        News shizhenegImgBottom = guoNeiNewsService.queryNewsWithImg(SHIZHENGBOTTOMIMG);
        model.addAttribute("shizhengImgTop",shizhengImgTop);
        model.addAttribute("shizhenegImgBottom",shizhenegImgBottom);

        //历史档案
        List<News> historyNews = guoNeiNewsService.queryNews(HISTORYNEWS,10);
        model.addAttribute("historyNews",historyNews);

        //最新新闻
        //左
        List<News> latestNewsLeft = guoNeiNewsService.queryNews(LATESTNEWSLEFT,10);
        List<News> latestNewsRight = guoNeiNewsService.queryNews(LATESTNEWSRIGHT,10);
        model.addAttribute("latestNewsLeft",latestNewsLeft);
        model.addAttribute("latestNewsRight",latestNewsRight);

        model.addAttribute("activeUri","guoNei");
        return "guoNei";


    }

}
