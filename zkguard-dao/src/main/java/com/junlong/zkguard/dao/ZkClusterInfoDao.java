package com.junlong.zkguard.dao;

import com.junlong.zkguard.domain.ZkClusterInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by niuniu on 2016/3/23.
 */
@Repository
public interface ZkClusterInfoDao {
    void saveClusterInfo(ZkClusterInfo zkClusterInfo);
}
