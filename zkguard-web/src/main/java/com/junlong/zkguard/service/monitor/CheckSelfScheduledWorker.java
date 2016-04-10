package com.junlong.zkguard.service.monitor;

import com.junlong.zkguard.constants.MonitorConstant;
import com.junlong.zkguard.constants.ZkConstants;
import com.junlong.zkguard.domain.MonitorInfo;
import com.junlong.zkguard.domain.ZkClusterState;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 自检-定时任务
 * Created by niuniu on 2016/4/7.
 */
public class CheckSelfScheduledWorker implements Runnable {
    private static Logger logger = Logger.getLogger(CheckSelfScheduledWorker.class);
    private MonitorInfo monitorInfo;
    private ZkClusterState zkClusterState;

    public CheckSelfScheduledWorker(MonitorInfo monitorInfo, ZkClusterState zkClusterState) {
        this.monitorInfo = monitorInfo;
        this.zkClusterState = zkClusterState;
    }

    @Override
    public void run() {
        try {
            logger.info(MonitorConstant.LOG_CHECK_SELF_SCHEDULED_WORKER + "开始定时任务-自检");
            ZkConstants.ZK_PATH_DATA = ZkConstants.ZK_GUARD_PATH + System.currentTimeMillis();
            List<String> serviceList = monitorInfo.getServiceList();
            Map<String, String> checkSelfState = zkClusterState.getCheckSelfState();
            ExecutorService executorService = monitorInfo.getExecutorService();
            for (String serviceIp : serviceList) {
                executorService.submit(new CheckSelfWorker(checkSelfState, serviceIp));
            }
        } catch (Exception e) {
            logger.info(MonitorConstant.LOG_CHECK_SELF_SCHEDULED_WORKER + "开始定时任务-自检-发生异常:" + e.getMessage(), e);
        }
    }
}
