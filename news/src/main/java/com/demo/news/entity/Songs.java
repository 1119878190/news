package com.demo.news.entity;

import java.util.Date;

public class Songs {

    private int id;
    private String name;
    private String nameHref;
    private String singer;
    private String singerHref;
    private Date saveTime;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameHref() {
        return nameHref;
    }

    public void setNameHref(String nameHref) {
        this.nameHref = nameHref;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSingerHref() {
        return singerHref;
    }

    public void setSingerHref(String singerHref) {
        this.singerHref = singerHref;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameHref='" + nameHref + '\'' +
                ", singer='" + singer + '\'' +
                ", singerHref='" + singerHref + '\'' +
                ", saveTime=" + saveTime +
                ", type=" + type +
                '}';
    }
}
