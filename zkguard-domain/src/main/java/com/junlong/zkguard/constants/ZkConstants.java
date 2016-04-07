package com.junlong.zkguard.constants;

/**
 * Created by niuniu on 2016/3/29.
 */
public class ZkConstants {
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";
    /*********  节点自检 **************/
    public static int CHECK_INTERVAL = 10;
    public static int FIXTHREAD_NUM = 20;//默认自检线程数
    public static int SCHEDULEDTHREAD_NUM = 5; //定时任务线程数
    public static final String ZK_GUARD_PATH = "/ZK.GUARD.CHECK";
    public volatile static String ZK_PATH_DATA = "";
    public static final int sessionTimeout = 1000*10;//会话超时时间
    public static final int connectTimeout = 1000*5;//连接创建超时时间
}
