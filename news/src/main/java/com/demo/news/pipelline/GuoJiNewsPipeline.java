package com.demo.news.pipelline;
/**
 * 国际
 */

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.demo.news.entity.News;
import com.demo.news.service.GuoJiNewsService;
import com.demo.news.service.impl.ElasticSearchService;
import com.demo.news.utils.GuoJiConstant;
import com.sun.org.apache.regexp.internal.RESyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class GuoJiNewsPipeline implements Pipeline, GuoJiConstant {



    @Autowired
    private GuoJiNewsService guoJiNewsService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Override
    public void process(ResultItems resultItems, Task task) {

        //即时新闻
        List<String> jiShiNewsHrefs = resultItems.get("jiShiNewsHrefs");
        List<String> jiShiNewsTitles = resultItems.get("jiShiNewsTitles");
        saveTitleAndHref(jiShiNewsTitles,jiShiNewsHrefs,JISHINEWS);


        //焦点新闻
        List<String> jiaoDianNewsTitles = resultItems.get("jiaoDianNewsTitles");
        List<String> jiaoDianNewsHrefs = resultItems.get("jiaoDianNewsHrefs");
        saveTitleAndHref(jiaoDianNewsTitles,jiaoDianNewsHrefs,JIAODIANNEWS);

        //军事新闻
        List<String> junShiNewsTitles = resultItems.get("junShiNewsTitles");
        List<String> junShiNewsHrefs = resultItems.get("junShiNewsHrefs");
        saveTitleAndHref(junShiNewsTitles,junShiNewsHrefs,JUNSHINEWS);

        //热点新闻
        List<String> hotNewsTitles = resultItems.get("hotNewsTitles");
        List<String> hotNewsHrefs = resultItems.get("hotNewsHrefs");
        saveTitleAndHref(hotNewsTitles,hotNewsHrefs,HOTSNEWS);

        //最新新闻呃
        List<String> latestNewsTitles = resultItems.get("latestNewsTitles");
        List<String> latestNewsHrefs = resultItems.get("latestNewsHrefs");
        List<String> latestNewsTimes = resultItems.get("latestNewsTimes");
        saveTitleAndHrefAndTime(latestNewsTitles,latestNewsHrefs,latestNewsTimes,LATESTNEWS);

        guoJiNewsService.deleteNews();

    }

    public void saveTitleAndHref(List<String> title,List<String> href,int type){
        if (title.size() !=0&& href.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setType(type);
                news.setSaveTime(new Date());
                guoJiNewsService.insertNews(news);
                elasticSearchService.saveNews(news);

            }
        }


    }

    public void saveTitleAndHrefAndTime(List<String> title,List<String> href,List<String> times,int type){
        if (title.size() !=0&& href.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setType(type);
                news.setSrc(times.get(i));
                news.setSaveTime(new Date());
                guoJiNewsService.insertNews(news);
                elasticSearchService.saveNews(news);

            }
        }
    }


}
