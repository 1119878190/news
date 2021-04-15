package com.demo.news.entity;

import java.util.Date;

//轮播图
public class RotationImg {

    private Integer id;
    private String href;
    private String src;
    private Integer type;
    private String showInfo;
    private Date saveTime;


    public RotationImg() {
    }

    public RotationImg(Integer id, String href, String src, Integer type, String showInfo, Date saveTime) {
        this.id = id;
        this.href = href;
        this.src = src;
        this.type = type;
        this.showInfo = showInfo;
        this.saveTime = saveTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(String showInfo) {
        this.showInfo = showInfo;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return "RotationImg{" +
                "id=" + id +
                ", href='" + href + '\'' +
                ", src='" + src + '\'' +
                ", type=" + type +
                ", showInfo='" + showInfo + '\'' +
                ", saveTime=" + saveTime +
                '}';
    }
}
