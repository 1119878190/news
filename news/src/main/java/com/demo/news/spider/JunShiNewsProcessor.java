package com.demo.news.spider;

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.JunShiNewsPipeline;
import com.demo.news.pipelline.JunShiWithImgMilPipeline;
import com.demo.news.pipelline.JunShiWithImgPipeline;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 军事
 */

@Component
public class JunShiNewsProcessor implements PageProcessor {


    @Autowired
    private JunShiWithImgProcessor junShiWithImgProcessor;

    @Autowired
    private JunShiWithImgPipeline junShiWithImgPipeline;

    @Autowired
    private JunShiWithImgMilProcessor junShiWithImgMilProcessor;

    @Autowired
    private JunShiWithImgMilPipeline junShiWithImgMilPipeline;

   private Site site = Site.me()
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

        //即时新闻
        List<String> jiShiNewsTitles = page.getHtml().css("#instant-news > ul > li > a", "text").all();
        List<String> jiShiNewsHrefs = page.getHtml().css("#instant-news > ul > li > a", "href").all();
        page.putField("jiShiNewsTitles",jiShiNewsTitles);
        page.putField("jiShiNewsHrefs",jiShiNewsHrefs);

        //焦点新闻
        List<String> jiaoDianNewsTitles = page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "text").all();
        List<String> jiaoDianNewsHrefs = page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "href").all();
        page.putField("jiaoDianNewsTitles",jiaoDianNewsTitles);
        page.putField("jiaoDianNewsHrefs",jiaoDianNewsHrefs);

        //焦点新闻   图片
        String jiaoDianTopImg = page.getHtml().css("#col_focus > div.l-left-col > div.ilist > ul > li:nth-child(1) > a", "href").get();
        readySpider.add(jiaoDianTopImg);
        String jiaoDianBottomImg = page.getHtml().css("#col_focus > div.l-left-col > div.ilist > ul > li:nth-child(2) > a", "href").get();
        readySpider.add(jiaoDianBottomImg);

        //军事视频
        List<String> junSHiVideoTitles = page.getHtml().css("#mil-video > div > p.title > a", "text").all();
        List<String> junSHiVideoHrefs  = page.getHtml().css("#mil-video > div > p.title > a", "href").all();
        List<String> junShiVideoSrcs = page.getHtml().css("#mil-video > div > p.picture > a > img", "src").all();
        page.putField("junSHiVideoTitles",junSHiVideoTitles);
        page.putField("junSHiVideoHrefs",junSHiVideoHrefs);
        page.putField("junShiVideoSrcs",junShiVideoSrcs);

        //中国军情
        List<String>  chinaMilitaryTitles = page.getHtml().css("#Chinamil > div.b-left > ul > li > a", "text").all();
        List<String> chinaMilitaryHrefs = page.getHtml().css("#Chinamil > div.b-left > ul > li > a", "href").all();
        page.putField("chinaMilitaryTitles",chinaMilitaryTitles);
        page.putField("chinaMilitaryHrefs",chinaMilitaryHrefs);

        //中国军情  图片
        String chinaMilitaryTopImg = page.getHtml().css("#Chinamil > div.ilist > ul > li:nth-child(1) > a", "href").get();
        readySpider.add(chinaMilitaryTopImg);
        String chinaMilitaryBottomImg = page.getHtml().css("#Chinamil > div.ilist > ul > li:nth-child(2) > a", "href").get();
        readySpider.add(chinaMilitaryBottomImg);

        //台海聚焦
        List<String> taiWanFocusingTitles = page.getHtml().css("#thjj-news > ul > li > a", "text").all();
        List<String> taiWanFocusingHrefs = page.getHtml().css("#thjj-news > ul > li > a", "href").all();
        page.putField("taiWanFocusingTitles",taiWanFocusingTitles);
        page.putField("taiWanFocusingHrefs",taiWanFocusingHrefs);

        //台湾聚焦  顶部图片
        String taiWanFocusingImgHref = page.getHtml().css("#thjj-news > ul > li.topic > span > a.i-t-title", "href").get();
        readySpider.add(taiWanFocusingImgHref);

        //国际军情
        List<String> worldMilitaryTitles = page.getHtml().css("#col_guojijq > div.l-left-col > div.b-left > ul > li > a", "text").all();
        List<String> worldMilitaryHrefs = page.getHtml().css("#col_guojijq > div.l-left-col > div.b-left > ul > li > a", "href").all();
        page.putField("worldMilitaryTitles",worldMilitaryTitles);
        page.putField("worldMilitaryHrefs",worldMilitaryHrefs);

        //国际军情 图片
        String worldMilitaryTopImg = page.getHtml().css("#col_guojijq > div.l-left-col > div.ilist > ul > li:nth-child(1) > a", "href").get();
        readySpider.add(worldMilitaryTopImg);
        String worldMilitaryBottomImg = page.getHtml().css("#col_guojijq > div.l-left-col > div.ilist > ul > li:nth-child(2) > a", "href").get();
        readySpider.add(worldMilitaryBottomImg);

        //热门新闻
        List<String> hotNewsTitles = page.getHtml().xpath("//*[@id='hot-article']/ul/li//a[1]/text()").all();
        List<String> hotNewsHrefs = page.getHtml().xpath("//*[@id='hot-article']/ul/li//a[1]/@href").all();
        page.putField("hotNewsTitles",hotNewsTitles);
        page.putField("hotNewsHrefs",hotNewsHrefs);

        //军事  图片
        List<String> militaryPictures = page.getHtml().css("#mil-picnews > div > ul > li > a", "href").all();

        //最新新闻
        List<String> latestNewsTitles = page.getHtml().css("#latest-news > ul > li > a", "text").all();
        List<String> latestNewsHrefs = page.getHtml().css("#latest-news > ul > li > a", "href").all();
        List<String> latestNewsTimes = page.getHtml().css("#latest-news > ul > li > span", "text").all();
        page.putField("latestNewsTitles",latestNewsTitles);
        page.putField("latestNewsHrefs",latestNewsHrefs);
        page.putField("latestNewsTimes",latestNewsTimes);




        for (String href : readySpider) {
            spiderImgByHref(href);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (String militaryPicture : militaryPictures) {
            spiderImgByHrefMilNews(militaryPicture);
        }
    }


    public synchronized void spiderImgByHref(String href){
        Spider.create(junShiWithImgProcessor)
                .addUrl(href)
                .addPipeline(junShiWithImgPipeline)
                .runAsync();

    }

    public void spiderImgByHrefMilNews(String href){
        Spider.create(junShiWithImgMilProcessor)
                .addUrl(href)
                .addPipeline(junShiWithImgMilPipeline)
                .thread(5)
                .runAsync();
    }



}
