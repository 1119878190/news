package com.demo.news.quartz;

import com.demo.news.pipelline.ZhiHuPipeline;
import com.demo.news.spider.ZhiHuHotWordsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class ZhiHuHotWordsJob implements Job {

    @Autowired
    private ZhiHuPipeline zhiHuPipeline;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //@Scheduled(cron = "0 0/2 * * * ?")

        Spider.create(new ZhiHuHotWordsProcessor())
                .addUrl("https://tophub.today/n/mproPpoq6O")
                .addPipeline(this.zhiHuPipeline)
                .thread(5)
                .runAsync();

    }
}
