package com.demo.news.controller;


import com.demo.news.entity.News;
import com.demo.news.entity.Page;
import com.demo.news.service.impl.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class SearchController {


    @Autowired
    ElasticSearchService elasticSearchService;

    /**
     * 关键词搜索  es
     * search?keyword=xxxx
     * @param keyword 关键词
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(String keyword, Page page, Model model){
        String msg = null;

        //搜索贴子
        org.springframework.data.domain.Page<News> searchResult =
                elasticSearchService.searchDiscussPost(keyword,page.getCurrent()-1,page.getLimit());


        //聚合数据
        List<News> discussPosts = new ArrayList<>();
        List<String> newsList = new ArrayList<>();
        List<News> news = new ArrayList<>();
        if (searchResult != null){
            for(News post : searchResult){
                discussPosts.add(post);
            }
        }
        //去重
        for (int i = 0; i < discussPosts.size(); i++) {
            if (!newsList.contains(discussPosts.get(i).getTitle())){
                newsList.add(discussPosts.get(i).getTitle());
                news.add(discussPosts.get(i));
            }
        }

        model.addAttribute("discussPosts",news);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);

        if (news.size()==0){

            msg = "对不起,改关键词没有相关新闻,请输入其它关键词";
        }

       // 分页信息
        page.setPath("/search?keyword="+keyword);
        page.setRows(searchResult == null ? 0 : (int)searchResult.getTotalElements());

        model.addAttribute("msg",msg);

        return "search";
    }
}
