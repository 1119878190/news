package com.demo.news.controller;

import com.demo.news.entity.News;
import com.demo.news.entity.RotationImg;
import com.demo.news.service.NewsService;
import com.demo.news.service.RotationImgService;
import com.demo.news.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController implements Constant {

    @Autowired
    private RotationImgService rotationImgService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //主页
    @GetMapping("/")
    public String queryHomePage(Model model){

        //查询热点新闻
        List<News> newsList = newsService.queryHotNews();

        //主页轮播图
        List<RotationImg> homepageRotationImg = rotationImgService.queryRotationImg(HomePage,8);
        int count = rotationImgService.selectRotationImgCount();//轮播图数量
        model.addAttribute("homepageRotationImg",homepageRotationImg);
        model.addAttribute("count",count);


        //微博热搜榜
        List<News> weiBoHotWords = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = (News) redisTemplate.opsForValue().get(redisKey+ split+i);
            weiBoHotWords.add(news);

        }
        model.addAttribute("weiBoHotWords",weiBoHotWords);

        //百度热搜排行榜
        List<News> baiDuHotWords = new ArrayList<>();
        for (int i = 0; i < 29; i++) {
            News news = (News) redisTemplate.opsForValue().get(baiDuRedisKey+split+i);
            baiDuHotWords.add(news);
        }
        model.addAttribute("baiDuHotWords",baiDuHotWords);

        //知乎热搜
        List<News> zhiHuHotWords = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = (News) redisTemplate.opsForValue().get(zhiHuRedisKey+split+i);
            zhiHuHotWords.add(news);
        }
        model.addAttribute("zhiHuHotWords",zhiHuHotWords);


        //主页国内新闻
        List<News> homePageGuoNei = newsService.queryIndexOtherNews(HomePageGuoNeiNews,0,12);//左
        News homeGuoNeiImgTop = newsService.queryIndexOtherNewsWithImg(GuoNeiImgTop);//中 上图片
        News homeGuoNeiImgBottomRight = newsService.queryIndexOtherNewsWithImg(GuoNeiImgBottomRight);//中下右图片
        News homeGuoNeiImgBottomLeft = newsService.queryIndexOtherNewsWithImg(GuoNeiImgBottomLeft);//中 下 左 图片
        News homeGuoNeiImgListLeft = newsService.queryIndexOtherNewsWithImg(GuoNeiImgListLeft);//右 左
        News homeGuoNeiIMgListRight = newsService.queryIndexOtherNewsWithImg(GuoNeiIMgListRight);//右 右
        List<News> homeGuoNeiHotCheck = newsService.queryIndexOtherNews(GuoNeiHotCheck,0,5);//国内热门点击

        model.addAttribute("newsList",newsList);



        model.addAttribute("homePageGuoNei",homePageGuoNei);
        model.addAttribute("homeGuoNeiImgTop",homeGuoNeiImgTop);
        model.addAttribute("homeGuoNeiImgBottomRight",homeGuoNeiImgBottomRight);
        model.addAttribute("homeGuoNeiImgBottomLeft",homeGuoNeiImgBottomLeft);
        model.addAttribute("homeGuoNeiImgListLeft",homeGuoNeiImgListLeft);
        model.addAttribute("homeGuoNeiIMgListRight",homeGuoNeiIMgListRight);
        model.addAttribute("homeGuoNeiHotCheck",homeGuoNeiHotCheck);
        
        
        //主页国际新闻
        List<News> homePageGuoJi = newsService.queryIndexOtherNews(HomePageGuoJiNews,0,12);//左
        News homeGuoJiImgTop = newsService.queryIndexOtherNewsWithImg(GuoJiImgTop);//中 上图片
        News homeGuoJiImgBottomRight = newsService.queryIndexOtherNewsWithImg(GuoJiImgBottomRight);//中下右图片
        News homeGuoJiImgBottomLeft = newsService.queryIndexOtherNewsWithImg(GuoJiImgBottomLeft);//中 下 左 图片
        News homeGuoJiImgListLeft = newsService.queryIndexOtherNewsWithImg(GuoJiImgListLeft);//右 左
        News homeGuoJiIMgListRight = newsService.queryIndexOtherNewsWithImg(GuoJiIMgListRight);//右 右
        List<News> homeGuoJiHotCheck = newsService.queryIndexOtherNews(GuoJiHotCheck,0,10);

        model.addAttribute("homePageGuoJi",homePageGuoJi);
        model.addAttribute("homeGuoJiImgTop",homeGuoJiImgTop);
        model.addAttribute("homeGuoJiImgBottomRight",homeGuoJiImgBottomRight);
        model.addAttribute("homeGuoJiImgBottomLeft",homeGuoJiImgBottomLeft);
        model.addAttribute("homeGuoJiImgListLeft",homeGuoJiImgListLeft);
        model.addAttribute("homeGuoJiIMgListRight",homeGuoJiIMgListRight);
        model.addAttribute("homeGuoJiHotCheck",homeGuoJiHotCheck);
        
        
        //主页军事新闻
        List<News> homePageJunShi = newsService.queryIndexOtherNews(HomePageJunShiNews,0,12);//左
        News homeJunShiImgTop = newsService.queryIndexOtherNewsWithImg(JunShiImgTop);//中 上图片
        News homeJunShiImgBottomRight = newsService.queryIndexOtherNewsWithImg(JunShiImgBottomRight);//中下右图片
        News homeJunShiImgBottomLeft = newsService.queryIndexOtherNewsWithImg(JunShiImgBottomLeft);//中 下 左 图片
      
        List<News> homeJunShiHotCheck = newsService.queryIndexOtherNews(JunShiHotCheck,0,5);//军事热门点击
        List<News> homeJunShiChinaWar = newsService.queryIndexOtherNews(JunShiChinaWar,0,5);//军事国内军情


        model.addAttribute("homePageJunShi",homePageJunShi);
        model.addAttribute("homeJunShiImgTop",homeJunShiImgTop);
        model.addAttribute("homeJunShiImgBottomRight",homeJunShiImgBottomRight);
        model.addAttribute("homeJunShiImgBottomLeft",homeJunShiImgBottomLeft);
        model.addAttribute("homeJunShiHotCheck",homeJunShiHotCheck);
        model.addAttribute("homeJunShiChinaWar",homeJunShiChinaWar);
        
        //主页娱乐
        List<News> homePageYuLe = newsService.queryIndexOtherNews(HomePageYuLeNews,0,12);//左
        News homeYuLeImgTop = newsService.queryIndexOtherNewsWithImg(YuLeImgTop);//中 上图片
        News homeYuLeImgBottomLeft = newsService.queryIndexOtherNewsWithImg(YuLeImgBottomLeft);//中 下 左 图片
        News homeYuLeImgBottomRight = newsService.queryIndexOtherNewsWithImg(YuLeImgBottomRight);//中下右图片
        News homeYuLeImgListLeft = newsService.queryIndexOtherNewsWithImg(YuLeImgListLeft);//右 左
        News homeYuLeIMgListRight = newsService.queryIndexOtherNewsWithImg(YuLeIMgListRight);//右 右


        List<News> homeYuLeHotWords = newsService.queryIndexOtherNews(YuLeHotWords,0,7);//热门点击
        List<News> homeYuLeBottomImgs = newsService.queryIndexOtherNews(YuLeBottomImgs,0,5);//最底部图片

        model.addAttribute("homePageYuLe",homePageYuLe);
        model.addAttribute("homeYuLeImgTop",homeYuLeImgTop);
        model.addAttribute("homeYuLeImgBottomRight",homeYuLeImgBottomRight);
        model.addAttribute("homeYuLeImgBottomLeft",homeYuLeImgBottomLeft);
        model.addAttribute("homeYuLeImgListLeft",homeYuLeImgListLeft);
        model.addAttribute("homeYuLeIMgListRight",homeYuLeIMgListRight);
        model.addAttribute("homeYuLeHotWords",homeYuLeHotWords);
        model.addAttribute("homeYuLeBottomImgs",homeYuLeBottomImgs);


        model.addAttribute("activeUri","index");



        return "index";

    }
}
