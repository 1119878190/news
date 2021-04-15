package com.demo.news.spider;
/**
 * 国内页面带有图片新闻
 */

import com.demo.news.entity.News;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;

@Component
public class GuoNeiWithImgProcessor implements PageProcessor {

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
        News news = new News();

        //若为百度
        if (page.getUrl().regex("http://baijiahao\\.baidu\\.com/s\\?id=\\d*").match()) {

            String title = page.getHtml().css("div.article-title > h2","text").get();
            String src = null;
            String href = null;
            // 虽然是以baijihao开头,但打开不是百度的新闻
            if (title==null){
                href = page.getUrl().get();
                title = page.getHtml().css("#title","text").get();
                src = page.getHtml().css("#container > article > p > img","src").get();
                news.setTitle(title);
                news.setHref(href);
                news.setSaveTime(new Date());
                news.setSrc(src);
                page.putField("news",news);
            //百度
            }else {
                href = page.getUrl().get();
                src = page.getHtml().xpath("//*[@class='article-content']/div[@class='img-container'][2]/img/@src").get();
                if (src==null){
                    src = page.getHtml().css(".img-container > img","src").get();
                    if (src == null){
                        src = page.getHtml().css("div.content-container img","src").get();
                    }
                }
                news.setSrc(src);
                news.setHref(href);
                news.setSaveTime(new Date());
                news.setTitle(title);
                page.putField("news",news);
                //System.out.println("-------------------------------------------------"+news.toString());
            }

        }
        //华声今日
        if (page.getUrl().regex("https://top\\.voc\\.com\\.cn/[a-z0-9_/.]*").match()){
            String src = page.getHtml().css("div.article-ps > p > img","src").get();
            String url = page.getUrl().toString();
            String title = page.getHtml().css(".article > h1","text").get();
            news.setTitle(title);
            news.setHref(url);
            news.setSaveTime(new Date());
            news.setSrc(src);
            page.putField("news",news);
            //System.out.println("-------------------------"+news.toString());

        }
        //环球网
        if (page.getUrl().regex("https://3w\\.huanqiu\\.com/a/[a-z]*").match()){
            String src = page.getHtml().css("#article > div.a-con > p > img","src").get();
            String url = page.getUrl().toString();
            String title = page.getHtml().css("#article > h1 > strong","text").get();
            news.setTitle(title);
            news.setHref(url);
            news.setSaveTime(new Date());
            news.setSrc(src);
            page.putField("news",news);
            // System.out.println("-------------------------"+news.toString());

        }
        //澎湃
        if (page.getUrl().regex("https://m\\.thepaper\\.cn/[a-z0-9]*").match()){
            String url = page.getUrl().toString();
            String title = page.getHtml().css("#title","text").get();
            String src = page.getHtml().css("#container > article > p > img","src").get();
            news.setTitle(title);
            news.setHref(url);
            news.setSaveTime(new Date());
            news.setSrc(src);
            page.putField("news",news);
            //System.out.println("-------------------------"+news.toString());
        }
    }


}
