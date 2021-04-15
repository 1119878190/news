package com.demo.news.spider;


import com.demo.news.pipelline.IndexOtherWithImgPipeline;
import com.demo.news.pipelline.IndexOtherWithImgYuLePipeline;
import com.demo.news.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


import java.util.ArrayList;
import java.util.List;

@Component
public class IndexOtherNewsProcessor implements PageProcessor, Constant {


    @Autowired
    private IndexOtherWithImgProcessor indexOtherWithImgProcessor;

    @Autowired
    private IndexOtherWithImgYuLeProcessor indexOtherWithImgYuLeProcessor;

    @Autowired
    private IndexOtherWithImgPipeline indexOtherWithImgPipeline;

    @Autowired
    private IndexOtherWithImgYuLePipeline indexOtherWithImgYuLePipeline;

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

        List<String> readySpider = new ArrayList<>();
        List<String> readySpiderYuLe = new ArrayList<>();
        System.out.println("开始采集主页国内新闻");

//主页国内新闻
        //左侧新闻列表
        List<String> guoNeiNewsTitle = page.getHtml().css("#guonei > div.l-left-col.col-mod > ul > li > a","text").all();
        List<String> guoNeiNewsHref = page.getHtml().css("#guonei > div.l-left-col.col-mod > ul > li > a").links().all();

        //图片上
        String guoNeiImgTopHref = page.getHtml().css("#guonei > div.l-middle-col > div > div.bd > div > div.imagearea-top > div > a.item-image", "href").get();
        //spiderWithImgByHref(guoNeiImgTopHref);
        readySpider.add(guoNeiImgTopHref);
        
