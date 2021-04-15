package com.demo.news.pipelline;
/**
 * 国内页面带有图片新闻
 */

import com.demo.news.entity.News;
import com.demo.news.service.GuoNeiNewsService;
import com.demo.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GuoNeiWithImgPipeline implements Pipeline {

    @Autowired
    private GuoNeiNewsService guoNeiNewsService;

    public  static AtomicInteger atomicInteger = new AtomicInteger(0);
    int[] types = {43,44,51,61,62};

    @Override
    public synchronized void process(ResultItems resultItems, Task task) {

        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================国内pipeline===========================");

            news.setType(types[atomicInteger.get()]);
            System.out.println(news.toString());
            atomicInteger.getAndIncrement();
            if (atomicInteger.get()==5){
                atomicInteger.set(0);
            }

            guoNeiNewsService.insertNews(news);

        }

    }
}
