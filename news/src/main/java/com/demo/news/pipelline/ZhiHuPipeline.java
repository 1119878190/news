package com.demo.news.pipelline;

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
public class ZhiHuPipeline implements Pipeline, Constant {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void process(ResultItems resultItems, Task task) {

         List<String> zhiHuTitles = resultItems.get("zhiHuTitles");
         List<String> zhiHuHrefs = resultItems.get("zhiHuHrefs");

        if (zhiHuTitles.size() != 0 && zhiHuHrefs.size() !=0){
            System.out.println("正在保存知乎热搜");
            for (int i = 0; i < zhiHuTitles.size(); i++) {
                News news = new News();
                news.setTitle(zhiHuTitles.get(i));
                news.setHref(zhiHuHrefs.get(i));
                news.setSaveTime(new Date());
                redisTemplate.opsForValue().set(zhiHuRedisKey+split+i,news);
            }
        }


    }

}
