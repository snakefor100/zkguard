package com.junlong.zkguard.service;

import com.junlong.zkguard.domain.ZkClusterInfo;

/**
 * Created by niuniu on 2016/4/3.
 */
public interface ValidateService {
    void checkZkClusterInfo(ZkClusterInfo zkClusterInfo);
}
