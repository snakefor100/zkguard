package com.junlong.zkguard.domain;

import java.util.Map;

/**
 * 集群监控状态
 * Created by niuniu on 2016/4/6.
 */
public class ZkClusterState {
    private String clusterId;
    private String clusterName;
    private Map<String,ZkNodeBaseInfo> zkNodeBaseInfoMap;
    private Map<String,ZkNodeState> zkNodeStateMap;
    private long startCheckSelf;
    private Map<String, String> checkSelfStateMap;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public long getStartCheckSelf() {
        return startCheckSelf;
    }

    public void setStartCheckSelf(long startCheckSelf) {
        this.startCheckSelf = startCheckSelf;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Map<String, ZkNodeBaseInfo> getZkNodeBaseInfoMap() {
        return zkNodeBaseInfoMap;
    }

    public void setZkNodeBaseInfoMap(Map<String, ZkNodeBaseInfo> zkNodeBaseInfoMap) {
        this.zkNodeBaseInfoMap = zkNodeBaseInfoMap;
    }

    public Map<String, ZkNodeState> getZkNodeStateMap() {
        return zkNodeStateMap;
    }

    public void setZkNodeStateMap(Map<String, ZkNodeState> zkNodeStateMap) {
        this.zkNodeStateMap = zkNodeStateMap;
    }

    public Map<String, String> getCheckSelfStateMap() {
        return checkSelfStateMap;
    }

    public void setCheckSelfStateMap(Map<String, String> checkSelfStateMap) {
        this.checkSelfStateMap = checkSelfStateMap;
    }
}
