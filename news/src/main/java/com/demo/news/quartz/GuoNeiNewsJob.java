package com.demo.news.quartz;

import com.demo.news.config.SeleniumDownloader;
import com.demo.news.pipelline.GuoNeiNewsPipeline;
import com.demo.news.spider.GuoNeiNewsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class GuoNeiNewsJob implements Job {


    @Autowired
    private GuoNeiNewsPipeline guoNeiNewsPipeline;

    @Autowired
    private  GuoNeiNewsProcessor guoNeiNewsProcessor;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Spider.create(guoNeiNewsProcessor)
                .addUrl("https://news.baidu.com/guonei")
                .addPipeline(this.guoNeiNewsPipeline)
                .setDownloader(new SeleniumDownloader("D:\\software\\chromedriver.exe"))
                .thread(5)
                .runAsync();

    }
}
