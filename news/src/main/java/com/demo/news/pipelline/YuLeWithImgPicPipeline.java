package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.service.YuLeNewsService;
import com.demo.news.utils.YuLeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class YuLeWithImgPicPipeline implements Pipeline, YuLeConstant {


    @Autowired
    private YuLeNewsService yuLeNewsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================娱乐底部pipeline===========================");
            news.setType(PICTURENEWS);
            System.out.println(news.toString());


            yuLeNewsService.insertNews(news);

        }
    }
}
