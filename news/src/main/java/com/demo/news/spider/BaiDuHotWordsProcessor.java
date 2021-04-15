package com.demo.news.spider;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Component
public class BaiDuHotWordsProcessor implements PageProcessor {



    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36")
            .setTimeOut(5000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {

        List<String> baiDuHrefs = page.getHtml().css("tbody tr td.keyword a:nth-child(1)", "href").all();
        List<String> baiDuTitles = page.getHtml().css("tbody tr td.keyword a:nth-child(1)", "text").all();


        page.putField("baiDuHrefs",baiDuHrefs);
        page.putField("baiDuTitles",baiDuTitles);
    }




}
