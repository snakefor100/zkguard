package com.junlong.zkguard.domain;

import java.util.List;

/**
 * zk集群信息
 * Created by niuniu on 2016/3/22.
 */
public class ZkClusterInfo {
    private String clusterId;
    private String clusterName;
    private List<String> nodeList;
    private String description;

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

    public List<String> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<String> nodeList) {
        this.nodeList = nodeList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
