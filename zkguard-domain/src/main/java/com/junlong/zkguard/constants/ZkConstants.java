package com.junlong.zkguard.constants;

/**
 * Created by niuniu on 2016/3/29.
 */
public class ZkConstants {
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";
    /******************  节点自检   ******************/
    public static int CHECK_INTERVAL = 30;
    public static int FIXTHREAD_NUM = 50;//默认检测线程数
    public static int SCHEDULEDTHREAD_NUM = 5; //定时任务线程数
    public static final String ZK_GUARD_PATH = "/ZK.GUARD.CHECK";
    public volatile static String ZK_PATH_DATA = "";
    public static final int sessionTimeout = 1000 * 10;//会话超时时间
    public static final int connectTimeout = 1000 * 5;//连接创建超时时间


    /******************  命令代码(部分命令需要节点有nc命令)   ******************/

    public final static String COMMAND_CONS = "echo cons | nc %s %d";
    public final static String COMMAND_STAT = "echo stat | nc %s %d";
    public final static String COMMAND_WCHS = "echo wchs | nc %s %d";
    public final static String COMMAND_WCHC = "echo wchc | nc %s %d";

    /***************       命令返回字段        **************************/
    public final static String FIELD_RECVED = "recved";
    public final static String FIELD_SENT = "sent";
    public final static String FIELD_SID = "sid";
    public final static String FIELD_EST = "est";
    public final static String FIELD_LRESP = "lresp";
    public final static String FIELD_MINLAT = "minlat";
    public final static String FIELD_AVGLAT = "avglat";
    public final static String FIELD_MAXLAT = "maxlat";
    public static final String MODE_FOLLOWER = "Mode: follower";
    public static final String MODE_LEADERER = "Mode: leader";
    public static final String MODE_STANDALONE = "Mode: standalone";
    public static final String MODE_OBSERVER = "Mode: observer";

}
