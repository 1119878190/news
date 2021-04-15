package com.demo.news.es;


import com.demo.news.entity.News;
import com.demo.news.service.GuoJiNewsService;
import com.demo.news.service.impl.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("esGuoJiNews")
public class GuoJiNews implements Runnable {

    @Autowired
    private GuoJiNewsService guoJiNewsService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Override
    public void run() {
        List<News> newsList = guoJiNewsService.queryAllNews();
        for (News news : newsList) {
            elasticSearchService.saveNews(news);
        }
    }
}
