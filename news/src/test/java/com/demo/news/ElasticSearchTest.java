package com.demo.news;


import com.demo.news.entity.News;
import com.demo.news.service.NewsService;
import com.demo.news.service.impl.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NewsApplication.class)
public class ElasticSearchTest {


    @Autowired
    private NewsService newsService;

    @Autowired
    private ElasticSearchService elasticSearchService;


    @Test
    public void insert(){

        List<News> newsList1 = newsService.queryHotNews();
        for (News news : newsList1) {
            elasticSearchService.saveNews(news);
        }

    }

    @Test
    public void delete(){

        Date date = new Date();
        System.out.println(date.getTime());
        elasticSearchService.deleteNews();
    }
}
