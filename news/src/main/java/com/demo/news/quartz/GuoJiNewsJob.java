package com.demo.news.quartz;

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.GuoJiNewsPipeline;
import com.demo.news.spider.GuoJiNewsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class GuoJiNewsJob implements Job {


    @Autowired
    private GuoJiNewsProcessor guoJiNewsProcessor;

    @Autowired
    private GuoJiNewsPipeline guoJiNewsPipeline;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Spider.create(guoJiNewsProcessor)
                .addUrl("https://news.baidu.com/guoji")
                .thread(5)
                .setDownloader(new SeleniumDownloader("D:\\software\\chromedriver.exe"))
                .addPipeline(guoJiNewsPipeline)
                .runAsync();
    }

}
