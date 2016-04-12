package com.junlong.zkguard.service.monitor;

import com.junlong.common.domain.exception.BusinessException;
import com.junlong.common.domain.exception.ResponseCode;
import com.junlong.common.domain.ssh.SSHRequest;
import com.junlong.service.ssh.SSHExecuteCallBack;
import com.junlong.service.ssh.SSHServiceTemplate;
import com.junlong.service.ssh.SSHSession;
import com.junlong.zkguard.constants.MonitorConstant;
import com.junlong.zkguard.constants.ZkConstants;
import com.junlong.zkguard.domain.*;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static void executeSSHCheckWork(String clusterId) {
        logger.info(MonitorConstant.LOG_MONITOR_MANAGER + "开启检测节点状态:" + clusterId);
        MonitorInfo monitorInfo = monitorInfoMap.get(clusterId);
        if (monitorInfo == null) {
            logger.error(MonitorConstant.LOG_MONITOR_MANAGER + "集群没有找到:" + clusterId);
            return;
        }
        ZkClusterState zkClusterState = resultMap.get(clusterId);
        if (zkClusterState == null) {
            zkClusterState = new ZkClusterState();
        }
        Map<String,ZkNodeBaseInfo> zkNodeBaseInfo = new HashMap<String,ZkNodeBaseInfo>();
        Map<String,ZkNodeState> zkNodeState = new HashMap<String,ZkNodeState>();
        zkClusterState.setZkNodeBaseInfoMap(zkNodeBaseInfo);
        zkClusterState.setZkNodeStateMap(zkNodeState);
        resultMap.put(clusterId,zkClusterState);
        ScheduledExecutorService scheduledExecutorService = monitorInfo.getScheduledExecutorService();
        scheduledExecutorService.scheduleWithFixedDelay(new CheckNodeInfoScheduledWorker(monitorInfo, zkClusterState), 0, ZkConstants.CHECK_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 开始执行自检
     */
    private static void executeSelfCheckWork(String clusterId) {
        logger.info(MonitorConstant.LOG_MONITOR_MANAGER + "开启自检:" + clusterId);
        MonitorInfo monitorInfo = monitorInfoMap.get(clusterId);
        if (monitorInfo == null) {
            logger.error(MonitorConstant.LOG_MONITOR_MANAGER + "集群没有找到:" + clusterId);
            return;
        }

        ZkClusterState zkClusterState = resultMap.get(clusterId);
        if (zkClusterState == null) {
            zkClusterState = new ZkClusterState();
        }
        zkClusterState.setStartCheckSelf(System.currentTimeMillis());
        ZkConstants.ZK_PATH_DATA = ZkConstants.ZK_GUARD_PATH + System.currentTimeMillis();
        Map<String, String> checkSelfMap = new HashMap<String, String>();
        zkClusterState.setCheckSelfStateMap(checkSelfMap);
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
            throw new BusinessException(ResponseCode.INIT,e);
        } finally {
            if (client == null) {
                client.close();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ZkClusterInfo zkClusterInfo = new ZkClusterInfo();
//        zkClusterInfo.setClusterId("a");
//        zkClusterInfo.setNodeList("192.168.1.170:2181,192.168.1.171:2181,192.168.1.172:2181");
//        zkClusterInfo.setClusterName("test");
//        MonitorManager.addCheckWorkNode(zkClusterInfo);
//        MonitorManager.executeSelfCheckWork("a");
//        ZkClusterState zkClusterState = resultMap.get("a");
//        Map<String, String> checkSelfState = zkClusterState.getCheckSelfStateMap();
//        while (true) {
//            Thread.sleep(1000);
//            System.out.println(checkSelfState);
//        }

        ZkClusterInfo zkClusterInfo = new ZkClusterInfo();
        zkClusterInfo.setClusterId("a");
        zkClusterInfo.setNodeList("192.168.1.170:2181,192.168.1.171:2181,192.168.1.172:2181");
        zkClusterInfo.setClusterName("test");
        MonitorManager.addCheckWorkNode(zkClusterInfo);
        MonitorManager.executeSSHCheckWork("a");
        ZkClusterState zkClusterState = resultMap.get("a");
        Map<String, ZkNodeState> zkNodeStateMap = zkClusterState.getZkNodeStateMap();
        while (true) {
            Thread.sleep(1000);
            System.out.println(zkNodeStateMap);
        }



//        SSHRequest request = new SSHRequest();
//        request.setIp("192.168.1.170");
//        request.setPort(22);
//        request.setUserName("root");
//        request.setPassWord("123456");
//        SSHSession session = new SSHServiceTemplate(request);
//        session.execute(String.format(ZkConstants.COMMAND_WCHC, request.getIp(),2181), new SSHExecuteCallBack() {
//            @Override
//            public void processResult(BufferedReader bufferedReader) {
//                String line = "";
//                try {
//                    int i=0;
//                    while ((line = bufferedReader.readLine()) != null){
//                        System.out.println("NO:"+i);
//                        System.out.println(line);
//                        i++;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        String s = "/127.0.0.1:59236[1](queued=0,recved=11,sent=11,sid=0x5400489da100c8,lop=PING,sid=aaa,est=1460305367425,to=30000,lcxid=0x0,lzxid=0xffffffffffffffff,lresp=1460305467661,llat=1,minlat=0,avglat=1,maxlat=7)";
//        String s1 = "maxlat=(?s).*?)";
//        Pattern compile = Pattern.compile(s1, Pattern.CANON_EQ);
//        Matcher matcher = compile.matcher(s);
//
//        while (matcher.find()){
//            System.out.println(matcher.group());
//            System.out.println(matcher.group().substring(matcher.group().indexOf("=")+1,matcher.group().length()));
//        }
//        NO:0
//        Zookeeper version: 3.4.6-1569965, built on 02/20/2014 09:09 GMT
//        NO:1
//        Clients:
//        NO:2
//                /192.168.1.170:34366[0](queued=0,recved=1,sent=0)
//        NO:3
//                /127.0.0.1:37370[1](queued=0,recved=1046,sent=1046)
//        NO:4
//
//        NO:5
//        Latency min/avg/max: 0/0/116
//        NO:6
//        Received: 1055
//        NO:7
//        Sent: 1054
//        NO:8
//        Connections: 2
//        NO:9
//        Outstanding: 0
//        NO:10
//        Zxid: 0x5a00000001
//        NO:11
//        Mode: follower
//        NO:12
//        Node count: 45




        //wchs
//        NO:0
//        1 connections watching 1 paths
//        NO:1
//        Total watches:1

        //wchc
//        NO:0
//        0x54058e4f830003
//        NO:1
//                /ZK.GUARD.CHECK
//        NO:2

    }
}
