package com.demo.news.quartz;
//轮播图任务

import com.demo.news.spider.RotationImgSelenium;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class RotationImgJob implements Job {

    @Autowired
    private RotationImgSelenium rotationImgSelenium;



    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        rotationImgSelenium.rotationImgSelenium();
    }
}
