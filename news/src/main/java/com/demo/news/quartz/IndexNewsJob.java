package com.demo.news.quartz;
//主页新闻
import com.demo.news.pipelline.IndexNewsPipeline;
import com.demo.news.spider.IndexNewsProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Spider;

public class IndexNewsJob implements Job {


    @Autowired
    private IndexNewsPipeline indexNewsPipeline;

    @Autowired
    private IndexNewsProcessor indexNewsProcessor;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        /**
         * 爬虫启动
         * @param
         */
        //@Scheduled(initialDelay = 1000,fixedDelay = 100*1000)
        //@Scheduled(cron = "* 0/30 * * * *")
        //@Scheduled(cron = "0 0/2 * * * *")


            System.out.println("开始爬取热点新闻内容");
            Spider.create(indexNewsProcessor)
                    .addUrl("https://news.baidu.com/")
                    .thread(5)
                    .addPipeline(this.indexNewsPipeline)
                    .run();

        System.out.println("清除过期数据完成");


    }
}
