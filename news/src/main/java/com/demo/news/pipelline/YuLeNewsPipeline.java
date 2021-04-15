package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.entity.Songs;
import com.demo.news.service.YuLeNewsService;
import com.demo.news.utils.YuLeConstant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

@Component
public class YuLeNewsPipeline  implements Pipeline, YuLeConstant {

    @Autowired
    private YuLeNewsService yuLeNewsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //即时新闻
        List<String> jiShiNewsTitles = resultItems.get("jiShiNewsTitles");
        List<String> jiSHiNewsHrefs = resultItems.get("jiSHiNewsHrefs");
        saveTitleAndHref(jiShiNewsTitles,jiSHiNewsHrefs,JISHINEWS);

        //焦点新闻
        List<String> focalNewsTitles = resultItems.get("focalNewsTitles");
        List<String> focalNewsHrefs = resultItems.get("focalNewsHrefs");
        saveTitleAndHref(focalNewsTitles,focalNewsHrefs,FOCALNEWS);

        //明星
        List<String> startsNewsTitles = resultItems.get("startsNewsTitles");
        List<String> startsNewsHrefs = resultItems.get("startsNewsHrefs");
        saveTitleAndHref(startsNewsTitles,startsNewsHrefs,STARTSNEWS);

        //传文爆料
        List<String> rumoursTitles = resultItems.get("rumoursTitles");
        List<String> rumoursHrefs = resultItems.get("rumoursHrefs");
        saveTitleAndHref(rumoursTitles,rumoursHrefs,RUMOURSNEWS);

        //综艺视频
        List<String> varietyVideoHrefs = resultItems.get("varietyVideoHrefs");
        List<String> varietyVideoSrcs = resultItems.get("varietyVideoSrcs");
        List<String> varietyVideoTitles = resultItems.get("varietyVideoTitles");
        saveTitleAndHrefAndSrc(varietyVideoTitles,varietyVideoHrefs,varietyVideoSrcs,VARIETYVIDEO);

        //电影
        List<String> moviesTitles = resultItems.get("moviesTitles");
        List<String> moviesHrefs = resultItems.get("moviesHrefs");
        saveTitleAndHref(moviesTitles,moviesHrefs,MOVIES);

        //电影花絮
        List<String> moviesFeaturesTitles = resultItems.get("moviesFeaturesTitles");
        List<String> moviesFeaturesHrefs = resultItems.get("moviesFeaturesHrefs");
        saveTitleAndHref(moviesFeaturesTitles,moviesFeaturesHrefs,MOVIESFEATURES);

        //电视
        List<String> tvsTitles = resultItems.get("tvsTitles");
        List<String> tvsHrefs = resultItems.get("tvsHrefs");
        saveTitleAndHref(tvsTitles,tvsHrefs,TVS);

        //热门剧集
        List<String> hotDramaSeriesTitles = resultItems.get("hotDramaSeriesTitles");
        List<String> hotDramaSeriesHrefs = resultItems.get("hotDramaSeriesHrefs");
        saveTitleAndHref(hotDramaSeriesTitles,hotDramaSeriesHrefs,HOTDRAMASERIES);

        //热门剧评
        List<String> hotDramaCommentsTitles = resultItems.get("hotDramaCommentsTitles");
        List<String> hotDramaCommentsHrefs = resultItems.get("hotDramaCommentsHrefs");
        saveTitleAndHref(hotDramaCommentsTitles,hotDramaCommentsHrefs,HOTDRAMACOMMENTS);

        //音乐
        List<String> musicsTitles = resultItems.get("musicsTitles");
        List<String> musicsHrefs = resultItems.get("musicsHrefs");
        saveTitleAndHref(musicsTitles,musicsHrefs,MUSIC);

        //新歌
        List<String> newSongsName = resultItems.get("newSongsName");
        List<String> newSongsNameHref = resultItems.get("newSongsNameHref");
        List<String> newSongsSinger = resultItems.get("newSongsSinger");
        List<String> newsSongsSingerHref = resultItems.get("newsSongsSingerHref");
        saveSongs(newSongsName,newSongsNameHref,newSongsSinger,newsSongsSingerHref,NEWSONGS);

        //中文金曲榜单
        List<String> rankingSongsName = resultItems.get("rankingSongsName");
        List<String> rankingSongsNameHref = resultItems.get("rankingSongsNameHref");
        List<String> rankingSinger = resultItems.get("rankingSinger");
        List<String> rankingSingerHref = resultItems.get("rankingSingerHref");
        saveSongs(rankingSongsName,rankingSongsNameHref,rankingSinger,rankingSingerHref,RAKING);

        //综艺
        List<String> varietiesTitles = resultItems.get("varietiesTitles");
        List<String> varietiesHrefs = resultItems.get("varietiesHrefs");
        saveTitleAndHref(varietiesTitles,varietiesHrefs,VARIETIES);

        //热门点击
        List<String> hotClickTitles = resultItems.get("hotClickTitles");
        List<String> hotClickHrefs = resultItems.get("hotClickHrefs");
        saveTitleAndHref(hotClickTitles,hotClickHrefs,HOTCLICKS);

        //最新新闻
        List<String> latestNewsTitles = resultItems.get("latestNewsTitles");
        List<String> latestNewsHrefs = resultItems.get("latestNewsHrefs");
        List<String> latestNewsTimes = resultItems.get("latestNewsTimes");
        saveTitleAndHrefAndSrc(latestNewsTitles,latestNewsHrefs,latestNewsTimes,LATESTNEWS);
    }


    public void saveTitleAndHref(List<String> title,List<String> href,int type){
        if (title.size()  !=0 && href.size() !=0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setType(type);
                news.setSaveTime(new Date());
                //System.out.println("+++++++++++++++++++++"+news);
                yuLeNewsService.insertNews(news);
            }
        }

    }

    public void saveTitleAndHrefAndSrc(List<String> title, List<String> href, List<String> src, int type){
        if (title.size()  !=0 && href.size() !=0 && src.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setType(type);
                news.setSrc(src.get(i));
                news.setSaveTime(new Date());
                //System.out.println("+++++++++++++++++++++++++"+news);
                yuLeNewsService.insertNews(news);
            }
        }

    }

    public void saveSongs(List<String> name,List<String> nameHref,List<String> singer,List<String> singerHref,int type){
        if (name.size() !=0 && nameHref.size() != 0 && singer.size() !=0 && singerHref.size()!=0){
            for (int i = 0; i < name.size(); i++) {
                Songs song = new Songs();
                song.setName(name.get(i));
                song.setNameHref(nameHref.get(i));
                song.setSinger(singer.get(i));
                song.setSingerHref(singerHref.get(i));
                song.setSaveTime(new Date());
                song.setType(type);
                //System.out.println("++++++++++++++++++"+song);
                yuLeNewsService.insertSongs(song);
            }
        }

    }
}
