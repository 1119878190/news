package com.demo.news.spider;
/**
 * 娱乐新闻
 */

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.YuLeNewsPipeline;
import com.demo.news.pipelline.YuLeWithImgPicPipeline;
import com.demo.news.pipelline.YuLeWithImgPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
@Component
public class YuLeNewsProcessor implements PageProcessor {




    Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36")
            .setTimeOut(8000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Autowired
    private YuLeWithImgProcessor yuLeWithImgProcessor;

    @Autowired
    private YuLeWithImgPipeline yuLeWithImgPipeline;

    @Autowired
    private YuLeWithImgPicProcessor yuLeWithImgPicProcessor;

    @Autowired
    private YuLeWithImgPicPipeline yuLeWithImgPicPipeline;


    @Override
    public Site getSite() {
        return site;
    }


    @Override
    public void process(Page page) {
        //即时新闻
        List<String>  jiShiNewsTitles = page.getHtml().css("#instant-news > ul > li > a", "text").all();
        List<String> jiSHiNewsHrefs = page.getHtml().css("#instant-news > ul > li > a", "href").all();
        page.putField("jiShiNewsTitles",jiShiNewsTitles);
        page.putField("jiSHiNewsHrefs",jiSHiNewsHrefs);

        //焦点新闻
        List<String> focalNewsTitles = page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "text").all();
        List<String> focalNewsHrefs = page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "href").all();
        page.putField("focalNewsTitles",focalNewsTitles);
        page.putField("focalNewsHrefs",focalNewsHrefs);

