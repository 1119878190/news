package com.demo.news.quartz;

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.YuLeNewsPipeline;
import com.demo.news.spider.YuLeNewsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class YuLeNewsJob implements Job {

    @Autowired
    private YuLeNewsProcessor yuLeNewsProcessor;

    @Autowired
    private YuLeNewsPipeline yuLeNewsPipeline;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Spider.create(yuLeNewsProcessor)
                .addUrl("https://news.baidu.com/ent")
                .thread(5)
                .addPipeline(yuLeNewsPipeline)
                .setDownloader(new SeleniumDownloader("D:\\software\\chromedriver.exe"))
                .runAsync();
    }
}
