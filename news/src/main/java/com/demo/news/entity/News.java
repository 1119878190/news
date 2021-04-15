package com.demo.news.entity;
//新闻实体类


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "news",type = "_doc",shards = 6,replicas = 3)
public class News implements Serializable {

    @Id
    private Integer id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart" )
    private String title;

    @Field(type = FieldType.Text)
    private String href;

    @Field(type = FieldType.Date)
    private Date saveTime;

    @Field(type = FieldType.Integer)
    private int type;

    @Field(type = FieldType.Text)
    private String src;

    public News() {
    }

    public News(Integer id, String title, String href, Date saveTime, int type, String src) {
        this.id = id;
        this.title = title;
        this.href = href;
        this.saveTime = saveTime;
        this.type = type;
        this.src = src;
    }

    public News(String title, String href, Date saveTime, int type, String src) {
        this.title = title;
        this.href = href;
        this.saveTime = saveTime;
        this.type = type;
        this.src = src;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", saveTime=" + saveTime +
                ", type=" + type +
                ", src='" + src + '\'' +
                '}';
    }

}
