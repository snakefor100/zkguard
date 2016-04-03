package com.junlong.zkguard.constants;

/**
 * Created by niuniu on 2016/3/29.
 */
public enum MenuEnum {
    Monitor(1,"监控平台"),SETTING(2,"系统设置");

    MenuEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
