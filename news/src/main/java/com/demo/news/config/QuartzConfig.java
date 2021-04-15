package com.demo.news.config;
/**
 * quartz定时框架配置
 */

import com.demo.news.quartz.*;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;


@Configuration
public class QuartzConfig {


    /**
     * 主页
     * @return
     */
    //配置JobDetail
    @Bean
    public JobDetailFactoryBean newsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(IndexNewsJob.class);
        factoryBean.setName("indexNewsJob");
        factoryBean.setGroup("indexNewsJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

    //配置Trigger(SimpleTriggerFactoryBean,CornTriggerFactoryBean)
     @Bean
    public CronTriggerFactoryBean newsTrigger(JobDetail newsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(newsDetail);
        factoryBean.setName("newsTrigger");
        factoryBean.setGroup("newsTriggerGroup");
        factoryBean.setCronExpression("0 0/30 * * * ?");
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }


    /**
     * 轮播图
     * @return
     */
    @Bean
    public JobDetailFactoryBean rotationImgDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(RotationImgJob.class);
        factoryBean.setName("rotationImgJob");
        factoryBean.setGroup("rotationImgJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean testTrigger(JobDetail rotationImgDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(rotationImgDetail);
        factoryBean.setName("rotationImgTrigger");
        factoryBean.setGroup("rotationImgTriggerGroup");
        factoryBean.setCronExpression("0 0/30 * * * ?");//多久执行一次任务
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 主页其它新闻
     * @return
     */
    @Bean
        public JobDetailFactoryBean indexOtherNewsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(IndexOtherNewsJob.class);
        factoryBean.setName("indexOtherNewsJob");
        factoryBean.setGroup("indexOtherNewsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean indexOtherNewsTrigger(JobDetail indexOtherNewsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(indexOtherNewsDetail);
        factoryBean.setName("indexOtherNewsTrigger");
        factoryBean.setGroup("indexOtherNewsTriggerGroup");
        factoryBean.setCronExpression("0 0/30 * * * ?");//多久执行一次任务
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 微博热搜榜
     * @return
     */
    @Bean
    public JobDetailFactoryBean weiBoHotWordsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(WeiBoHotWordsJob.class);
        factoryBean.setName("weiBoHotWordsJob");
        factoryBean.setGroup("weiBoHotWordsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean weiBoHotWordsTrigger(JobDetail weiBoHotWordsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(weiBoHotWordsDetail);
        factoryBean.setName("weiBoHotWordsTrigger");
        factoryBean.setGroup("weiBoHotWordsTriggerGroup");
        factoryBean.setCronExpression("0 0/10 * * * ?");//多久执行一次任务 2分钟一次
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 知乎热搜榜
     */
    @Bean
    public JobDetailFactoryBean zhiHuHotWordsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(ZhiHuHotWordsJob.class);
        factoryBean.setName("zhiHuHotWordsJob");
        factoryBean.setGroup("zhiHuHotWordsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean zhiHuHotWordsTrigger(JobDetail zhiHuHotWordsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(zhiHuHotWordsDetail);
        factoryBean.setName("zhiHuHotWordsTrigger");
        factoryBean.setGroup("zhiHuHotWordsTriggerGroup");
        factoryBean.setCronExpression("0 0/10 * * * ?");//多久执行一次任务 2分钟一次
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 百度热搜榜
     */
    @Bean
    public JobDetailFactoryBean baiDuHotWordsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(BaiDuHotWordsJob.class);
        factoryBean.setName("baiDuHotWordsJob");
        factoryBean.setGroup("baiDuHotWordsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean baiDuHotWordsTrigger(JobDetail baiDuHotWordsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(baiDuHotWordsDetail);
        factoryBean.setName("baiDuHotWordsTrigger");
        factoryBean.setGroup("baiDuHotWordsTriggerGroup");
        factoryBean.setCronExpression("0 0/10 * * * ?");//多久执行一次任务 2分钟一次
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 国内新闻
     */
    @Bean
    public JobDetailFactoryBean guoNeiNewsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(GuoNeiNewsJob.class);
        factoryBean.setName("guoNeiNewsJob");
        factoryBean.setGroup("guoNeiNewsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean guoNeiNewsTrigger(JobDetail guoNeiNewsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(guoNeiNewsDetail);
        factoryBean.setName("guoNeiNewsTrigger");
        factoryBean.setGroup("guoNeiNewsTriggerGroup");
        factoryBean.setCronExpression("0 0/40 * * * ?");//多久执行一次任务 2分钟一次
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 国际
     */
    @Bean
    public JobDetailFactoryBean guoJiNewsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(GuoJiNewsJob.class);
        factoryBean.setName("guoJiNewsJob");
        factoryBean.setGroup("guoJiNewsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean guoJiNewsTrigger(JobDetail guoJiNewsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(guoJiNewsDetail);
        factoryBean.setName("guoJiNewsTrigger");
        factoryBean.setGroup("guoJiNewsTriggerGroup");
        factoryBean.setCronExpression("0 0/40 * * * ?");//多久执行一次任务
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 军事
     */
    @Bean
    public JobDetailFactoryBean junShiNewsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(JunShiNewsJob.class);
        factoryBean.setName("junShiNewsJob");
        factoryBean.setGroup("junShiNewsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean junSHiNewsTrigger(JobDetail junShiNewsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(junShiNewsDetail);
        factoryBean.setName("junShiNewsTrigger");
        factoryBean.setGroup("junShiNewsTriggerGroup");
        factoryBean.setCronExpression("0 0/40 * * * ?");//多久执行一次任务
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * 娱乐
     */
    @Bean
    public JobDetailFactoryBean yuLeNewsDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(YuLeNewsJob.class);
        factoryBean.setName("yuLeNewsJob");
        factoryBean.setGroup("yuLeNewsJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean yuLeNewsTrigger(JobDetail yuLeNewsDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(yuLeNewsDetail);
        factoryBean.setName("yuLeNewsTrigger");
        factoryBean.setGroup("yuLeNewsTriggerGroup");
        factoryBean.setCronExpression("0 0/5 * * * ?");//多久执行一次任务
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }

    /**
     * ElasticSearch
     */
    @Bean
    public JobDetailFactoryBean elasticsearchDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(ElasticSearchJob.class);
        factoryBean.setName("elasticsearchJob");
        factoryBean.setGroup("elasticsearchJobGroup");
        factoryBean.setDurability(true);//持久保存
        factoryBean.setRequestsRecovery(true);//该任务是不是可恢复
        return factoryBean;
    }


    @Bean
    public CronTriggerFactoryBean elasticsearchTrigger(JobDetail elasticsearchDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(elasticsearchDetail);
        factoryBean.setName("elasticsearchTrigger");
        factoryBean.setGroup("elasticsearchTriggerGroup");
        factoryBean.setCronExpression("0 0/5 * * * ?");//多久执行一次任务
        factoryBean.setJobDataMap(new JobDataMap());//Job状态
        return factoryBean;
    }
}