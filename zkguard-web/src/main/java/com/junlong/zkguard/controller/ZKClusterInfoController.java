package com.junlong.zkguard.controller;

import com.junlong.zkguard.dao.ZkClusterInfoDao;
import com.junlong.zkguard.domain.ZkClusterInfo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuniu on 2016/3/23.
 */
@Controller
@RequestMapping("/test")
public class ZKClusterInfoController {
    @Resource
    private ZkClusterInfoDao zkClusterInfoDao;
    @RequestMapping("/insertzk")
    @ResponseBody
    public List<String> testMy() throws InterruptedException {
        List<String> result = new ArrayList<String>();
        result.add("A");
        ZkClusterInfo z1 = new ZkClusterInfo();
        z1.setClusterId("1");
        z1.setClusterName("A");
        z1.setDescription("F");
        zkClusterInfoDao.saveClusterInfo(z1);
        Thread.sleep(1000);
        ZkClusterInfo z2 = new ZkClusterInfo();
        z2.setClusterId("2");
        z2.setClusterName("A1");
        z2.setDescription("F");
        zkClusterInfoDao.saveClusterInfo(z2);
        return result;
    }
}
