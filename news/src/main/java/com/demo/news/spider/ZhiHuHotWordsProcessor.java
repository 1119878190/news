package com.demo.news.spider;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
public class ZhiHuHotWordsProcessor implements PageProcessor {



   private String prefix = "https://tophub.today";

    Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36")
            .setTimeOut(20*1000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Override
    public void process(Page page) {
        List<String> originHrefs = page.getHtml().css("#page > div.c-d.c-d-e > div.Zd-p-Sc > div:nth-child(1) > div.cc-dc-c > div > div.jc-c > table > tbody > tr > td.al > a", "href").all();
        List<String> titles = page.getHtml().css("#page > div.c-d.c-d-e > div.Zd-p-Sc > div:nth-child(1) > div.cc-dc-c > div > div.jc-c > table > tbody > tr > td.al > a", "text").all();
        List<String> hrefs = new ArrayList<>();

        for (int i = 0; i < originHrefs.size(); i++) {
            StringBuffer href = new StringBuffer(originHrefs.get(i));
            href.insert(0,prefix);
            hrefs.add(href.toString());
        }

        page.putField("zhiHuTitles",titles);
        page.putField("zhiHuHrefs",hrefs);

    }

    @Override
    public Site getSite() {
        return site;
    }


}
