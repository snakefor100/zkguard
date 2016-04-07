package com.junlong.zkguard.service.monitor;

import com.junlong.zkguard.constants.MonitorConstant;
import com.junlong.zkguard.constants.ZkConstants;
import com.junlong.zkguard.sysenum.ZkNodeStateEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.log4j.Logger;
import org.apache.zookeeper.data.Stat;

import java.util.Map;

/**
 * Created by niuniu on 2016/4/6.
 */
public class CheckSelfWorker implements Runnable {
    private static Logger logger = Logger.getLogger(CheckSelfWorker.class);

    private Map<String, String> map;
    private String serviceIp;

    public CheckSelfWorker(Map<String, String> map, String serviceIp) {
        this.map = map;
        this.serviceIp = serviceIp;
    }

    @Override
    public void run() {
        logger.info(MonitorConstant.LOG_CHECK_SELF_WORKER+"开始自检,IP:"+serviceIp);
        CuratorFramework client = null;
        try {
            map.put(serviceIp, ZkNodeStateEnum.CHECKING.getMsg());
            RetryNTimes retryNTimes = new RetryNTimes(2,2);
            client = CuratorFrameworkFactory.builder().connectString(serviceIp).sessionTimeoutMs(ZkConstants.sessionTimeout).connectionTimeoutMs(ZkConstants.connectTimeout).retryPolicy(retryNTimes).build();
            client.start();
            final NodeCache nodeCache = new NodeCache(client, ZkConstants.ZK_GUARD_PATH);
            nodeCache.start(false);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    String data = new String(nodeCache.getCurrentData().getData());
                    logger.info("测试："+data);
                    if (data.compareTo(ZkConstants.ZK_PATH_DATA) == -1) {
                        logger.error(MonitorConstant.LOG_CHECK_SELF_WORKER+"检测"+serviceIp+"过程中，watcher读取数据不符规范，watcher读取数据:["+data+"],节点自检前设置数据:["+ZkConstants.ZK_PATH_DATA+"]");
                        map.put(serviceIp, ZkNodeStateEnum.WATCHER.getMsg());
                    }
                }
            });

            String saveData = ZkConstants.ZK_GUARD_PATH + System.currentTimeMillis();
            client.setData().forPath(ZkConstants.ZK_GUARD_PATH, saveData.getBytes());
            String zkData = new String(client.getData().storingStatIn(new Stat()).forPath(ZkConstants.ZK_GUARD_PATH));
            if (StringUtils.isBlank(zkData) || !zkData.startsWith(ZkConstants.ZK_GUARD_PATH)) {
                map.put(serviceIp, ZkNodeStateEnum.DATAERROR.getMsg());
                logger.error(MonitorConstant.LOG_CHECK_SELF_WORKER+"检测"+serviceIp+"过程中，直接读取到的数据不符规范，读取数据:["+zkData+"]");
            }else{
                map.put(serviceIp, ZkNodeStateEnum.OK.getMsg());
                logger.info(MonitorConstant.LOG_CHECK_SELF_WORKER+"检测"+serviceIp+"数据成功");
            }
            nodeCache.close();
        } catch (Exception e) {
            map.put(serviceIp, ZkNodeStateEnum.CRASH.getMsg());
            logger.error(MonitorConstant.LOG_CHECK_SELF_WORKER+"检测"+serviceIp+"过程中，发生异常:"+e.getMessage(),e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
