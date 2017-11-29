package com.jinpaihushi.model;

import java.util.Date;

public class ResultDTO {
	  
    private String statueMessage;  
    private String sex;  
    private String birthday;  
    private int age;  
    private Date birthdayTime;
     
    public String getStatueMessage() {  
        return statueMessage;  
    }  
    public void setStatueMessage(String statueMessage) {  
        this.statueMessage = statueMessage;  
    }  
    public String getSex() {  
        return sex;  
    }  
    public void setSex(String sex) {  
        this.sex = sex;  
    }  
    public String getBirthday() {  
        return birthday;  
    }  
    public void setBirthday(String birthday) {  
        this.birthday = birthday;  
    }  
    public int getAge() {  
        return age;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
    public Date getBirthdayTime() {
		return birthdayTime;
	}
	public void setBirthdayTime(Date birthdayTime) {
		this.birthdayTime = birthdayTime;
	}
	public String toString() {  
        String res = "";  
        if(this.statueMessage != null) {  
            res += this.statueMessage;  
        }else {  
            res += "sex:" + this.sex + ",birthday:" + this.birthday + ",age:" + this.age;  
        }  
        return res;  
    }  
}
