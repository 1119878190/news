package com.demo.news.pipelline;
//主页热点新闻持久化数据


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
public class IndexNewsPipeline implements Pipeline, Constant {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private NewsService newsService;

    /**
     * 数据持久化
     * @param resultItems resultItems
     * @param task task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {


        //热点新闻
        List<String> hrefs = resultItems.get("hrefs");
        List<String> titles = resultItems.get("titles");


        System.out.println("开始插入主页热点新闻");
        if (hrefs.size() != 0 && titles.size() != 0){
            for (int i = 0; i < hrefs.size(); i++) {
                News news = new News();
                news.setHref(hrefs.get(i));
                news.setTitle(titles.get(i));
                news.setSaveTime(new Date());
                news.setType(1);
                this.newsService.insertNews(news);

               // elasticSearchService.saveNews(news);

            }
        }
        System.out.println("主页热点希望存储完毕");

        //查询数据库中是否有数据
        int count = newsService.queryCount(HomePage);
        if (count > 30){
            //如果有数据,则最多保留昨天的数据
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();

            calendar.setTime(now);
            calendar.add(Calendar.HOUR,-2);
            Date twoHourAgo = calendar.getTime();

            //删除数据
            newsService.deleteNews(twoHourAgo);
        }






    }
}
