package com.demo.news.spider;
/**
 * 测试
 */

import com.demo.news.entity.RotationImg;
import com.demo.news.utils.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;

public class TestSelenium implements Constant {


   /* @Autowired
    private RotationImgService rotationImgService;*/

    public static void main(String[] args) {
        //1.设置驱动
        System.setProperty("webdriver.chrome.driver","D:\\software\\chromedriver.exe");
        //2.创建webdriver是咧
        WebDriver driver = new ChromeDriver();
        //3.通过get方法,打开指定的url地址
        driver.get("https://news.baidu.com/");

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
        System.out.println("开始获取军事轮播图");
        spiderRotationImg(JunShiRotationImg,driver);


        //娱乐
        driver.findElement(By.linkText("娱乐")).click();
        System.out.println("开始获取娱乐轮播图");
        spiderRotationImg(YuLeRotationImg,driver);


        System.out.println("--------所有轮播图获取完毕-------------");


        driver.quit();

    }

    public static void spiderRotationImg(int type, WebDriver driver){

        RotationImg rotationImgOne = new RotationImg();
        //获取第一张图片
        rotationImgOne.setSrc(driver.findElement(By.cssSelector("#imgView > a > img")).getAttribute("src"));//src
        rotationImgOne.setHref(driver.findElement(By.cssSelector("#imgTitle > a")).getAttribute("href"));//href
        rotationImgOne.setShowInfo(driver.findElement(By.cssSelector("#imgTitle > a > strong")).getText());
        rotationImgOne.setType(type);
        rotationImgOne.setSaveTime(new Date());
        System.out.println(rotationImgOne);

        //获取一共多少张图片
        int count = Integer.parseInt(driver.findElement(By.cssSelector("#imgNav > a:nth-child(1)")).getAttribute("index"));

        //获取剩余图片
        for (int i = 0; i < count-1; i++) {
            driver.findElement(By.cssSelector("#imgplayer-next")).click();
            RotationImg rotationImg = new RotationImg();
            //获取图片src
            rotationImg.setSrc(driver.findElement(By.cssSelector("#imgView > a > img")).getAttribute("src"));//src
            rotationImg.setHref(driver.findElement(By.cssSelector("#imgTitle > a")).getAttribute("href"));//href
            rotationImg.setShowInfo(driver.findElement(By.cssSelector("#imgTitle > a > strong")).getText());//title
            rotationImg.setSaveTime(new Date());
            rotationImg.setType(type);
            System.out.println(rotationImg);

        }
    }
}
