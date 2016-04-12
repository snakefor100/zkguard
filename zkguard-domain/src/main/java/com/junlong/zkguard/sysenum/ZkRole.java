package com.junlong.zkguard.sysenum;

/**
 * Created by niuniu on 2016/4/12.
 */
public enum ZkRole {
    LEADER("L"),FOLLOWER("F"),OBSERVER("O"),STANDALONE("S");

    ZkRole(String role) {
        this.role = role;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
