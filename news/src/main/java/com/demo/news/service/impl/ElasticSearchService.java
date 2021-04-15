package com.demo.news.service.impl;

import com.demo.news.entity.News;
import com.demo.news.mapper.NewsRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ElasticSearchService {


    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ElasticsearchTemplate elasticTemplate;

    /**
     * 添加
     * @param post
     */
    public void saveNews(News post){
        newsRepository.save(post);
    }

    /**
     * 删除
     *
     */
    public void deleteNews(){
        newsRepository.deleteAll();

    }

    /**
     * 查询
     * @param keyword 关键词
     * @param current  当前页
     * @param limit  几条数据
     * @return
     */
    public Page<News> searchDiscussPost(String keyword, int current, int limit){
        //构造查询条件
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyword,"title"))
                .withSort(SortBuilders.fieldSort("saveTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(current,limit))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>")
                ).build();

        //返回的数据
        return elasticTemplate.queryForPage(searchQuery, News.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                //获取命中数据
                SearchHits hits = response.getHits();
                if (hits.getTotalHits() <=0){
                    return null;
                }
                List<News> list = new ArrayList<>();
                for (SearchHit hit : hits){
                    //从中取值为String类型,然后转换类型
                    News post = new News();

                    String title = hit.getSourceAsMap().get("title").toString();
                    post.setTitle(title);

                    String href = hit.getSourceAsMap().get("href").toString();
                    post.setHref(href);

                    String saveTime = hit.getSourceAsMap().get("saveTime").toString();
                    post.setSaveTime(new Date(Long.valueOf(saveTime)));

                    String type = hit.getSourceAsMap().get("type").toString();
                    post.setType(Integer.valueOf(type));

                    //处理高亮显示结果  将原先获取的title和content覆盖掉
                    HighlightField titleField  = hit.getHighlightFields().get("title");
                    if (titleField != null){
                        post.setTitle(titleField.getFragments()[0].toString());
                    }

                    list.add(post);
                }
                return new AggregatedPageImpl(list,pageable,hits.getTotalHits(),
                        response.getAggregations(),response.getScrollId(),hits.getMaxScore());
            }
        });
    }
}
