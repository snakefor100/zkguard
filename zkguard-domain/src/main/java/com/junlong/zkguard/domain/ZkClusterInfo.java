package com.junlong.zkguard.domain;

import java.util.List;

/**
 * zk集群信息
 * Created by niuniu on 2016/3/22.
 */
public class ZkClusterInfo {
    private String clusterId;
    private String clusterName;
    private String nodeList;
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

    public String getNodeList() {
        return nodeList;
    }

    public void setNodeList(String nodeList) {
        this.nodeList = nodeList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ZkClusterInfo{" +
                "clusterId='" + clusterId + '\'' +
                ", clusterName='" + clusterName + '\'' +
                ", nodeList=" + nodeList +
                ", description='" + description + '\'' +
                '}';
    }
}
