package com.demo.news.pipelline;
/**
 * 国内新闻
 */

import com.demo.news.entity.News;
import com.demo.news.service.GuoNeiNewsService;
import com.demo.news.service.impl.ElasticSearchService;
import com.demo.news.utils.GuoNeiConstant;
import com.google.j2objc.annotations.AutoreleasePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

@Component
public class GuoNeiNewsPipeline implements Pipeline , GuoNeiConstant {


    @Autowired
    private GuoNeiNewsService guoNeiNewsService;

    @Autowired
    private ElasticSearchService elasticSearchService;


    @Override
    public void process(ResultItems resultItems, Task task) {

        //即时新闻
        List<String>jiShiNewsHrefs = resultItems.get("jiShiNewsHrefs");
        List<String> jiShiNewsTitles = resultItems.get("jiShiNewsTitles");
        saveTitleAndHref(jiShiNewsTitles,jiShiNewsHrefs,JISHINEWS);


        //焦点新闻
        List<String> jiaoDianNewsHrefs = resultItems.get("jiaoDianNewsHrefs");
        List<String> jiaoDianNewsTitles = resultItems.get("jiaoDianNewsTitles");
        saveTitleAndHref(jiaoDianNewsTitles,jiaoDianNewsHrefs,JIAODIANNEWS);

        //视频新闻
        List<String> shiPingNewsHrefs = resultItems.get("shiPingNewsHrefs");
        List<String> shiPingNewsTitles = resultItems.get("shiPingNewsTitles");
        List<String> shiPingNewsSrcs = resultItems.get("shiPingNewsSrcs");
        saveListWithImg(shiPingNewsTitles,shiPingNewsHrefs,shiPingNewsSrcs,SHIPINGNEWS);

    //港澳台
        //台湾
        List<String> taiWanHrefs = resultItems.get("taiWanHrefs");
        List<String> taiWanTitles = resultItems.get("taiWanTitles");
        saveTitleAndHref(taiWanTitles,taiWanHrefs,TAIWANNEWS);

        //港澳
        List<String> gangaotaiHrefs = resultItems.get("gangaotaiHrefs");
        List<String> gangaotaiTitles = resultItems.get("gangaotaiTitles");
        saveTitleAndHref(gangaotaiTitles,gangaotaiHrefs,GANGAONEWS);


        //台湾民生
        List<String> mingshengHrefs = resultItems.get("mingshengHrefs");
        List<String> mingshengTitles = resultItems.get("mingshengTitles");
        saveTitleAndHref(mingshengTitles,mingshengHrefs,MINGSHENGNEWS);


    //时政要闻
        List<String> shizhengHrefs = resultItems.get("shizhengHrefs");
        List<String> shizhengTitles = resultItems.get("shizhengTitles");
        saveTitleAndHref(shizhengTitles,shizhengHrefs,SHIZHENGNEWS);



    //历史档案
        List<String> historyHrefs = resultItems.get("historyHrefs");
        List<String> historyTitles = resultItems.get("historyTitles");
        saveTitleAndHref(historyTitles,historyHrefs,HISTORYNEWS);

    //最新新闻
        //左
        List<String>  latestLeftHrefs = resultItems.get("latestLeftHrefs");
        List<String>  latestLeftTitles = resultItems.get("latestLeftTitles");
        List<String>  latestLeftTime = resultItems.get("latestLeftTime");
        saveListWithTime(latestLeftTitles,latestLeftHrefs,latestLeftTime,LATESTNEWSLEFT);


        //右
        List<String> latestRightHrefs = resultItems.get("latestRightHrefs");
        List<String> latestRightTitles = resultItems.get("latestRightTitles");
        List<String> latestRightTime = resultItems.get("latestRightTime");
        saveListWithTime(latestRightTitles,latestRightHrefs,latestRightTime,LATESTNEWSRIGHT);


        //删除过期数据
        guoNeiNewsService.deleteNews();

    }


    public void saveTitleAndHref(List<String> title,List<String> href,int type){

        if (title.size() != 0 && href.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News();
                news.setTitle(title.get(i));
                news.setHref(href.get(i));
                news.setSaveTime(new Date());
                news.setType(type);
                guoNeiNewsService.insertNews(news);
            }
        }

    }

    public void saveListWithImg(List<String> title,List<String> href,List<String> src,int type){
        if (title.size() != 0 && href.size() != 0){
            for (int i = 0; i < title.size(); i++) {
                News news = new News(
                        title.get(i),
                        href.get(i),
                        new Date(),
                        type,
                        src.get(i)
                );
                guoNeiNewsService.insertNews(news);

            }

        }

    }

    public void saveListWithTime(List<String> title,List<String> href,List<String> time,int type){
        if (title.size() != 0 && href.size() != 00) {
            for (int i = 0; i < title.size(); i++) {
                News news = new News(
                        title.get(i),
                        href.get(i),
                        new Date(),
                        type,
                        time.get(i));

                guoNeiNewsService.insertNews(news);

            }
        }
    }

    public void saveImg(News news){

            guoNeiNewsService.insertNews(news);

    }


}
