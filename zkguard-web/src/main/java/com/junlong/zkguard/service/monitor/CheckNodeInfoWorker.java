package com.junlong.zkguard.service.monitor;

import com.junlong.common.domain.Constants;
import com.junlong.common.domain.ssh.SSHRequest;
import com.junlong.service.ssh.SSHExecuteCallBack;
import com.junlong.service.ssh.SSHServiceTemplate;
import com.junlong.service.ssh.SSHSession;
import com.junlong.utils.DateUtils;
import com.junlong.zkguard.constants.MonitorConstant;
import com.junlong.zkguard.constants.ZkConstants;
import com.junlong.zkguard.domain.NodeConnector;
import com.junlong.zkguard.domain.SessionWatcher;
import com.junlong.zkguard.domain.ZkNodeBaseInfo;
import com.junlong.zkguard.domain.ZkNodeState;
import com.junlong.zkguard.sysenum.ZkRole;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuniu on 2016/4/10.
 */
public class CheckNodeInfoWorker implements Runnable {
    private static Logger logger = Logger.getLogger(CheckNodeInfoWorker.class);
    private SSHSession sshSession;
    private ZkNodeBaseInfo zkNodeBaseInfo;
    private ZkNodeState zkNodeState;

    public CheckNodeInfoWorker(SSHRequest sshRequest, ZkNodeBaseInfo zkNodeBaseInfo, ZkNodeState zkNodeState) {
        sshSession = new SSHServiceTemplate(sshRequest);
        this.zkNodeBaseInfo = zkNodeBaseInfo;
        this.zkNodeState = zkNodeState;
    }

    @Override
    public void run() {
        executeCONS();
        executeSTAT();
        executeWCHS();
        executeWCHC();
    }

