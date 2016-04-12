package com.junlong.zkguard.domain;

/**
 * Zookeeper集群节点基础信息
 * Created by niuniu on 2016/4/6.
 */
public class ZkNodeBaseInfo {
    private String nodeIP;
    private float cpuUsage;
    private float memUsage;
    private float load;
    private String Disk;//挂在点 百分比
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNodeIP() {
        return nodeIP;
    }

    public void setNodeIP(String nodeIP) {
        this.nodeIP = nodeIP;
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public float getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(float memUsage) {
        this.memUsage = memUsage;
    }

    public float getLoad() {
        return load;
    }

    public void setLoad(float load) {
        this.load = load;
    }

    public String getDisk() {
        return Disk;
    }

    public void setDisk(String disk) {
        Disk = disk;
    }
}
