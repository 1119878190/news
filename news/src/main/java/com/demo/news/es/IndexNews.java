package com.demo.news.es;

import com.demo.news.entity.News;
import com.demo.news.service.NewsService;
import com.demo.news.service.impl.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("esIndexNews")
public class IndexNews implements Runnable {


    @Autowired
    private NewsService newsService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Override
    public void run() {
        List<News> newsList = newsService.queryAllNews();
        for (News news : newsList) {
            elasticSearchService.saveNews(news);
        }
    }
}
