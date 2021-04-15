package com.demo.news;

import com.demo.news.entity.News;
import com.demo.news.service.NewsService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewsApplicationTests {

    @Autowired
    private NewsService newsService;


    @Test
    void contextLoads() {

        System.out.println(newsService);
        News news = new News();
        news.setTitle("这是一个测试2222");
        news.setHref("www.text.coM");
        newsService.insertNews(news);
    }

}
