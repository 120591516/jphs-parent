package com.jinpaihushi.jphs.statistics.model;

import com.jinpaihushi.model.BaseModel;

@SuppressWarnings("serial")
public class StatisticsModel extends BaseModel {
    private String device;

    private String create_time;

    private String num;

    private String years;

    private String months;

    private String days;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

}
