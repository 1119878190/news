package com.demo.news.es;

import com.demo.news.entity.News;
import com.demo.news.service.GuoNeiNewsService;
import com.demo.news.service.impl.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;

@Component("esGuoNeiNews")
public class GuoNeiNews implements Runnable {

    @Autowired
    private GuoNeiNewsService guoNeiNewsService;

    @Autowired
    private  ElasticSearchService elasticSearchService;

    @Override
    public void run() {
        List<News> newsList = guoNeiNewsService.queryAllNews();
        for (News news : newsList) {
            elasticSearchService.saveNews(news);
        }
    }
}
