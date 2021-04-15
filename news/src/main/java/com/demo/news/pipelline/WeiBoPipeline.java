package com.demo.news.pipelline;
/**
 * 微博热搜榜
 * @author liuxu
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
public class WeiBoPipeline implements Pipeline, Constant {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> weiBoTitles = resultItems.get("weiBoTitles");
        List<String> weiBoHrefs = resultItems.get("weiBoHrefs");


        if (weiBoHrefs.size() !=0 && weiBoTitles.size() != 0){
            System.out.println("正在保存微博热榜");
            for (int i = 0; i < weiBoHrefs.size(); i++) {
                News news = new News();
                news.setHref(weiBoHrefs.get(i));
                news.setTitle(weiBoTitles.get(i));
                news.setSaveTime(new Date());
                redisTemplate.opsForValue().set(redisKey+split+i,news);
            }
        }
    }

}
