package com.demo.news.spider;

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.GuoJiNewsPipeline;
import com.demo.news.pipelline.GuoJiWithImgPipeline;
import org.apache.tomcat.util.net.openssl.OpenSSLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
public class GuoJiNewsProcessor implements PageProcessor {



    Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36")
            .setTimeOut(8000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Autowired
    private GuoJiWithImgProcessor guoJiWithImgProcessor;

    @Autowired
    private GuoJiWithImgPipeline guoJiWithImgPipeline;

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {

        List<String> readSpider = new ArrayList<>();

        //即时新闻
        List<String> jiShiNewsHrefs = page.getHtml().css("#instant-news > ul > li > a","href").all();
        List<String> jiShiNewsTitles = page.getHtml().css("#instant-news > ul > li > a","text").all();
        page.putField("jiShiNewsHrefs",jiShiNewsHrefs);
        page.putField("jiShiNewsTitles",jiShiNewsTitles);

        //焦点新闻
        List<String>  jiaoDianNewsHrefs= page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "href").all();
        List<String> jiaoDianNewsTitles = page.getHtml().css("#col_focus > div.l-left-col > div.b-left > ul > li > a", "text").all();
        page.putField("jiaoDianNewsHrefs",jiaoDianNewsHrefs);
        page.putField("jiaoDianNewsTitles",jiaoDianNewsTitles);

        //焦点新闻  --图片
        String jiaoDianTopImgHref = page.getHtml().css("#col_focus > div.l-left-col > div.ilist > ul > li:nth-child(1) > a", "href").get();
        String jiaoDianBottomImgHref = page.getHtml().css("#col_focus > div.l-left-col > div.ilist > ul > li:nth-child(2) > a","href").get();
        readSpider.add(jiaoDianTopImgHref);
        readSpider.add(jiaoDianBottomImgHref);

        //图片新闻
        List<String> imgNewsHrefs = page.getHtml().css("#focus-aside-news > div.bd > div > ul > li > a", "href").all();
        for (String imgNewsHref : imgNewsHrefs) {
            readSpider.add(imgNewsHref);
        }


        //军事新闻
        List<String> junShiNewsHrefs = page.getHtml().css("#MilitaryNews > div.b-left > ul > li > a", "href").all();
        List<String> junShiNewsTitles = page.getHtml().css("#MilitaryNews > div.b-left > ul > li > a", "text").all();
        page.putField("junShiNewsHrefs",junShiNewsHrefs);
        page.putField("junShiNewsTitles",junShiNewsTitles);

        //军事新闻 图片
        String junShiTopImgHref = page.getHtml().css("#MilitaryNews > div.ilist > ul > li:nth-child(1) > a", "href").get();
        String junShiBottomImgHref = page.getHtml().css("#MilitaryNews > div.ilist > ul > li:nth-child(2) > a", "href").get();
        readSpider.add(junShiTopImgHref);
        readSpider.add(junShiBottomImgHref);

        //热点新闻
        List<String> hotNewsHrefs = page.getHtml().xpath("//*[@id='hot-article']/ul/li//a[1]/@href").all();
        List<String> hotNewsTitles = page.getHtml().xpath("//*[@id='hot-article']/ul/li//a[1]/text()").all();
        page.putField("hotNewsHrefs",hotNewsHrefs);
        page.putField("hotNewsTitles",hotNewsTitles);

        //最新新闻
        List<String> latestNewsHrefs = page.getHtml().css("#latest-news > ul > li > a", "href").all();
        List<String> latestNewsTitles = page.getHtml().css("#latest-news > ul > li > a", "text").all();
        List<String> latestNewsTimes = page.getHtml().css("#latest-news > ul > li > span","text").all();
        page.putField("latestNewsHrefs",latestNewsHrefs);
        page.putField("latestNewsTitles",latestNewsTitles);
        page.putField("latestNewsTimes",latestNewsTimes);

        //爬取带有图片的新闻
        for (String s : readSpider) {
            spiderWithImgByHref(s);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void spiderWithImgByHref(String href){
        if (!href.equals("") && href.length()>0){
            Spider.create(guoJiWithImgProcessor)
                    .addUrl(href)
                    .addPipeline(guoJiWithImgPipeline)
                    .runAsync();
        }


    }




}
