package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.service.NewsService;
import com.demo.news.service.impl.ElasticSearchService;
import com.demo.news.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class OtherNewsPipeline implements Pipeline, Constant {

    @Autowired
    private NewsService newsService;


    @Override
    public void process(ResultItems resultItems, Task task) {

        System.out.println("开始保存主页其它数据");
    //主页国内新闻
        List<String> guoNeiNewsTitle = resultItems.get("guoNeiNewsTitle");
        List<String> guoNeiNewsHref = resultItems.get("guoNeiNewsHref");
        //deleteData(HomePageGuoNeiNews);
        saveTitleAndHref(guoNeiNewsTitle,guoNeiNewsHref,HomePageGuoNeiNews);

        //删除国内新闻
        //deleteData(GuoNeiImgTop,GuoNeiHotCheck);


       List<String> guoNeiTopCheckTitle = resultItems.get("guoNeiTopCheckTitle");
       List<String> guoNeiTopCheckHref = resultItems.get("guoNeiTopCheckHref");
       saveTitleAndHref(guoNeiTopCheckTitle,guoNeiTopCheckHref,GuoNeiHotCheck);



    //主页国际新闻
        List<String> guoJiNewsTitle = resultItems.get("guoJiNewsTitle");
        List<String> guoJiNewsHref = resultItems.get("guoJiNewsHref");
        //deleteData(HomePageGuoJiNews);
        saveTitleAndHref(guoJiNewsTitle,guoJiNewsHref,HomePageGuoJiNews);

        //删除国际新闻
        //deleteData(GuoJiImgTop,GuoJiHotCheck);



        List<String> guoJiHotWordsTitle = resultItems.get("guoJiHotWordsTitle");
        List<String> guoJiHotWordsHref = resultItems.get("guoJiHotWordsHref");
        saveTitleAndHref(guoJiHotWordsTitle,guoJiHotWordsHref,GuoJiHotCheck);


    //军事
        List<String> junShiNewsTitle = resultItems.get("junShiNewsTitle");
        List<String> junShiNewsHref = resultItems.get("junShiNewsHref");
        //deleteData(HomePageJunShiNews);
        saveTitleAndHref(junShiNewsTitle,junShiNewsHref,HomePageJunShiNews);

        //deleteData(JunShiImgTop,JunShiChinaWar);



        List<String> junShiHotCheckTitle = resultItems.get("junShiHotCheckTitle");
        List<String> juShiHotCheckHref = resultItems.get("juShiHotCheckHref");
        saveTitleAndHref(junShiHotCheckTitle,juShiHotCheckHref,JunShiHotCheck);

        List<String> junShiChinaWarTitle = resultItems.get("junShiChinaWarTitle");
        List<String> junShiChinaWarHref  = resultItems.get("junShiChinaWarHref");
        saveTitleAndHref(junShiChinaWarTitle,junShiChinaWarHref,JunShiChinaWar);

    //娱乐

        List<String> yuLeNewsTitle = resultItems.get("guoNeiNewsTitle");
        List<String> yuLeNewsHref = resultItems.get("guoNeiNewsHref");
        //deleteData(HomePageYuLeNews);
        saveTitleAndHref(yuLeNewsTitle,yuLeNewsHref,HomePageYuLeNews);

        //删除国内新闻
        //deleteData(YuLeImgTop,YuLeBottomImgs);


        List<String> yuLeHotWordsTitle = resultItems.get("yuLeHotWordsTitle");
        List<String> yuLeHotWordsHref = resultItems.get("yuLeHotWordsHref");
        saveTitleAndHref(yuLeHotWordsTitle,yuLeHotWordsHref,YuLeHotWords);

        List<String> yuLeBottomImgsTitle = resultItems.get("yuLeBottomImgsTitle");
        List<String> yuleBottomImgsHref = resultItems.get("yuleBottomImgsHref");
        List<String> yuleBottomImgsSrc = resultItems.get("yuleBottomImgsSrc");
        saveTitleAndHrefAndSrc(yuLeBottomImgsTitle,yuleBottomImgsHref,yuleBottomImgsSrc,YuLeBottomImgs);

        //deleteData();
    }

    //删除
    public void deleteData(int start,int end){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR,-2);
        Date twoHourAgo = calendar.getTime();

        for (int i = start; i <= end; i++) {
            newsService.deleteNewsByType(twoHourAgo,i);
        }

    }



    public void deleteData(){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR,-2);
        Date twoHourAgo = calendar.getTime();

        newsService.deleteNewsByType(twoHourAgo,1);
    }

    //保存
    public void saveTitleAndHref(List<String> title,List<String> href,int type){
        if (title.size() != 0 && href.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setType(type);
                news.setSaveTime(new Date());
                newsService.insertNews(news);

            }
        }
    }
    public void saveTitleAndHrefAndSrc(List<String> title,List<String> href,List<String> src,int type){
        if (title.size() != 0 && href.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setSrc(src.get(i));
                news.setType(type); 
                news.setSaveTime(new Date());
                newsService.insertNewsWithImg(news);

            }
        }
    }


}