    private void executeWCHC() {
        final String commond = String.format(ZkConstants.COMMAND_WCHC, zkNodeState.getNodeIP(), zkNodeState.getZkPort());
        logger.info(MonitorConstant.LOG_CHECK_NODE_WORKER + "开始任务-检查命令:" + commond);
        sshSession.execute(commond, new SSHExecuteCallBack() {
            @Override
            public void processResult(BufferedReader bufferedReader) {
                String line = null;
                List<SessionWatcher> sessionWatcherList = new ArrayList<SessionWatcher>();

                try {
                    SessionWatcher sessionWatcher = null;
                    List<String> pathList = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (StringUtils.isBlank(line)) {
                            if(sessionWatcher != null){
                                sessionWatcherList.add(sessionWatcher);
                            }
                            continue;
                        }
                        if (line.startsWith("0x")) {
                            if(sessionWatcher != null){
                                sessionWatcherList.add(sessionWatcher);
                            }
                            sessionWatcher = new SessionWatcher();
                            sessionWatcher.setSessionId(line);
                            pathList = new ArrayList<String>();
                            sessionWatcher.setPathList(pathList);
                        }else{
                            pathList.add(line);
                        }
                    }
                    zkNodeState.setSessionWatcherList(sessionWatcherList);
                } catch (Exception e) {
                    logger.error(MonitorConstant.LOG_CHECK_NODE_WORKER + "执行" + commond + "命令后，从bufferedReader获取数据时发生异常:" + e.getMessage(), e);
                }
            }
        });
    }

    private void executeWCHS() {
        final String commond = String.format(ZkConstants.COMMAND_WCHS, zkNodeState.getNodeIP(), zkNodeState.getZkPort());
        logger.info(MonitorConstant.LOG_CHECK_NODE_WORKER + "开始任务-检查命令:" + commond);
        sshSession.execute(commond, new SSHExecuteCallBack() {
            @Override
            public void processResult(BufferedReader bufferedReader) {
                String line = null;
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        if (StringUtils.isBlank(line)) {
                            continue;
                        }
                        if (line.contains("connections watching")) {
                            String[] watchInfo = line.replace("paths", "").split("connections watching");
                            zkNodeState.setConnectionWatcher(Integer.parseInt(watchInfo[0].trim()));
                            zkNodeState.setConnectionWatchedPath(Integer.parseInt(watchInfo[1].trim()));
                            continue;
                        }
                        if (line.contains("Total watches:")) {
                            zkNodeState.setTotalWatchNum(Integer.parseInt(line.replace("Total watches:", "").trim()));
                        }
                    }
                } catch (Exception e) {
                    logger.error(MonitorConstant.LOG_CHECK_NODE_WORKER + "执行" + commond + "命令后，从bufferedReader获取数据时发生异常:" + e.getMessage(), e);
                }
            }
        });
    }

    private void executeSTAT() {
        final String commond = String.format(ZkConstants.COMMAND_STAT, zkNodeState.getNodeIP(), zkNodeState.getZkPort());
        logger.info(MonitorConstant.LOG_CHECK_NODE_WORKER + "开始任务-检查命令:" + commond);
        sshSession.execute(commond, new SSHExecuteCallBack() {
            @Override
            public void processResult(BufferedReader bufferedReader) {
                String line = null;
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        if (StringUtils.isBlank(line)) {
                            continue;
                        }
                        if (line.contains("Received")) {
                            zkNodeState.setReceivedSize(Long.parseLong(line.split(Constants.COLON)[1].trim()));
                            continue;
                        }
                        if (line.contains("Sent")) {
                            zkNodeState.setSentSize(Long.parseLong(line.split(Constants.COLON)[1].trim()));
                            continue;
                        }
                        if (line.contains("Connections")) {
                            zkNodeState.setConnectionNum(Integer.parseInt(line.split(Constants.COLON)[1].trim()));
                            continue;
                        }
                        if (line.contains(ZkConstants.MODE_FOLLOWER)) {
                            zkNodeState.setRole(ZkRole.FOLLOWER.getRole());
                            continue;
                        }
                        if (line.contains(ZkConstants.MODE_LEADERER)) {
                            zkNodeState.setRole(ZkRole.LEADER.getRole());
                            continue;
                        }
                        if (line.contains(ZkConstants.MODE_OBSERVER)) {
                            zkNodeState.setRole(ZkRole.OBSERVER.getRole());
                            continue;
                        }
                        if (line.contains(ZkConstants.MODE_STANDALONE)) {
                            zkNodeState.setRole(ZkRole.STANDALONE.getRole());
                            continue;
                        }
                        if (line.contains("Node count")) {
                            zkNodeState.setNodeCount(Integer.parseInt(line.split(Constants.COLON)[1].trim()));
                            continue;
                        }
                        if (line.contains("Zxid")) {
                            zkNodeState.setZxId(line.split(Constants.COLON)[1].trim());
                            continue;
                        }
                    }
                } catch (Exception e) {
                    logger.error(MonitorConstant.LOG_CHECK_NODE_WORKER + "执行" + commond + "命令后，从bufferedReader获取数据时发生异常:" + e.getMessage(), e);
                }
            }
        });
    }

    private void executeCONS() {
        final String commond = String.format(ZkConstants.COMMAND_CONS, zkNodeState.getNodeIP(), zkNodeState.getZkPort());
        logger.info(MonitorConstant.LOG_CHECK_NODE_WORKER + "开始任务-检查命令:" + commond);
        sshSession.execute(commond, new SSHExecuteCallBack() {
            @Override
            public void processResult(BufferedReader bufferedReader) {
                List<NodeConnector> nodeConnectorList = new ArrayList<NodeConnector>();
                String line = null;
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        if (StringUtils.isBlank(line)) {
                            continue;
                        }
                        String[] tuple = line.split("\\(");
                        if (tuple.length != 2) {
                            continue;
                        }
                        NodeConnector nodeConnector = new NodeConnector();
                        String[] infoArr = tuple[1].substring(0, tuple[1].length() - 1).split(",");
                        for (String info : infoArr) {
                            String[] attribute = info.split("=");
                            switch (attribute[0]) {
                                case ZkConstants.FIELD_SID:
                                    nodeConnector.setSessionId(attribute[1]);
                                    break;
                                case ZkConstants.FIELD_SENT:
                                    nodeConnector.setSent(Long.parseLong(attribute[1]));
                                    break;
                                case ZkConstants.FIELD_RECVED:
                                    nodeConnector.setRecved(Long.parseLong(attribute[1]));
                                    break;
                                case ZkConstants.FIELD_EST:
                                    nodeConnector.setEstablishTime(DateUtils.long2String(Long.parseLong(attribute[1])));
                                    break;
                                case ZkConstants.FIELD_LRESP:
                                    nodeConnector.setLastResTime(DateUtils.long2String(Long.parseLong(attribute[1])));
                                    break;
                                case ZkConstants.FIELD_MAXLAT:
                                    nodeConnector.setMaxlat(Integer.parseInt(attribute[1]));
                                    break;
                                case ZkConstants.FIELD_MINLAT:
                                    nodeConnector.setMinlat(Integer.parseInt(attribute[1]));
                                    break;
                                case ZkConstants.FIELD_AVGLAT:
                                    nodeConnector.setAvglat(Integer.parseInt(attribute[1]));
                                    break;
                            }
                        }
                        String[] nodeinfo = tuple[0].substring(1).split(":");
                        nodeConnector.setIp(nodeinfo[0]);
                        nodeConnector.setPort(Integer.parseInt(nodeinfo[1].substring(0, nodeinfo[1].indexOf("["))));
                        nodeConnectorList.add(nodeConnector);
                    }
                    zkNodeState.setConnectorList(nodeConnectorList);
                } catch (IOException e) {
                    logger.error(MonitorConstant.LOG_CHECK_NODE_WORKER + "执行" + commond + "命令后，从bufferedReader获取数据时发生异常:" + e.getMessage(), e);
                }
            }
        });
    }
}
