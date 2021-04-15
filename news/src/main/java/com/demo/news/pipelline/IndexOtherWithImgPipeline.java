package com.demo.news.pipelline;
/**
 * 测试
 */

import com.demo.news.entity.News;
import com.demo.news.service.NewsService;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IndexOtherWithImgPipeline implements Pipeline {


    @Autowired
    public  NewsService newsService;



    public  static AtomicInteger atomicInteger = new AtomicInteger(0);
    int[] types = {21,22,23,24,25,31,32,33,34,35,41,42,43};


    @Override
    public synchronized void process(ResultItems resultItems, Task task) {

        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================pipeline===========================");


            news.setType(types[atomicInteger.get()]);
            System.out.println(news.toString());
            atomicInteger.getAndIncrement();
            if (atomicInteger.get()==13){
                atomicInteger.set(0);
            }

            newsService.insertNewsWithImg(news);

        }


    }

}
