package com.demo.news.utils;
//常量接口
public interface Constant {

     int HomePage = 1;//主页热点

//
     int HomePageGuoNeiNews = 2;//主页国内

     int GuoNeiImgTop = 21;//国内 中上

     int GuoNeiImgBottomLeft = 22; //国内 中 下 左

     int GuoNeiImgBottomRight = 23; //国内 中 下 右

     int GuoNeiImgListLeft = 24; //国内 右 左

     int GuoNeiIMgListRight = 25;//国内  右 右

     int GuoNeiHotCheck = 26;//国内  热门点击

//
     int HomePageGuoJiNews = 3;//主页国际

     int GuoJiImgTop = 31;//国际 中上

     int GuoJiImgBottomLeft = 32; //国际 中 下 左

     int GuoJiImgBottomRight = 33; //国际 中 下 右

     int GuoJiImgListLeft = 34; //国际 右 左

     int GuoJiIMgListRight = 35;//国际  右 右

     int GuoJiHotCheck = 36;//国际  热门点击

//
     int HomePageJunShiNews = 4;//主页军事

     int JunShiImgTop = 41;//军事 中上

     int JunShiImgBottomLeft = 42; //军事 中 下 左

     int JunShiImgBottomRight = 43; //军事 中 下 右

     int JunShiHotCheck = 44;//军事  热门点击

     int JunShiChinaWar = 45;//国内军情

//
     
     int HomePageYuLeNews = 5;//主页娱乐

     int YuLeImgTop = 51;//娱乐 中上

     int YuLeImgBottomLeft = 52; //娱乐 中 下 左

     int YuLeImgBottomRight = 53; //娱乐 中 下 右

     int YuLeImgListLeft = 54; //娱乐 右 左

     int YuLeIMgListRight = 55;//娱乐  右 右

     int YuLeHotWords = 56;//娱乐热搜关键词

     int YuLeBottomImgs = 57;//娱乐 最底部图片

//

     String redisKey = "weiBo";

     String zhiHuRedisKey = "zhiHu";

     String baiDuRedisKey = "baiDu";

     String split = ":";


     //轮播图
     int IndexRotationImg = 1;//主页轮播图

     int GuoNeiRotationImg = 2;//国内轮播图

     int GuoJiRotationImg = 3;//国际轮播图

     int JunShiRotationImg = 4;//军事轮播图

     int YuLeRotationImg = 5;//娱乐轮播图


     

}
