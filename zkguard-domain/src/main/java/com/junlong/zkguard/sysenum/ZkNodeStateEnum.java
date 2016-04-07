package com.junlong.zkguard.sysenum;

/**
 * Created by niuniu on 2016/4/6.
 */
public enum ZkNodeStateEnum {
    OK(1,"正常运行"),CHECKING(2,"正在检查"),CRASH(3,"宕机"),WATCHER(4,"watcher监控数据异常"),DATAERROR(5,"读取数据异常");

    ZkNodeStateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
