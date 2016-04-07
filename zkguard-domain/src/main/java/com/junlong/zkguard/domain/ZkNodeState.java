package com.junlong.zkguard.domain;

/**
 * Zookeeper集群节点状态
 * Created by niuniu on 2016/4/6.
 */
public class ZkNodeState {
    private String nodeIP;
    private String Role;
    private int connectionNum;
    private int watchNum;
    private int watchedPath;
    private long sentSize;
    private long receivedSize;


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

    public int getConnectionNum() {
        return connectionNum;
    }

    public void setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
    }

    public int getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(int watchNum) {
        this.watchNum = watchNum;
    }

    public int getWatchedPath() {
        return watchedPath;
    }

    public void setWatchedPath(int watchedPath) {
        this.watchedPath = watchedPath;
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

}
