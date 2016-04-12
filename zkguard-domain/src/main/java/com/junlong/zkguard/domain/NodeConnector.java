package com.junlong.zkguard.domain;

/**
 * Zk节点连接者
 * Created by niuniu on 2016/4/11.
 */
public class NodeConnector {
    /**
     * 连接Ip
     */
    private String ip;
    /**
     * 连接端口
     */
    private int port;
    /**
     * 接收package
     */
    private long recved;
    /**
     * 发送package
     */
    private long sent;
    /**
     * 会话Id
     */
    private String sessionId;
    /**
     * 建立时间
     */
    private String establishTime;
    /**
     * 最后回复时间
     */
    private String lastResTime;
    /**
     * 最小延迟
     */
    private int minlat;
    /**
     * 平均延迟
     */
    private int avglat;
    /**
     * 最大延迟
     */
    private int maxlat;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getRecved() {
        return recved;
    }

    public void setRecved(long recved) {
        this.recved = recved;
    }

    public long getSent() {
        return sent;
    }

    public void setSent(long sent) {
        this.sent = sent;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getLastResTime() {
        return lastResTime;
    }

    public void setLastResTime(String lastResTime) {
        this.lastResTime = lastResTime;
    }

    public int getMinlat() {
        return minlat;
    }

    public void setMinlat(int minlat) {
        this.minlat = minlat;
    }

    public int getAvglat() {
        return avglat;
    }

    public void setAvglat(int avglat) {
        this.avglat = avglat;
    }

    public int getMaxlat() {
        return maxlat;
    }

    public void setMaxlat(int maxlat) {
        this.maxlat = maxlat;
    }

    @Override
    public String toString() {
        return "NodeConnector{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", recved=" + recved +
                ", sent=" + sent +
                ", sessionId='" + sessionId + '\'' +
                ", establishTime='" + establishTime + '\'' +
                ", lastResTime='" + lastResTime + '\'' +
                ", minlat=" + minlat +
                ", avglat=" + avglat +
                ", maxlat=" + maxlat +
                '}';
    }
}
