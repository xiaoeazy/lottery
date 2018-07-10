package com.huan.lottery.pojo;

public class LtPrizeitem {
    private Integer id;

    private String prizename;

    private Integer num;

    private Integer remainnum;

    private Long probability;

    private Integer aid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizename() {
        return prizename;
    }

    public void setPrizename(String prizename) {
        this.prizename = prizename == null ? null : prizename.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getRemainnum() {
        return remainnum;
    }

    public void setRemainnum(Integer remainnum) {
        this.remainnum = remainnum;
    }

    public Long getProbability() {
        return probability;
    }

    public void setProbability(Long probability) {
        this.probability = probability;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
}