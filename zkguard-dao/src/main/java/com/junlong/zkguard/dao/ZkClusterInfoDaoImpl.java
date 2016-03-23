//package com.junlong.zkguard.dao;
//
//import com.junlong.zkguard.domain.ZkClusterInfo;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by niuniu on 2016/3/23.
// */
//@Service
//@Transactional
//public class ZkClusterInfoDaoImpl extends BaseDao implements ZkClusterInfoDao {
//    @Override
//    public void init() {
//
//    }
//
//    @Override
//    public void saveClusterInfo(ZkClusterInfo zkClusterInfo) {
//        getSqlSession().insert("saveClusterInfo",zkClusterInfo);
//        int a = 1/0;
//    }
//}