        //百家专栏  图片
        List<String> baiJiaImg = page.getHtml().css("#col_focus > div.l-left-col > div.ilist > ul > li > a", "href").all();
        for (String href : baiJiaImg) {
            spiderImgByHref(href);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //明星
        List<String> startsNewsTitles = page.getHtml().css("#col_star > div.l-left-col > div.b-left > ul > li > a", "text").all();
        List<String> startsNewsHrefs = page.getHtml().css("#col_star > div.l-left-col > div.b-left > ul > li > a", "href").all();
        page.putField("startsNewsTitles",startsNewsTitles);
        page.putField("startsNewsHrefs",startsNewsHrefs);

        //明星  图片
        List<String> startsNewsImg = page.getHtml().css("#col_star > div.l-left-col > div.ilist > ul > li > a", "href").all();
        for (String href : startsNewsImg) {
            spiderImgByHref(href);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //传文爆料
        List<String> rumoursTitles = page.getHtml().css("#zongyi_cwbl > ul > li > a", "text").all();
        List<String> rumoursHrefs = page.getHtml().css("#zongyi_cwbl > ul > li > a", "href").all();
        page.putField("rumoursTitles",rumoursTitles);
        page.putField("rumoursHrefs",rumoursHrefs);


        //综艺视频
        List<String> varietyVideoHrefs = page.getHtml().css("#zongyi_video > div.bd > div > ul > li > a", "href").all();
        List<String> varietyVideoSrcs = page.getHtml().css("#zongyi_video > div.bd > div > ul > li > a > img", "src").all();
        List<String> varietyVideoTitles = page.getHtml().css("#zongyi_video > div.bd > div > ul > li > a > span", "text").all();
        page.putField("varietyVideoHrefs",varietyVideoHrefs);
        page.putField("varietyVideoSrcs",varietyVideoSrcs);
        page.putField("varietyVideoTitles",varietyVideoTitles);


        //电影
        List<String> moviesTitles = page.getHtml().css("#col_film > div.l-left-col > div.b-left > ul > li > a", "text").all();
        List<String> moviesHrefs = page.getHtml().css("#col_film > div.l-left-col > div.b-left > ul > li > a", "href").all();
        page.putField("moviesTitles",moviesTitles);
        page.putField("moviesHrefs",moviesHrefs);

        //电影 图片
        List<String> moviesImg = page.getHtml().css("#col_film > div.l-left-col > div.ilist > ul > li > a", "href").all();
        for (String href : moviesImg) {
            spiderImgByHref(href);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //电影花絮
        List<String> moviesFeaturesTitles = page.getHtml().css("#film_huaxu > ul > li> a", "text").all();
        List<String> moviesFeaturesHrefs = page.getHtml().css("#film_huaxu > ul > li > a", "href").all();
        page.putField("moviesFeaturesTitles",moviesFeaturesTitles);
        page.putField("moviesFeaturesHrefs",moviesFeaturesHrefs);

        //电视
        List<String> tvsTitles = page.getHtml().css("#col_tv > div.l-left-col > div.b-left > ul > li > a", "text").all();
        List<String> tvsHrefs = page.getHtml().css("#col_tv > div.l-left-col > div.b-left > ul > li > a", "href").all();
        page.putField("tvsTitles",tvsTitles);
        page.putField("tvsHrefs",tvsHrefs);

        //电视  图片
        List<String> tvsImg = page.getHtml().css("#col_tv > div.l-left-col > div.ilist > ul > li > a", "href").all();
        for (String href : tvsImg) {
            spiderImgByHref(href);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //热门剧集
        List<String> hotDramaSeriesTitles = page.getHtml().css("#hotdramas > table > tbody > tr > td.n > a", "text").all();
        List<String> hotDramaSeriesHrefs = page.getHtml().css("#hotdramas > table > tbody > tr > td.n > a", "href").all();
        page.putField("hotDramaSeriesTitles",hotDramaSeriesTitles);
        page.putField("hotDramaSeriesHrefs",hotDramaSeriesHrefs);

        //热门剧评
        List<String> hotDramaCommentsTitles = page.getHtml().css("#hot_recommend > div.bd > ul > li > a", "text").all();
        List<String> hotDramaCommentsHrefs = page.getHtml().css("#hot_recommend > div.bd > ul > li > a", "href").all();
        page.putField("hotDramaCommentsTitles",hotDramaCommentsTitles);
        page.putField("hotDramaCommentsHrefs",hotDramaCommentsHrefs);

        //音乐
        List<String> musicsTitles = page.getHtml().css("#col_music > div.l-left-col > div.b-left > ul > li> a", "text").all();
        List<String> musicsHrefs = page.getHtml().css("#col_music > div.l-left-col > div.b-left > ul > li > a", "href").all();
        page.putField("musicsTitles",musicsTitles);
        page.putField("musicsHrefs",musicsHrefs);

        //音乐  图片
        List<String> musicsImg = page.getHtml().css("#col_music > div.l-left-col > div.ilist > ul > li > a", "href").all();
        for (String href : musicsImg) {
            spiderImgByHref(href);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //新歌
        List<String> newSongsName = page.getHtml().css("#music_new_song > div.bd > div > table > tbody > tr > td.n > a", "text").all();
        List<String> newSongsNameHref = page.getHtml().css("#music_new_song > div.bd > div > table > tbody > tr > td.n > a", "href").all();
        List<String> newSongsSinger = page.getHtml().css("#music_new_song > div.bd > div > table > tbody > tr > td.s > a", "text").all();
        List<String> newsSongsSingerHref = page.getHtml().css("#music_new_song > div.bd > div > table > tbody > tr > td.s > a", "href").all();
        page.putField("newSongsName",newSongsName);
        page.putField("newSongsNameHref",newSongsNameHref);
        page.putField("newSongsSinger",newSongsSinger);
        page.putField("newsSongsSingerHref",newsSongsSingerHref);

        //中文金曲榜单
        List<String> rankingSongsName = page.getHtml().css("#music_gold_song > div.bd > div > table > tbody > tr > td.n > a", "text").all();
        List<String> rankingSongsNameHref = page.getHtml().css("#music_gold_song > div.bd > div > table > tbody > tr > td.n > a", "href").all();
        List<String> rankingSinger = page.getHtml().css("#music_gold_song > div.bd > div > table > tbody > tr > td.s > a", "text").all();
        List<String> rankingSingerHref = page.getHtml().css("#music_gold_song > div.bd > div > table > tbody > tr > td.s > a", "href").all();
        page.putField("rankingSongsName",rankingSongsName);
        page.putField("rankingSongsNameHref",rankingSongsNameHref);
        page.putField("rankingSinger",rankingSinger);
        page.putField("rankingSingerHref",rankingSingerHref);

        //综艺
        List<String> varietiesTitles = page.getHtml().css("#col_zongyi > div.l-left-col > div.b-left > ul > li > a", "text").all();
        List<String> varietiesHrefs = page.getHtml().css("#col_zongyi > div.l-left-col > div.b-left > ul > li> a", "href").all();
        page.putField("varietiesTitles",varietiesTitles);
        page.putField("varietiesHrefs",varietiesHrefs);

        //综艺  图片
        List<String> varietiesImg = page.getHtml().css("#col_zongyi > div.l-left-col > div.ilist > ul > li > a", "href").all();
        for (String href : varietiesImg) {
            spiderImgByHref(href);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //热门点击
        List<String> hotClickTitles = page.getHtml().css("#hot-clicks > ol > li > a", "text").all();
        List<String> hotClickHrefs = page.getHtml().css("#hot-clicks > ol > li > a", "href").all();
        page.putField("hotClickTitles",hotClickTitles);
        page.putField("hotClickHrefs",hotClickHrefs);

        //图片新闻  图片
        List<String> pictureNews = page.getHtml().css("#mil-picnews > div > ul > li > a", "href").all();
        for (String pictureNew : pictureNews) {
            spiderImgByHrefPic(pictureNew);
        }

        //最新新闻
        List<String> latestNewsTitles = page.getHtml().css("#latest-news > ul > li > a", "text").all();
        List<String> latestNewsHrefs = page.getHtml().css("#latest-news > ul > li > a", "href").all();
        List<String> latestNewsTimes = page.getHtml().css("#latest-news > ul > li > span", "text").all();
        page.putField("latestNewsTitles",latestNewsTitles);
        page.putField("latestNewsHrefs",latestNewsHrefs);
        page.putField("latestNewsTimes",latestNewsTimes);





    }

    public synchronized void spiderImgByHref(String href){
        if (!href.equals("") && href.length()>0){
            Spider.create(yuLeWithImgProcessor)
                    .addUrl(href)
                    .addPipeline(yuLeWithImgPipeline)
                    .runAsync();
        }


    }

    public void spiderImgByHrefPic(String href){
        if (!href.equals("") && href.length()>0){
            Spider.create(yuLeWithImgPicProcessor)
                    .addUrl(href)
                    .addPipeline(yuLeWithImgPicPipeline)
                    .thread(5)
                    .runAsync();
        }

    }


}
