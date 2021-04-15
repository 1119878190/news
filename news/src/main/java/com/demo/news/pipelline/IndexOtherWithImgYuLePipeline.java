package com.demo.news.pipelline;
/**
 * 主页娱乐图片
 */

import com.demo.news.entity.News;
import com.demo.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IndexOtherWithImgYuLePipeline implements Pipeline {

    @Autowired
    private NewsService newsService;

    int[] types = {51,52,53,54,55};

    public  static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public synchronized void process(ResultItems resultItems, Task task) {
        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================娱乐pipeline===========================");


            news.setType(types[atomicInteger.get()]);
            System.out.println(news.toString());
            atomicInteger.getAndIncrement();
            if (atomicInteger.get()==5){
                atomicInteger.set(0);
            }

            newsService.insertNewsWithImg(news);

        }
    }
}
