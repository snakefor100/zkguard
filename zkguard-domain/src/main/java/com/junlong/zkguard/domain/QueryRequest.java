package com.junlong.zkguard.domain;

import java.util.Map;

/**
 * Created by niuniu on 2016/3/29.
 */
public class QueryRequest {
    private int start;
    private int limit = 20;
    private int pageSize = 20;
    private Map<String,String> sortItemMap;
    private Map<String,Object> fields;

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, String> getSortItemMap() {
        return sortItemMap;
    }

    public void setSortItemMap(Map<String, String> sortItemMap) {
        this.sortItemMap = sortItemMap;
    }
}
