package com.demo.news.spider;

import com.demo.news.entity.News;
import com.demo.news.pipelline.GuoNeiWithImgPipeline;
import com.demo.news.service.impl.ElasticSearchService;
import com.demo.news.utils.GuoNeiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GuoNeiNewsProcessor implements PageProcessor, GuoNeiConstant {


    @Autowired
    private GuoNeiWithImgProcessor guoNeiWithImgProcessor;

    @Autowired
    private GuoNeiWithImgPipeline guoNeiWithImgPipeline;



    Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36")
            .setTimeOut(8000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {

        List<String> readySpider = new ArrayList<>();
        System.out.println("----开始抓取国内新闻------");

        //即时新闻
        List<String> jiShiNewsHrefs = page.getHtml().css("#instant-news > ul > li > a", "href").all();
        List<String> jiShiNewsTitles = page.getHtml().css("#instant-news > ul > li > a","text").all();
        page.putField("jiShiNewsHrefs",jiShiNewsHrefs);
        page.putField("jiShiNewsTitles",jiShiNewsTitles);

        //焦点新闻
        List<String> jiaoDianNewsHrefs = page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "href").all();
        List<String> jiaoDianNewsTitles = page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "text").all();
        page.putField("jiaoDianNewsHrefs",jiaoDianNewsHrefs);
        page.putField("jiaoDianNewsTitles",jiaoDianNewsTitles);

        //视频新闻
        List<String> shiPingNewsHrefs = page.getHtml().css("#focus-videos > div > p.title > a", "href").all();
        List<String> shiPingNewsTitles = page.getHtml().css("#focus-videos > div > p.title > a","text").all();
        List<String> shiPingNewsSrcs = page.getHtml().css("#focus-videos > div > p.picture > a > img", "src").all();
        page.putField("shiPingNewsHrefs",shiPingNewsHrefs);
        page.putField("shiPingNewsTitles",shiPingNewsTitles);
        page.putField("shiPingNewsSrcs",shiPingNewsSrcs);


    //港澳台
        //台湾
        List<String> taiWanHrefs = page.getHtml().css("#col_gangaotai > div.l-left-col > div.b-left > ul:nth-child(2) > li > a", "href").all();
        List<String> taiWanTitles = page.getHtml().css("#col_gangaotai > div.l-left-col > div.b-left > ul:nth-child(2) > li > a", "text").all();
        page.putField("taiWanHrefs",taiWanHrefs);
        page.putField("taiWanTitles",taiWanTitles);

        //港澳
        List<String> gangaotaiHrefs = page.getHtml().css("#col_gangaotai > div.l-left-col > div.b-left > ul:nth-child(4) > li > a", "href").all();
        List<String> gangaotaiTitles = page.getHtml().css("#col_gangaotai > div.l-left-col > div.b-left > ul:nth-child(4) > li > a","text").all();
        page.putField("gangaotaiHrefs",gangaotaiHrefs);
        page.putField("gangaotaiTitles",gangaotaiTitles);

        //中间 顶部图片
        String gangaotaiCenterTopImgHref = page.getHtml().css("#col_gangaotai > div.l-left-col > div.ilist > ul > li:nth-child(1) > a", "href").get();
        //spiderWithImgByHref(gangaotaiCenterTopImgHref);
        readySpider.add(gangaotaiCenterTopImgHref);

        //中间 底部图片
        String gangaotaiCenterBottomImgHref = page.getHtml().css("#col_gangaotai > div.l-left-col > div.ilist > ul > li:nth-child(2) > a","href").get();
        //spiderWithImgByHref(gangaotaiCenterBottomImgHref);
        readySpider.add(gangaotaiCenterBottomImgHref);

        //台湾民生  带有图片
        String mingshengImgHref = page.getHtml().css("#twms-news > ul > li.topic > span > a.i-t-title","href").get();
        //spiderWithImgByHref(mingshengImgHref);
        readySpider.add(mingshengImgHref);

        //台湾民生
        List<String> mingshengHrefs = page.getHtml().css("#twms-news > ul > li > a", "href").all();
        List<String> mingshengTitles  = page.getHtml().css("#twms-news > ul > li > a", "text").all();
        page.putField("mingshengHrefs",mingshengHrefs);
        page.putField("mingshengTitles",mingshengTitles);


    //时政要闻
        List<String> shizhengHrefs = page.getHtml().css("#col_politics > div.l-left-col > div.b-left > ul > li > a", "href").all();
        List<String> shizhengTitles = page.getHtml().css("#col_politics > div.l-left-col > div.b-left > ul > li > a","text").all();
        page.putField("shizhengHrefs",shizhengHrefs);
        page.putField("shizhengTitles",shizhengTitles);

        //中间顶部图片
        String shizhengCenterTopImgHref = page.getHtml().css("#col_politics > div.l-left-col > div.ilist > ul > li:nth-child(1) > a", "href").get();
        //spiderWithImgByHref(shizhengCenterTopImgHref);
        readySpider.add(shizhengCenterTopImgHref);

        //中间底部图片
        String shizhengCenterBottomImgHref = page.getHtml().css("#col_politics > div.l-left-col > div.ilist > ul > li:nth-child(2) > a", "href").get();
        //spiderWithImgByHref(shizhengCenterBottomImgHref);
        readySpider.add(shizhengCenterBottomImgHref);

    //历史档案
        List<String> historyHrefs = page.getHtml().css("#historical-news > ul > li > a", "href").all();
        List<String> historyTitles = page.getHtml().css("#historical-news > ul > li > a", "text").all();
        page.putField("historyHrefs",historyHrefs);
        page.putField("historyTitles",historyTitles);


    //最新新闻
        //左
        List<String>  latestLeftHrefs = page.getHtml().css("#latest-news > ul:nth-child(1) > li > a", "href").all();
        List<String>  latestLeftTitles = page.getHtml().css("#latest-news > ul:nth-child(1) > li > a", "text").all();
        List<String>  latestLeftTime = page.getHtml().css("#latest-news > ul:nth-child(1) > li > span", "text").all();
        page.putField("latestLeftHrefs",latestLeftHrefs);
        page.putField("latestLeftTitles",latestLeftTitles);
        page.putField("latestLeftTime",latestLeftTime);

        //右
        List<String> latestRightHrefs = page.getHtml().css("#latest-news > ul:nth-child(2) > li > a", "href").all();
        List<String> latestRightTitles = page.getHtml().css("#latest-news > ul:nth-child(2) > li > a", "text").all();
        List<String> latestRightTime = page.getHtml().css("#latest-news > ul:nth-child(2) > li > span", "text").all();
        page.putField("latestRightHrefs",latestRightHrefs);
        page.putField("latestRightTitles",latestRightTitles);
        page.putField("latestRightTime",latestRightTime);


        if(readySpider.size()>0) {
            for (String href : readySpider) {
                spiderWithImgByHref(href);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }



    public synchronized void spiderWithImgByHref(String href){

        Spider.create(guoNeiWithImgProcessor)
                .addUrl(href)
                .addPipeline(guoNeiWithImgPipeline)
                .runAsync();

    }






}
