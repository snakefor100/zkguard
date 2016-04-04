package com.junlong.zkguard.service.impl;

import com.junlong.common.domain.exception.BusinessException;
import com.junlong.common.domain.exception.ResponseCode;
import com.junlong.zkguard.domain.ZkClusterInfo;
import com.junlong.zkguard.service.ValidateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * Created by niuniu on 2016/4/3.
 */
@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public void checkZkClusterInfo(ZkClusterInfo zkClusterInfo) {
        if (null == zkClusterInfo || StringUtils.isBlank(zkClusterInfo.getClusterName()) || StringUtils.isBlank(zkClusterInfo.getNodeList())) {
            throw new BusinessException(ResponseCode.PARAM);
        }
    }
}
