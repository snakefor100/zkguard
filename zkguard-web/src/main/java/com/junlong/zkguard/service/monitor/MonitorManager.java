package com.junlong.zkguard.service.monitor;

import com.junlong.common.domain.exception.BusinessException;
import com.junlong.common.domain.exception.ResponseCode;
import com.junlong.zkguard.constants.ZkConstants;
import com.junlong.zkguard.constants.MonitorConstant;
import com.junlong.zkguard.domain.MonitorInfo;
import com.junlong.zkguard.domain.ZkClusterInfo;
import com.junlong.zkguard.domain.ZkClusterState;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by niuniu on 2016/4/5.
 */
public class MonitorManager {
    private static Logger logger = Logger.getLogger(MonitorManager.class);


    private volatile static Map<String, ZkClusterState> resultMap = new ConcurrentHashMap<String, ZkClusterState>();//结果集
    private volatile static Map<String, MonitorInfo> monitorInfoMap = new ConcurrentHashMap<String, MonitorInfo>();


    public static void addCheckWorkNode(ZkClusterInfo zkClusterInfo) {
        logger.info(MonitorConstant.LOG_MONITOR_MANAGER + "添加监控节点：" + zkClusterInfo);
        MonitorInfo monitorInfo = new MonitorInfo(zkClusterInfo);
        ExecutorService executorService = Executors.newFixedThreadPool(ZkConstants.FIXTHREAD_NUM);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(ZkConstants.SCHEDULEDTHREAD_NUM);
        monitorInfo.setExecutorService(executorService);
        monitorInfo.setScheduledExecutorService(scheduledExecutorService);
        monitorInfoMap.put(zkClusterInfo.getClusterId(), monitorInfo);
    }

    /**
     * 远程连接检查，获取zk集群，以及服务器相关信息
     */
    private static void executeSSHCheckWork() {

    }

    /**
     * 开始执行自检
     */
    private static void executeSelfCheckWork(String clusterId) {
        logger.info(MonitorConstant.LOG_MONITOR_MANAGER + "开启自检:" + clusterId);
        MonitorInfo monitorInfo = monitorInfoMap.get(clusterId);
        if (monitorInfo == null) {
            logger.error(MonitorConstant.LOG_MONITOR_MANAGER + "自检集群没有找到:" + clusterId);
            return;
        }

        ZkClusterState zkClusterState = resultMap.get(clusterId);
        if (zkClusterState == null) {
            zkClusterState = new ZkClusterState();
        }
        zkClusterState.setStartCheckSelf(System.currentTimeMillis());
        ZkConstants.ZK_PATH_DATA = ZkConstants.ZK_GUARD_PATH + System.currentTimeMillis();
        Map<String, String> checkSelfMap = new HashMap<String, String>();
        zkClusterState.setCheckSelfState(checkSelfMap);
        resultMap.put(clusterId, zkClusterState);

        //初始化节点数据
        initZkData(monitorInfo);

        ScheduledExecutorService scheduledExecutorService = monitorInfo.getScheduledExecutorService();
        scheduledExecutorService.scheduleWithFixedDelay(new CheckSelfScheduledWorker(monitorInfo, zkClusterState), 0, ZkConstants.CHECK_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 初始化zk数据
     *
     * @param monitorInfo
     */
    private static void initZkData(MonitorInfo monitorInfo) {
        CuratorFramework client = null;
        try {
            RetryNTimes retryNTimes = new RetryNTimes(2, 2);
            client = CuratorFrameworkFactory.builder().connectString(monitorInfo.getNodeList()).sessionTimeoutMs(ZkConstants.sessionTimeout).connectionTimeoutMs(ZkConstants.connectTimeout).retryPolicy(retryNTimes).build();
            client.start();
            if (client.checkExists().forPath(ZkConstants.ZK_GUARD_PATH) == null) {

                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(ZkConstants.ZK_GUARD_PATH, ZkConstants.ZK_PATH_DATA.getBytes());
            }
        } catch (Exception e) {
            logger.error(MonitorConstant.LOG_MONITOR_MANAGER + "初始化ZK数据发生异常.", e);
            throw new BusinessException(ResponseCode.INIT);
        } finally {
            if (client == null) {
                client.close();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZkClusterInfo zkClusterInfo = new ZkClusterInfo();
        zkClusterInfo.setClusterId("a");
        zkClusterInfo.setNodeList("192.168.1.170:2181,192.168.1.171:2181,192.168.1.172:2181");
        zkClusterInfo.setClusterName("test");
        MonitorManager.addCheckWorkNode(zkClusterInfo);
        MonitorManager.executeSelfCheckWork("a");
        ZkClusterState zkClusterState = resultMap.get("a");
        Map<String, String> checkSelfState = zkClusterState.getCheckSelfState();
        while (true) {
            Thread.sleep(1000);
            System.out.println(checkSelfState);
        }
    }
}
