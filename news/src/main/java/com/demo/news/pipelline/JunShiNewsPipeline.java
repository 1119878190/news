package com.demo.news.pipelline;

import com.demo.news.entity.News;
import com.demo.news.service.JunShiNewsService;
import com.demo.news.utils.JunShiConstant;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.swing.border.TitledBorder;
import java.util.Date;
import java.util.List;

@Component
public class JunShiNewsPipeline implements Pipeline, JunShiConstant {


    @Autowired
    private JunShiNewsService junShiNewsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //即时新闻
        List<String> jiShiNewsTitles = resultItems.get("jiShiNewsTitles");
        List<String> jiShiNewsHrefs =  resultItems.get("jiShiNewsHrefs");
        saveTitleAndHref(jiShiNewsTitles,jiShiNewsHrefs,JISHINEWS);

        //焦点新闻
        List<String> jiaoDianNewsTitles = resultItems.get("jiaoDianNewsTitles");
        List<String> jiaoDianNewsHrefs =  resultItems.get("jiaoDianNewsHrefs");
        saveTitleAndHref(jiaoDianNewsTitles,jiaoDianNewsHrefs,JIAODIANNEWS);

        //军事视频
        List<String> junSHiVideoTitles = resultItems.get("junSHiVideoTitles");
        List<String> junSHiVideoHrefs =  resultItems.get("junSHiVideoHrefs");
        List<String> junShiVideoSrcs = resultItems.get("junShiVideoSrcs");
        saveTitleAndHrefAndSrc(junSHiVideoTitles,junSHiVideoHrefs,junShiVideoSrcs,JUNSHINVIDEO);

        //中国军情
        List<String> chinaMilitaryTitles =  resultItems.get("chinaMilitaryTitles");
        List<String> chinaMilitaryHrefs = resultItems.get("chinaMilitaryHrefs");
        saveTitleAndHref(chinaMilitaryTitles,chinaMilitaryHrefs,ZHONGGUOJUNQING);

        //台湾聚焦
        List<String> taiWanFocusingTitles = resultItems.get("taiWanFocusingTitles");
        List<String> taiWanFocusingHrefs =  resultItems.get("taiWanFocusingHrefs");
        saveTitleAndHref(taiWanFocusingTitles,taiWanFocusingHrefs,TAIWANJUJIAO);

        //国际军情
        List<String> worldMilitaryTitles = resultItems.get("worldMilitaryTitles");
        List<String> worldMilitaryHrefs =  resultItems.get("worldMilitaryHrefs");
        saveTitleAndHref(worldMilitaryTitles,worldMilitaryHrefs,GUOJIJUNQING);

        //热门新闻
        List<String> hotNewsTitles = resultItems.get("hotNewsTitles");
        List<String> hotNewsHrefs =  resultItems.get("hotNewsHrefs");
        saveTitleAndHref(hotNewsTitles,hotNewsHrefs,HOTNEWS);

        //最新新闻
        List<String> latestNewsTitles = resultItems.get("latestNewsTitles");
        List<String> latestNewsHrefs =  resultItems.get("latestNewsHrefs");
        List<String> latestNewsTimes = resultItems.get("latestNewsTimes");
        saveTitleAndHrefAndSrc(latestNewsTitles,latestNewsHrefs,latestNewsTimes,LATESTNEWS);


        junShiNewsService.deleteNews();
    }

    public void saveTitleAndHref(List<String> title,List<String> href,int type){
        if (title.size()  !=0 && href.size() !=0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setType(type);
                news.setSaveTime(new Date());
                junShiNewsService.insertNews(news);
            }
        }

    }

    public void saveTitleAndHrefAndSrc(List<String> title,List<String> href,List<String> src,int type){
        if (title.size()  !=0 && href.size() !=0 && src.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setType(type);
                news.setSrc(src.get(i));
                news.setSaveTime(new Date());
                junShiNewsService.insertNews(news);
            }
        }

    }


}
