package com.junlong.zkguard.service.monitor;

import com.junlong.common.domain.Constants;
import com.junlong.common.domain.ssh.SSHRequest;
import com.junlong.zkguard.constants.MonitorConstant;
import com.junlong.zkguard.domain.MonitorInfo;
import com.junlong.zkguard.domain.ZkClusterState;
import com.junlong.zkguard.domain.ZkNodeBaseInfo;
import com.junlong.zkguard.domain.ZkNodeState;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 节点检测-定时任务
 * Created by niuniu on 2016/4/10.
 */
public class CheckNodeInfoScheduledWorker implements Runnable {
    private static Logger logger = Logger.getLogger(CheckNodeInfoScheduledWorker.class);
    private MonitorInfo monitorInfo;
    private ZkClusterState zkClusterState;

    public CheckNodeInfoScheduledWorker(MonitorInfo monitorInfo, ZkClusterState zkClusterState) {
        this.monitorInfo = monitorInfo;
        this.zkClusterState = zkClusterState;
    }
    @Override
    public void run() {
        logger.info(MonitorConstant.LOG_CHECK_NODE_SCHEDULED_WORKER + "开始定时任务-SSH检测");
        List<String> serviceList = monitorInfo.getServiceList();
        ExecutorService executorService = monitorInfo.getExecutorService();
        Map<String, ZkNodeBaseInfo> zkNodeBaseInfoMap = zkClusterState.getZkNodeBaseInfoMap();
        Map<String, ZkNodeState> zkNodeStateMap = zkClusterState.getZkNodeStateMap();
        for(String serviceIp : serviceList){
            String ip = serviceIp.split(Constants.COLON)[0];
            String zkPort = serviceIp.split(Constants.COLON)[1];
            SSHRequest request =  new SSHRequest();
            request.setIp(ip);
            request.setPort(Constants.SSH_PORT);
            request.setUserName(Constants.SSH_USERNAME);
            request.setPassWord(Constants.SSH_PASSWORD);
            ZkNodeBaseInfo zkNodeBaseInfo = zkNodeBaseInfoMap.get(ip);
            if(zkNodeBaseInfo == null){
                zkNodeBaseInfo = new ZkNodeBaseInfo();
                zkNodeBaseInfo.setNodeIP(ip);
                zkNodeBaseInfoMap.put(ip,zkNodeBaseInfo);
            }
            ZkNodeState zkNodeState = zkNodeStateMap.get(ip);
            if(zkNodeState == null){
                zkNodeState = new ZkNodeState();
                zkNodeState.setNodeIP(ip);
                zkNodeState.setZkPort(Integer.parseInt(zkPort));
                zkNodeStateMap.put(ip,zkNodeState);
            }
            executorService.submit(new CheckNodeInfoWorker(request,zkNodeBaseInfo,zkNodeState));
        }
    }
}
