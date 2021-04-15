package com.demo.news;


import com.demo.news.entity.News;
import com.demo.news.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.Oneway;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private NewsService newsService;

    @Test
    public void testInsert(){
        News news = new News();
        news.setTitle("这是一个测试");
        news.setHref("www.text.coM");
        news.setSaveTime(new Date());
        newsService.insertNews(news);


    }


    @Test
    public void test2(){
        boolean b = false;
        try {
            b = scheduler.deleteJob(new JobKey("newsJob", "newsJobGroup"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println(b);
    }
}
