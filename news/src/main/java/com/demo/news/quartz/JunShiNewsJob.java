package com.demo.news.quartz;

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.JunShiNewsPipeline;
import com.demo.news.spider.JunShiNewsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class JunShiNewsJob implements Job {

    @Autowired
    private JunShiNewsProcessor junShiNewsProcessor;

    @Autowired
    private JunShiNewsPipeline junShiNewsPipeline;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Spider.create(junShiNewsProcessor)
                .addUrl("https://news.baidu.com/mil")
                .addPipeline(junShiNewsPipeline)
                .setDownloader(new SeleniumDownloader("D:\\software\\chromedriver.exe"))
                .thread(5)
                .runAsync();

    }
}
