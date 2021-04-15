package com.demo.news.spider;
//主页新闻抓取
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;


import java.util.*;

@Component
public class IndexNewsProcessor implements PageProcessor {



    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36")
            .setTimeOut(8000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);

    @Override
    public Site getSite() {
        return site;
    }


    /**
     * 页面抽取
     * @param page page
     */
    @Override
    public void process(Page page) {

        //1.热点新闻
        //获取所有的超链接
        List<String> allHref = page.getHtml().xpath("//*[@id='pane-news']//li//a/@href").all();
        System.out.println("超链接的集合大小是: "+allHref.size());


        //获取所有的超链接文本

        List<String> allTitle = page.getHtml().xpath("//*[@id='pane-news']//ul/li//*/text()").all();


        //1.热点数据整理
        List<String> titles = new ArrayList<String>();
        //去除空字符
        for (String title : allTitle) {
            if (StringUtils.isNotBlank(title) && StringUtils.isNotEmpty(title)){
                titles.add(title);
            }
        }

        System.out.println("title的集合大小是:" + titles.size());
        System.out.println("完成");




       page.putField("hrefs",allHref);
       page.putField("titles",titles);





    }


    /*public  void main(String[] args) {

        Spider.create(new NewsProcessor())
                .addUrl("https://news.baidu.com/")
                .thread(5)
                .setDownloader(new SeleniumDownloader("D:\\software\\chromedriver.exe").setSleepTime(1000))
                .addPipeline(this.myPipeline)
                .runAsync();
    }*/

}
