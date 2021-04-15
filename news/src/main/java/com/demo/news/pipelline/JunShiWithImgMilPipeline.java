package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.service.JunShiNewsService;
import com.demo.news.utils.JunShiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 军事新闻 底部 军事图片
 */
@Component
public class JunShiWithImgMilPipeline implements Pipeline, JunShiConstant {

    @Autowired
    private JunShiNewsService junShiNewsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        News news = resultItems.get("news");

        if (news!=null){

            System.out.println("===================军事图片pipeline===========================");

            news.setType(JUNSHIPICTURES);
            System.out.println("这是军事"+news.toString());

            junShiNewsService.insertNews(news);

        }
    }
}
