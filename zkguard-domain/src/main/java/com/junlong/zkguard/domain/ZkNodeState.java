package com.junlong.zkguard.domain;

import java.util.List;

/**
 * Zookeeper集群节点状态
 * Created by niuniu on 2016/4/6.
 */
public class ZkNodeState {
    private String nodeIP;
    private int zkPort;
    private String Role;
    private int connectionNum;
    private List<NodeConnector> connectorList;
    private List<SessionWatcher> sessionWatcherList;
    private int totalWatchNum;
    private int connectionWatchedPath;
    private int connectionWatcher;
    private long sentSize;
    private long receivedSize;
    private String zxId;
    private int nodeCount;


    public List<SessionWatcher> getSessionWatcherList() {
        return sessionWatcherList;
    }

    public void setSessionWatcherList(List<SessionWatcher> sessionWatcherList) {
        this.sessionWatcherList = sessionWatcherList;
    }

    public String getZxId() {
        return zxId;
    }

    public void setZxId(String zxId) {
        this.zxId = zxId;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public int getConnectionNum() {
        return connectionNum;
    }

    public void setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
    }

    public int getZkPort() {
        return zkPort;
    }

    public void setZkPort(int zkPort) {
        this.zkPort = zkPort;
    }

    public String getNodeIP() {
        return nodeIP;
    }

    public void setNodeIP(String nodeIP) {
        this.nodeIP = nodeIP;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public List<NodeConnector> getConnectorList() {
        return connectorList;
    }

    public void setConnectorList(List<NodeConnector> connectorList) {
        this.connectorList = connectorList;
    }


    public int getTotalWatchNum() {
        return totalWatchNum;
    }

    public void setTotalWatchNum(int totalWatchNum) {
        this.totalWatchNum = totalWatchNum;
    }

    public int getConnectionWatchedPath() {
        return connectionWatchedPath;
    }

    public void setConnectionWatchedPath(int connectionWatchedPath) {
        this.connectionWatchedPath = connectionWatchedPath;
    }

    public int getConnectionWatcher() {
        return connectionWatcher;
    }

    public void setConnectionWatcher(int connectionWatcher) {
        this.connectionWatcher = connectionWatcher;
    }

    public long getSentSize() {
        return sentSize;
    }

    public void setSentSize(long sentSize) {
        this.sentSize = sentSize;
    }

    public long getReceivedSize() {
        return receivedSize;
    }

    public void setReceivedSize(long receivedSize) {
        this.receivedSize = receivedSize;
    }

    @Override
    public String toString() {
        return "ZkNodeState{" +
                "nodeIP='" + nodeIP + '\'' +
                ", zkPort=" + zkPort +
                ", Role='" + Role + '\'' +
                ", connectionNum=" + connectionNum +
                ", connectorList=" + connectorList +
                ", sessionWatcherList=" + sessionWatcherList +
                ", totalWatchNum=" + totalWatchNum +
                ", connectionWatchedPath=" + connectionWatchedPath +
                ", connectionWatcher=" + connectionWatcher +
                ", sentSize=" + sentSize +
                ", receivedSize=" + receivedSize +
                ", zxId='" + zxId + '\'' +
                ", nodeCount=" + nodeCount +
                '}';
    }
}
