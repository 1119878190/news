package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.service.YuLeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class YuLeWithImgPipeline implements Pipeline {


    @Autowired
    private YuLeNewsService yuLeNewsService;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    int[] types = {3,3,41,41,41,71,71,91,91,121,121,151,151};

    @Override
    public void process(ResultItems resultItems, Task task) {
        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================娱乐pipeline===========================");

            news.setType(types[atomicInteger.get()]);

            System.out.println(news.toString());
            atomicInteger.getAndIncrement();
            if (atomicInteger.get()==13){
                atomicInteger.set(0);
            }
            yuLeNewsService.insertNews(news);

        }
    }
}
