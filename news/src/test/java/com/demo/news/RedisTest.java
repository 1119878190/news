package com.demo.news;


import com.demo.news.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    String redisKey = "weibo";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Test
    public void testObject(){

        for (int i = 0; i < 2; i++) {
            News news = new News();
            news.setTitle("nini");
            news.setHref("wwsjljfllsf");

            redisTemplate.opsForValue().set(redisKey+i,news,60, TimeUnit.SECONDS);
        }

    }

    @Test
    public void testget(){
        System.out.println(redisTemplate.opsForValue().get(redisKey));
    }

    @Test
    public void testdelete(){
        Boolean weibo = redisTemplate.delete("weibo0");
        Boolean weibo1 = redisTemplate.delete("weibo1");
    }

    @Test
    public void testSub(){
        String title = "#这是一个标题#";

        String substring = title.substring(1, title.length() - 1);
        System.out.println(substring);
    }


}
