package com.demo.news.pipelline;
/**
 * 百度热搜榜
 */

import com.demo.news.entity.News;
import com.demo.news.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

@Component
public class BaiDuPipeline implements Pipeline, Constant {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void process(ResultItems resultItems, Task task) {


        List<String> baiDuTitles = resultItems.get("baiDuTitles");
        List<String> baiDuHrefs = resultItems.get("baiDuHrefs");

        if (baiDuTitles.size() != 0 && baiDuHrefs.size() != 0){
            System.out.println("开始保存百度热搜榜");
            for (int i = 0; i < baiDuTitles.size(); i++) {
                News news = new News();
                news.setTitle(baiDuTitles.get(i));
                news.setHref(baiDuHrefs.get(i));
                news.setSaveTime(new Date());
                redisTemplate.opsForValue().set(baiDuRedisKey+split+i,news);
            }
        }
    }
}
