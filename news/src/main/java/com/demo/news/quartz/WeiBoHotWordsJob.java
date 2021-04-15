package com.demo.news.quartz;

import com.demo.news.pipelline.WeiBoPipeline;
import com.demo.news.spider.WeiBoHotWordsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class WeiBoHotWordsJob implements Job {



    @Autowired
    private WeiBoPipeline weiBoPipeline;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //@Scheduled(cron = "0 0/2 * * * ?")

            Spider.create(new WeiBoHotWordsProcessor())
                    .addUrl("https://s.weibo.com/top/summary?cate=socialevent")
                    .addPipeline(weiBoPipeline)
                    .thread(5)
                    .start();

    }

}
