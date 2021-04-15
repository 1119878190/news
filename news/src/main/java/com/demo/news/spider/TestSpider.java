package com.demo.news.spider;
/**
 * 测试
 */

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.IndexOtherWithImgPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;


public class TestSpider implements PageProcessor {


    @Override
    public void process(Page page) {




    }

    public void Spidersrc(String href){
        Spider.create(new IndexOtherWithImgProcessor())
                .addUrl(href)
                .addPipeline(new IndexOtherWithImgPipeline())
                .runAsync();
    }


    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36")
            .setTimeOut(5000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestSpider())
                .addUrl("http://baijiahao.baidu.com/s?id=1681939027075272920")
                .setDownloader(new SeleniumDownloader("D:\\software\\chromedriver.exe"))
                //.addPipeline(new IndexOtherWithImgPipeline())
                .thread(10)
                .start();
    }

}
