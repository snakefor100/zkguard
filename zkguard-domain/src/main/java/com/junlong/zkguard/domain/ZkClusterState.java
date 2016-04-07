package com.junlong.zkguard.domain;

import java.util.List;
import java.util.Map;

/**
 * 集群监控状态
 * Created by niuniu on 2016/4/6.
 */
public class ZkClusterState {
    private String clusterId;
    private String clusterName;
    private List<ZkNodeBaseInfo> zkNodeBaseInfo;
    private List<ZkNodeState> zkNodeState;
    private long startCheckSelf;
    private Map<String,String> checkSelfState;

    public Map<String, String> getCheckSelfState() {
        return checkSelfState;
    }

    public void setCheckSelfState(Map<String, String> checkSelfState) {
        this.checkSelfState = checkSelfState;
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

    public List<ZkNodeBaseInfo> getZkNodeBaseInfo() {
        return zkNodeBaseInfo;
    }

    public void setZkNodeBaseInfo(List<ZkNodeBaseInfo> zkNodeBaseInfo) {
        this.zkNodeBaseInfo = zkNodeBaseInfo;
    }

    public List<ZkNodeState> getZkNodeState() {
        return zkNodeState;
    }

    public void setZkNodeState(List<ZkNodeState> zkNodeState) {
        this.zkNodeState = zkNodeState;
    }
}
