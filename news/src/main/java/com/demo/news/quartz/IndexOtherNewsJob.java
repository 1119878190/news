package com.demo.news.quartz;

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.OtherNewsPipeline;
import com.demo.news.spider.IndexOtherNewsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class IndexOtherNewsJob implements Job {

    @Autowired
    private OtherNewsPipeline otherNewsPipeline;

    @Autowired
    private IndexOtherNewsProcessor indexOtherNewsProcessor;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //@Scheduled(cron = "0 0/30 * * * *")


            Spider.create(indexOtherNewsProcessor)
                    .addUrl("https://news.baidu.com/")
                    .thread(5)
                    .addPipeline(this.otherNewsPipeline)
                    .setDownloader(new SeleniumDownloader("D:\\software\\chromedriver.exe"))
                    .runAsync();

    }
}
