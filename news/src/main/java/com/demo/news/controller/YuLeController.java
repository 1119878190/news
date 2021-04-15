package com.demo.news.controller;

import com.demo.news.entity.News;
import com.demo.news.entity.RotationImg;
import com.demo.news.entity.Songs;
import com.demo.news.service.RotationImgService;
import com.demo.news.service.YuLeNewsService;
import com.demo.news.utils.Constant;
import com.demo.news.utils.YuLeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class YuLeController implements Constant, YuLeConstant {


    @Autowired
    private YuLeNewsService yuLeNewsService;

    @Autowired
    private RotationImgService rotationImgService;

    @GetMapping("/yuLe")
    public String yuLeNews(Model model){

        //主页轮播图
        List<RotationImg> homepageRotationImg = rotationImgService.queryRotationImg(HomePage,8);
        model.addAttribute("homepageRotationImg",homepageRotationImg);
        //军事轮播图
        List<RotationImg> yuLeRotationImg = rotationImgService.queryRotationImg(YuLeRotationImg,3);
        model.addAttribute("yuLeRotationImg",yuLeRotationImg);

        //即时新闻
        List<News> jiSHiNews = yuLeNewsService.queryNews(JISHINEWS, 8);
        model.addAttribute("jiSHiNews",jiSHiNews);

        //焦点新闻
        List<News> focalNews = yuLeNewsService.queryNews(FOCALNEWS, 12);
        model.addAttribute("jiaoDianNews",focalNews);

        //百家专栏
        List<News> baiJia = yuLeNewsService.queryNews(BAJIAIMG, 2);
        model.addAttribute("baiJiaNews",baiJia);

        //明星
        List<News> startsNews = yuLeNewsService.queryNews(STARTSNEWS, 18);
        model.addAttribute("startsNews",startsNews);
        //明星图片
        List<News> startsNewsImg = yuLeNewsService.queryNews(STARTSNEWSIMG, 3);
        model.addAttribute("startsNewsImg",startsNewsImg);

        //传文爆料
        List<News> rumoursNews = yuLeNewsService.queryNews(RUMOURSNEWS, 10);
        model.addAttribute("rumoursNews",rumoursNews);

        //综艺视频
        List<News> varietyVideos = yuLeNewsService.queryNews(VARIETYVIDEO, 2);
        model.addAttribute("varietyVideos",varietyVideos);

        //电影
        List<News> moviesNews = yuLeNewsService.queryNews(MOVIES, 12);
        model.addAttribute("moviesNews",moviesNews);
        //电影图片
        List<News> moviesImgs = yuLeNewsService.queryNews(MOVIESIMG, 2);
        model.addAttribute("moviesImgs",moviesImgs);

        //电影花絮
        List<News> moviesFeatures = yuLeNewsService.queryNews(MOVIESFEATURES, 10);
        model.addAttribute("moviesFeatures",moviesFeatures);

        //电视
        List<News> tvs = yuLeNewsService.queryNews(TVS, 12);
        model.addAttribute("tvs",tvs);
        //电视图片
        List<News> tvsImgs = yuLeNewsService.queryNews(TVSIMG, 2);
        model.addAttribute("tvsImgs",tvsImgs);

        //热门剧集
        List<News> hotDramaseries = yuLeNewsService.queryNews(HOTDRAMASERIES, 4);
        model.addAttribute("hotDramaseries",hotDramaseries);

        //热门剧评
        List<News> hotDramaComments = yuLeNewsService.queryNews(HOTDRAMACOMMENTS, 4);
        model.addAttribute("hotDramaComments",hotDramaComments);

        //音乐
        List<News> musicNews = yuLeNewsService.queryNews(MUSIC, 12);
        model.addAttribute("musicNews",musicNews);
        //音乐图片
        List<News> musicImgs = yuLeNewsService.queryNews(MUSICIMG, 2);
        model.addAttribute("musicImgs",musicImgs);

        //新歌
        List<Songs> newSongs = yuLeNewsService.querySongs(NEWSONGS, 4);
        model.addAttribute("newsSongs",newSongs);

        //金曲榜
        List<Songs> rakingSongs = yuLeNewsService.querySongs(RAKING, 4);
        model.addAttribute("rakingSongs",rakingSongs);

        //综艺
        List<News> varieties = yuLeNewsService.queryNews(VARIETIES, 12);
        model.addAttribute("varieties",varieties);
        //综艺图片
        List<News> varietiesImg = yuLeNewsService.queryNews(VARIETIESIMG, 2);
        model.addAttribute("varietiesImgs",varietiesImg);

        //热门点击
        List<News> hotClicks = yuLeNewsService.queryNews(HOTCLICKS, 10);
        model.addAttribute("hotClicks",hotClicks);

        //图片新闻
        List<News> pictureNews = yuLeNewsService.queryNews(PICTURENEWS, 10);
        model.addAttribute("pictureNews",pictureNews);

        //最新新闻
        List<News> latestNews = yuLeNewsService.queryNews(LATESTNEWS, 20);
        model.addAttribute("latestNews",latestNews);

        model.addAttribute("activeUri","yuLe");

        return "yuLe";
    }
}
