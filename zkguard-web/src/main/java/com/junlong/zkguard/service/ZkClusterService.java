package com.junlong.zkguard.service;

import com.junlong.common.domain.PageRequestBean;
import com.junlong.common.domain.PageResponseBean;
import com.junlong.zkguard.domain.ZkClusterInfo;

import java.util.List;

/**
 * Created by niuniu on 2016/3/28.
 */
public interface ZkClusterService {
    void saveZkClusterInfo(ZkClusterInfo zkClusterInfo);
    void saveOrUpdateZkClusterInfo(ZkClusterInfo zkClusterInfo);
    PageResponseBean<ZkClusterInfo> getZkClusterInfoByParam(PageRequestBean requestBean);
}
