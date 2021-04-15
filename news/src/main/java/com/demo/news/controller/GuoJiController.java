package com.demo.news.controller;

import com.demo.news.entity.News;
import com.demo.news.entity.RotationImg;
import com.demo.news.service.GuoJiNewsService;
import com.demo.news.service.NewsService;
import com.demo.news.service.RotationImgService;
import com.demo.news.utils.Constant;
import com.demo.news.utils.GuoJiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GuoJiController implements GuoJiConstant, Constant {

    @Autowired
    private GuoJiNewsService guoJiNewsService;

    @Autowired
    private RotationImgService rotationImgService;

    @GetMapping("/guoJi")
    public String guoJiNews(Model model){

        //主页轮播图
        List<RotationImg> homepageRotationImg = rotationImgService.queryRotationImg(HomePage,8);
        model.addAttribute("homepageRotationImg",homepageRotationImg);
        //国内轮播图
        List<RotationImg> guoJiRotationImg = rotationImgService.queryRotationImg(GuoJiRotationImg,3);
        model.addAttribute("guoJiRotationImg",guoJiRotationImg);

        //即时新闻
        List<News> jiShiNews = guoJiNewsService.queryNews(JISHINEWS, 8);
        model.addAttribute("jiShiNews",jiShiNews);

        //焦点新闻
        List<News> jiaoDianNews = guoJiNewsService.queryNews(JIAODIANNEWS, 12);
        model.addAttribute("jiaoDianNews",jiaoDianNews);

        //焦点新闻 图片
        News jiaoDianTopImg = guoJiNewsService.queryNewsWithImg(JIAODIANNEWSTOPIMG);
        News jiaoDianBottomImg = guoJiNewsService.queryNewsWithImg(JIAODIANNEWSBOTTOMIMG);
        model.addAttribute("jiaoDianTopImg",jiaoDianTopImg);
        model.addAttribute("jiaoDianBottomImg",jiaoDianBottomImg);

        //图片新闻
        List<News> pictureImgs = guoJiNewsService.queryNews(PICTURESNEWS,6);
        model.addAttribute("pictureImgs",pictureImgs);

        //军事新闻
        List<News> junSHiNews = guoJiNewsService.queryNews(JUNSHINEWS,12);
        model.addAttribute("junSHiNews",junSHiNews);

        //军事图片
        News junShiTopImg = guoJiNewsService.queryNewsWithImg(JUNSHINEWSTOPIMG);
        News junShiBottomImg = guoJiNewsService.queryNewsWithImg(JUNSHINEWSBOTTOMIMG);
        model.addAttribute("junShiTopImg",junShiTopImg);
        model.addAttribute("junShiBottomImg",junShiBottomImg);

        //热门新闻
        List<News> hotNews = guoJiNewsService.queryNews(HOTSNEWS,8);
        model.addAttribute("hotNews",hotNews);

        //最新新闻
        List<News> latestNews = guoJiNewsService.queryNews(LATESTNEWS,10);
        model.addAttribute("latestNews",latestNews);

        model.addAttribute("activeUri","guoJi");


        return "guoJi";
    }
}
