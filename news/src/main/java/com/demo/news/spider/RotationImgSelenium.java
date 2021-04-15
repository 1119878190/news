package com.demo.news.spider;
/**
 * 主页,国内,国际,军事,娱乐轮播图
 */

import com.demo.news.entity.RotationImg;
import com.demo.news.service.RotationImgService;
import com.demo.news.utils.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class RotationImgSelenium implements Constant {

    @Autowired
    private RotationImgService rotationImgService;

    public void rotationImgSelenium() {


        System.setProperty("webdriver.chrome.driver","D:\\software\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://news.baidu.com/");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //首页
        System.out.println("------开始获取首页轮播图-----");
        spiderRotationImg(IndexRotationImg,driver);

        //国内
        driver.findElement(By.linkText("国内")).click();
        System.out.println("-----开始获取国内轮播图-----");
        spiderRotationImg(GuoNeiRotationImg,driver);

        //国际
        driver.findElement(By.linkText("国际")).click();
        System.out.println("------开始获取国际轮播图-----");
        spiderRotationImg(GuoJiRotationImg,driver);

        //军事
        driver.findElement(By.linkText("军事")).click();
        System.out.println("------开始获取军事轮播图------");
        spiderRotationImg(JunShiRotationImg,driver);


        //娱乐
        driver.findElement(By.linkText("娱乐")).click();
        System.out.println("------开始获取娱乐轮播图-------");
        spiderRotationImg(YuLeRotationImg,driver);
        System.out.println("--------所有轮播图获取完毕-------------");


        driver.quit();

        //清除两小时前的数据
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR,-2);
        Date twoHourAgo = calendar.getTime();

        rotationImgService.deleteRotationImg(twoHourAgo);

    }

    public  void spiderRotationImg(int type, WebDriver driver){

        RotationImg rotationImgOne = new RotationImg();
        //获取第一张图片  #imgView > a > img
        rotationImgOne.setSrc(driver.findElement(By.cssSelector("#imgView > a > img")).getAttribute("src"));
        rotationImgOne.setHref(driver.findElement(By.cssSelector("#imgTitle > a")).getAttribute("href"));
        rotationImgOne.setShowInfo(driver.findElement(By.cssSelector("#imgTitle > a > strong")).getText());
        rotationImgOne.setType(type);
        rotationImgOne.setSaveTime(new Date());
        rotationImgService.insertRotationImg(rotationImgOne);
        //System.out.println(rotationImgOne);

        //获取一共多少张图片
        int count = Integer.parseInt(driver.findElement(By.cssSelector("#imgNav > a:nth-child(1)")).getAttribute("index"));

        //获取剩余图片
        for (int i = 0; i < count-1; i++) {
            driver.findElement(By.cssSelector("#imgplayer-next")).click();
            RotationImg rotationImg = new RotationImg();
            //获取图片src
            rotationImg.setSrc(driver.findElement(By.cssSelector("#imgView > a > img")).getAttribute("src"));
            rotationImg.setHref(driver.findElement(By.cssSelector("#imgTitle > a")).getAttribute("href"));
            rotationImg.setShowInfo(driver.findElement(By.cssSelector("#imgTitle > a > strong")).getText());
            rotationImg.setSaveTime(new Date());
            rotationImg.setType(type);
            rotationImgService.insertRotationImg(rotationImg);
            //System.out.println(rotationImg);

        }

    }

}
