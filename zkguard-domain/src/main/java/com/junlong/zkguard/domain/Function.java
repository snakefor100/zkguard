package com.junlong.zkguard.domain;

/**
 * Created by niuniu on 2016/3/29.
 */
public class Function{
    /**
     * 功能ID
     */
    private String functionId;
    /**
     * 功能名称
     */
    private String functionName;
    /**
     * 描述
     */
    private String descript;
    /**
     * 响应url
     */
    private String actionUrl;
    /**
     * 排序字段 越小越重要
     */
    private int sort;
    /**
     * 父功能节点id
     */
    private String parentId;
    /**
     * 功能状态
     */
    private int state;
    /**
     * 功能类型
     */
    private int functionType;


    public int getFunctionType() {
        return functionType;
    }

    public void setFunctionType(int functionType) {
        this.functionType = functionType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Function{" +
                "functionId='" + functionId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", descript='" + descript + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", sort=" + sort +
                ", parentId='" + parentId + '\'' +
                ", state=" + state +
                ", functionType=" + functionType +
                '}';
    }
}
