package com.demo.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@EnableScheduling
public class NewsApplication {

    @PostConstruct
    public void init(){
        //解决netty启动冲突的问题
        //Netty4utils  setAvailableProcessors()
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(NewsApplication.class, args);
    }

}
