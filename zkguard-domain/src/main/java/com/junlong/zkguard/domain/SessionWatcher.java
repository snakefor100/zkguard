package com.junlong.zkguard.domain;

import java.util.List;

/**
 * Created by niuniu on 2016/4/12.
 */
public class SessionWatcher {
    private String sessionId;
    private List<String> pathList;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }

    @Override
    public String toString() {
        return "SessionWatcher{" +
                "sessionId='" + sessionId + '\'' +
                ", pathList=" + pathList +
                '}';
    }
}
