package com.demo.news.spider;

import com.demo.news.utils.Constant;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeiBoHotWordsProcessor implements PageProcessor, Constant {





    private String defaultStr = "https://s.weibo.com";
    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36")
            .setTimeOut(8000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        List<String> originHref = page.getHtml().css("#pl_top_realtimehot > table > tbody > tr > td.td-02 > a", "href").all();
        List<String> originTitles = page.getHtml().css("#pl_top_realtimehot > table > tbody > tr > td.td-02 > a", "text").all();
        List<String> weiBoHrefs = new ArrayList<>();
        List<String> weiBoTitles = new ArrayList<>();

        //一共50条数据
        for (int i = 0; i < 50; i++) {
            StringBuffer href = new StringBuffer(originHref.get(i));
            //将最前面何最后面的#去掉
            String title = originTitles.get(i).substring(1,originTitles.get(i).length()-1);
            href.insert(0,defaultStr);
            weiBoTitles.add(title);
            weiBoHrefs.add(href.toString());
        }

        page.putField("weiBoTitles",weiBoTitles);
        page.putField("weiBoHrefs",weiBoHrefs);


    }


}
