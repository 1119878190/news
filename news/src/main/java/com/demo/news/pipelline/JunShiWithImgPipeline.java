package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.service.GuoJiNewsService;
import com.demo.news.service.JunShiNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.atomic.AtomicInteger;
@Component
public class JunShiWithImgPipeline implements Pipeline {

    @Autowired
    private JunShiNewsService junShiNewsService;

    public  static AtomicInteger atomicInteger = new AtomicInteger(0);
    int[] types = {21,22,41,42,51,61,62};

    @Override
    public void process(ResultItems resultItems, Task task) {
        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================军事pipeline===========================");

            news.setType(types[atomicInteger.get()]);
            System.out.println(news.toString());
            atomicInteger.getAndIncrement();
            if (atomicInteger.get()==7){
                atomicInteger.set(0);
            }
            junShiNewsService.insertNews(news);

        }
    }
}
