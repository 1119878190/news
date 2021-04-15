package com.demo.news.quartz;

import com.demo.news.pipelline.BaiDuPipeline;
import com.demo.news.spider.BaiDuHotWordsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class BaiDuHotWordsJob implements Job {

    @Autowired
    private BaiDuPipeline baiDuPipeline;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       // @Scheduled(cron = "0 0/2 * * * ?")


        Spider.create(new BaiDuHotWordsProcessor())
                .addUrl("http://top.baidu.com/buzz?b=1&c=513&fr=topcategory_c513")
                .addPipeline(this.baiDuPipeline)
                .thread(5)
                .runAsync();

    }
}
