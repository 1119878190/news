package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.service.GuoJiNewsService;
import com.demo.news.service.GuoNeiNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GuoJiWithImgPipeline implements Pipeline {


    @Autowired
    private GuoJiNewsService guoJiNewsService;

    public  static AtomicInteger atomicInteger = new AtomicInteger(0);
    int[] types = {21,22,3,3,3,3,3,3,41,42};

    @Override
    public synchronized void process(ResultItems resultItems, Task task) {
        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================国际pipeline===========================");

            news.setType(types[atomicInteger.get()]);
            System.out.println(news.toString());
            atomicInteger.getAndIncrement();
            if (atomicInteger.get()==10){
                atomicInteger.set(0);
            }

            guoJiNewsService.insertNews(news);

        }

    }

}