        //图片中左
        String guoNeiImgBottomRightHref = page.getHtml().css("#guonei > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(1) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoNeiImgBottomRightHref);
        readySpider.add(guoNeiImgBottomRightHref);

        //图片中右
        String guoNeiImgBottomLeftHref = page.getHtml().css("#guonei > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(2) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoNeiImgBottomLeftHref);
        readySpider.add(guoNeiImgBottomLeftHref);

        //图片右左
        String guoNeiImgListRightHref = page.getHtml().css("#aside-civil-pic > div.bd > div > div > div:nth-child(1) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoNeiImgListRightHref);
        readySpider.add(guoNeiImgListRightHref);

        //图片右右
        String guoNeiImgListLeftHref = page.getHtml().css("#aside-civil-pic > div.bd > div > div > div:nth-child(2) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoNeiImgListLeftHref);
        readySpider.add(guoNeiImgListLeftHref);

        //热门点击
        List<String> guoNeiTopCheckTitle = page.getHtml().css("#civil-aside-tophit > div.bd > ol > li > a", "title").all();
        List<String> guoNeiTopCheckHref = page.getHtml().css("#civil-aside-tophit > div.bd > ol > li > a", "href").all();

        System.out.println("主页国内新闻爬取完毕");



        System.out.println("开始爬取主页国际新闻");
//主页国际新闻
        List<String> guoJiNewsTitle = page.getHtml().css("#guojie > div.l-left-col.col-mod > ul > li > a","text").all();
        List<String> guoJiNewsHref = page.getHtml().css("#guojie > div.l-left-col.col-mod > ul > li > a").links().all();

        //图片上
        String guoJiImgTopHref = page.getHtml().css("#guojie > div.l-middle-col > div > div.bd > div > div.imagearea-top > div > a.item-image", "href").get();
        //spiderWithImgByHref(guoJiImgTopHref);
        readySpider.add(guoJiImgTopHref);
        
        //图片中左
        String guoJiImgBottomRightHref = page.getHtml().css("#guojie > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(1) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoJiImgBottomRightHref);
        readySpider.add(guoJiImgBottomRightHref);

        //图片中右
        String guoJiImgBottomLeftHref = page.getHtml().css("#guojie > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(2) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoJiImgBottomLeftHref);
        readySpider.add(guoJiImgBottomLeftHref);

        //图片右左
        String guoJiImgListRightHref = page.getHtml().css("#huanqiu > div.bd > div > div > div:nth-child(1) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoJiImgListRightHref);
        readySpider.add(guoJiImgListRightHref);

        //图片右右
        String guoJiImgListLeftHref = page.getHtml().css("#huanqiu > div.bd > div > div > div:nth-child(2) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(guoJiImgListLeftHref);
        readySpider.add(guoJiImgListLeftHref);

        //热门点击
        List<String> guoJiHotWordsTitle = page.getHtml().css("#internal-hotword > div.bd > ol > li > a", "title").all();
        List<String> guoJiHotWordsHref = page.getHtml().css("#internal-hotword > div.bd > ol > li > a", "href").all();

        System.out.println("主页国际新闻爬取完毕");
        //存入到pipeline,准备持久化



        System.out.println("开始爬取主页军事新闻");
    //主页军事新闻
        List<String> junShiNewsTitle = page.getHtml().css("#junshi > div.l-left-col.col-mod > ul > li > a", "text").all();
        List<String> junShiNewsHref = page.getHtml().css("#junshi > div.l-left-col.col-mod > ul > li > a","href").all();

        //图片上
        String junShiImgTopHref = page.getHtml().css("#junshi > div.l-middle-col > div > div.bd > div > div.imagearea-top > div > a.item-image", "href").get();
        //spiderWithImgByHref(junShiImgTopHref);
        readySpider.add(junShiImgTopHref);

        //图片中左
        String junShiImgBottomRightHref = page.getHtml().css("#junshi > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(1) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(junShiImgBottomRightHref);
        readySpider.add(junShiImgBottomRightHref);

        //图片中右
        String junShiImgBottomLeftHref = page.getHtml().css("#junshi > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(2) > a:nth-child(1)", "href").get();
        //spiderWithImgByHref(junShiImgBottomLeftHref);
        readySpider.add(junShiImgBottomLeftHref);

        //热门点击 44
        List<String> junShiHotCheckTitle = page.getHtml().css("#mil-aside-video > div.bd > ol > li > a", "text").all();
        List<String> juShiHotCheckHref = page.getHtml().css("#mil-aside-video > div.bd > ol > li > a", "href").all();

        //中国军情 45
        List<String> junShiChinaWarTitle = page.getHtml().css("#mil-aside-jq > div.bd > ul > li > a", "text").all();
        List<String> junShiChinaWarHref = page.getHtml().css("#mil-aside-jq > div.bd > ul > li > a", "href").all();

        System.out.println("主页国际新闻爬取完毕");

        System.out.println("开始爬取主页娱乐新闻");

    //主页娱乐新闻
        List<String> yuLeNewsTitle = page.getHtml().css("#yule > div.l-left-col.col-mod > ul > li > a", "text").all();
        List<String> yuLeNewsHref = page.getHtml().css("#yule > div.l-left-col.col-mod > ul > li > a","href").all();

        //图片上
        String yuLeImgTopHref = page.getHtml().css("#yule > div.l-middle-col > div > div.bd > div > div.imagearea-top > div > a.item-image", "href").get();
        //spiderWithImgByHrefYuLe(yuLeImgTopHref);
        readySpiderYuLe.add(yuLeImgTopHref);

        //图片中右
        String yuLeImgBottomRightHref = page.getHtml().css("#yule > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(2) > a:nth-child(1)", "href").get();
        //spiderWithImgByHrefYuLe(yuLeImgBottomRightHref);
        readySpiderYuLe.add(yuLeImgBottomRightHref);

        //图片中左
        String yuLeImgBottomLeftHref = page.getHtml().css("#yule > div.l-middle-col > div > div.bd > div > div.imagearea-bottom > div:nth-child(1) > a:nth-child(1)", "href").get();
        //spiderWithImgByHrefYuLe(yuLeImgBottomLeftHref);
        readySpiderYuLe.add(yuLeImgBottomLeftHref);

        //图片右左
        String yuLeImgListRightHref = page.getHtml().css("#yule-aside-star > div.bd > div > div > div:nth-child(1) > a:nth-child(1)", "href").get();
        //spiderWithImgByHrefYuLe(yuLeImgListRightHref);
        readySpiderYuLe.add(yuLeImgListRightHref);

        //图片右右
        String yuLeImgListLeftHref = page.getHtml().css("#yule-aside-star > div.bd > div > div > div:nth-child(2) > a:nth-child(1)", "href").get();
        //spiderWithImgByHrefYuLe(yuLeImgListLeftHref);
        readySpiderYuLe.add(yuLeImgListLeftHref);

        //热门点击
        List<String> yuLeHotWordsTitle = page.getHtml().css("#yule-aside-hotwords > div > ul > li > a", "text").all();
        List<String> yuLeHotWordsHref = page.getHtml().css("#yule-aside-hotwords > div > ul > li > a", "href").all();

        //底部图片新闻
        List<String> yuLeBottomImgsTitle = page.getHtml().css("#sports-picwall > div > div > div > div > a:nth-child(1)","title").all();
        List<String> yuleBottomImgsHref = page.getHtml().css("#sports-picwall > div > div > div > div > a:nth-child(1)","href").all();
        List<String> yuleBottomImgsSrc = page.getHtml().css("#sports-picwall > div > div > div > div > a:nth-child(1) > img","src").all();

        System.out.println("正在将爬取的主页其它新闻添加到pipeline中");
        //国内
        page.putField("guoNeiNewsTitle",guoNeiNewsTitle);
        page.putField("guoNeiNewsHref",guoNeiNewsHref);
        page.putField("guoNeiTopCheckTitle",guoNeiTopCheckTitle);
        page.putField("guoNeiTopCheckHref",guoNeiTopCheckHref);


        //国际
        page.putField("guoJiNewsTitle",guoJiNewsTitle);
        page.putField("guoJiNewsHref",guoJiNewsHref);
        page.putField("guoJiHotWordsTitle",guoJiHotWordsTitle);
        page.putField("guoJiHotWordsHref",guoJiHotWordsHref);


        //军事
        page.putField("junShiNewsTitle",junShiNewsTitle);
        page.putField("junShiNewsHref",junShiNewsHref);
        page.putField("junShiHotCheckTitle",junShiHotCheckTitle);
        page.putField("juShiHotCheckHref",juShiHotCheckHref);
        page.putField("junShiChinaWarTitle",junShiChinaWarTitle);
        page.putField("junShiChinaWarHref",junShiChinaWarHref);


        //娱乐
        page.putField("yuLeNewsTitle",yuLeNewsTitle);
        page.putField("yuLeNewsHref",yuLeNewsHref);
        page.putField("yuLeHotWordsTitle",yuLeHotWordsTitle);
        page.putField("yuLeHotWordsHref",yuLeHotWordsHref);
        page.putField("yuLeBottomImgsTitle",yuLeBottomImgsTitle);
        page.putField("yuleBottomImgsHref",yuleBottomImgsHref);
        page.putField("yuleBottomImgsSrc",yuleBottomImgsSrc);

        if (readySpider.size()>0){
            for (String href : readySpider) {
                spiderWithImgByHref(href);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (readySpiderYuLe.size()>0){
            for (String href : readySpiderYuLe) {
                spiderWithImgByHrefYuLe(href);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }





    public  synchronized void spiderWithImgByHref(String href){

        if ( !href.equals("") && href.length()>0 ){
            Spider.create(indexOtherWithImgProcessor)
                    .addUrl(href)
                    .addPipeline(this.indexOtherWithImgPipeline)
                    .runAsync();
        }


    }

    public synchronized void spiderWithImgByHrefYuLe(String href){
        if ( !href.equals("") && href.length()>0 ) {
            Spider.create(indexOtherWithImgYuLeProcessor)
                    .addUrl(href)
                    .addPipeline(indexOtherWithImgYuLePipeline)
                    .runAsync();
        }
    }


}
