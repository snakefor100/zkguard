package com.junlong.zkguard.domain;

import com.junlong.common.domain.Constants;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 集群监控 继承自集群信息
 * Created by niuniu on 2016/4/6.
 */
public class MonitorInfo extends ZkClusterInfo{

    private boolean isCheckSelf;
    private boolean isCheckNodeState;
    private ExecutorService executorService; //Fix类型的自检 线程池
    private ScheduledExecutorService scheduledExecutorService;//定时线程
    private List<String> serviceList;
    public MonitorInfo(ZkClusterInfo zkClusterInfo) {

        this.setClusterId(zkClusterInfo.getClusterId());
        this.setClusterName(zkClusterInfo.getClusterName());
        this.setNodeList(zkClusterInfo.getNodeList());
        serviceList = Arrays.asList(zkClusterInfo.getNodeList().split(Constants.COMMA));
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public boolean isCheckSelf() {
        return isCheckSelf;
    }

    public void setCheckSelf(boolean checkSelf) {
        isCheckSelf = checkSelf;
    }

    public boolean isCheckNodeState() {
        return isCheckNodeState;
    }

    public void setCheckNodeState(boolean checkNodeState) {
        isCheckNodeState = checkNodeState;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public String toString() {
        return "MonitorInfo{" +
                "executorService=" + executorService +
                ", scheduledExecutorService=" + scheduledExecutorService +
                ", clusterId=" + this.getClusterId() +
                ", clusterName=" + this.getClusterName() +
                ", nodeList=" + this.getNodeList() +
                '}';
    }
}