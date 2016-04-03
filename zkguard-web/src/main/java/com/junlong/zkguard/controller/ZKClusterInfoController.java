package com.junlong.zkguard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junlong.common.domain.PageRequestBean;
import com.junlong.common.domain.PageResponseBean;
import com.junlong.zkguard.domain.ZkClusterInfo;
import com.junlong.zkguard.service.ZkClusterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuniu on 2016/3/23.
 */
@Controller
@RequestMapping("/zkClusterInfo")
public class ZKClusterInfoController {
    @Resource(name="zkClusterService")
    private ZkClusterService zkClusterService;

    @RequestMapping("/getZkClusterByParam")
    @ResponseBody
    public PageResponseBean<ZkClusterInfo>  getZkClusterByParam(PageRequestBean requestBean) throws InterruptedException, JsonProcessingException {
        System.out.println("入参:"+requestBean);
        PageResponseBean<ZkClusterInfo> zkClusterInfoByParam = zkClusterService.getZkClusterInfoByParam(requestBean);
        System.out.println("结果:"+zkClusterInfoByParam.getTotal()+"\t"+zkClusterInfoByParam.getRows());
        ObjectMapper obj = new ObjectMapper();
        String s = obj.writeValueAsString(zkClusterInfoByParam);
        System.out.println("结果2："+s);
        return zkClusterInfoByParam;
    }

    @RequestMapping("/toZkClusterView")
    public String toZkClusterView() throws InterruptedException {
        return "zkcluster/zkClusterInfo";
    }

    @RequestMapping("/insertzk")
    @ResponseBody
    public List<String> testMy() throws InterruptedException {
        List<String> result = new ArrayList<String>();
        result.add("A");
        ZkClusterInfo z1 = new ZkClusterInfo();
        z1.setClusterId("1");
        z1.setClusterName("A");
        z1.setDescription("F");
        zkClusterService.saveZkClusterInfo(z1);
        Thread.sleep(1000);
        ZkClusterInfo z2 = new ZkClusterInfo();
        z2.setClusterId("2");
        z2.setClusterName("A1");
        z2.setDescription("F");
        zkClusterService.saveZkClusterInfo(z2);
        return result;
    }
}
