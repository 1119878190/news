package com.demo.news;
//日历测试

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalendarTest {





    @Test
    public void test1(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        System.out.println(simpleDateFormat.format(calendar.getTime()));

        //昨天
        calendar.setTime(now);
        //calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.HOUR,-2);
        //calendar.set(Calendar.MINUTE, 0);
        //calendar.set(Calendar.SECOND, 0);
        //calendar.add(Calendar.DATE,-1);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}
