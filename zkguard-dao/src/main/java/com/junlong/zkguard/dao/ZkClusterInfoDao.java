package com.junlong.zkguard.dao;

import com.junlong.zkguard.domain.ZkClusterInfo;

/**
 * Created by niuniu on 2016/3/23.
 */
public interface ZkClusterInfoDao {
    void saveClusterInfo(ZkClusterInfo zkClusterInfo);
}
