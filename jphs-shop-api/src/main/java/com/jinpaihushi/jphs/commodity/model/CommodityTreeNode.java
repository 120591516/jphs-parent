package com.jinpaihushi.jphs.commodity.model;

import com.jinpaihushi.model.BaseModel;

public class CommodityTreeNode  extends BaseModel{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String parentId;

    private String text;

    private String state = "closed";

    private Boolean checked = false;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


}
