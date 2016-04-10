package com.junlong.zkguard.service.monitor;

import com.junlong.zkguard.constants.MonitorConstant;
import com.junlong.zkguard.domain.MonitorInfo;
import com.junlong.zkguard.domain.ZkClusterState;
import org.apache.log4j.Logger;

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
        logger.info(MonitorConstant.LOG_CHECK_NODE_SCHEDULED_WORKER + "开始定时任务-自检");
    }
}
