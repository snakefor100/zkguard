package com.junlong.zkguard.service.impl;

import com.junlong.common.domain.PageRequestBean;
import com.junlong.common.domain.PageResponseBean;
import com.junlong.zkguard.dao.Dao;
import com.junlong.zkguard.domain.ZkClusterInfo;
import com.junlong.zkguard.service.ZkClusterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by niuniu on 2016/3/28.
 */
@Transactional
@Service("zkClusterService")
public class ZkClusterServiceImpl implements ZkClusterService{
    @Resource(name="daoSupport")
    private Dao<ZkClusterInfo> dao;

    @Override
    public void saveZkClusterInfo(ZkClusterInfo zkClusterInfo) {
        dao.save("ZkClusterInfoMapper.saveClusterInfo",zkClusterInfo);
    }

    @Override
    public void saveOrUpdateZkClusterInfo(ZkClusterInfo zkClusterInfo) {
        dao.save("ZkClusterInfoMapper.saveOrUpdateClusterInfo",zkClusterInfo);
    }

    @Override
    public PageResponseBean<ZkClusterInfo> getZkClusterInfoByParam(PageRequestBean requestBean) {
        PageResponseBean responseBean = new PageResponseBean();
        responseBean.setTotal(dao.getCount("ZkClusterInfoMapper.getClusterInfoCount"));
        List<ZkClusterInfo> list = dao.findForList("ZkClusterInfoMapper.getClusterInfoByParam", requestBean);
        responseBean.setRows(list);
        return responseBean;
    }
}
