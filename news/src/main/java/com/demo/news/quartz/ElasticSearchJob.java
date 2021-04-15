package com.demo.news.quartz;


import com.demo.news.es.GuoJiNews;
import com.demo.news.es.GuoNeiNews;
import com.demo.news.es.IndexNews;
import com.demo.news.service.impl.ElasticSearchService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;



public class ElasticSearchJob implements Job {

    @Autowired
    private IndexNews esIndexNews;

    @Autowired
    private GuoNeiNews esGuoNeiNews;

    @Autowired
    private GuoJiNews esGuoJiNews;

    @Autowired
    private ElasticSearchService elasticSearchService;



    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("=======================删除es中的数据=============================");
        elasticSearchService.deleteNews();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("======================开始将主页数据插入es中==========================");
        new Thread(esIndexNews).start();

        System.out.println("======================开始将国内数据插入es中==========================");
        new Thread(esGuoNeiNews).start();


        System.out.println("======================开始将国际数据插入es中==========================");
        new Thread(esGuoJiNews).start();

        System.out.println("=========================数据插入es完毕===================");


    }


}
