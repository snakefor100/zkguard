package com.junlong.zkguard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junlong.common.domain.PageRequestBean;
import com.junlong.common.domain.PageResponseBean;
import com.junlong.common.domain.exception.BusinessException;
import com.junlong.common.domain.exception.ResponseCode;
import com.junlong.common.generator.IdGenerator;
import com.junlong.zkguard.domain.ZkClusterInfo;
import com.junlong.zkguard.service.ValidateService;
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
    @Resource
    private ValidateService validateService;

    @RequestMapping("/getZkClusterByParam")
    @ResponseBody
    public PageResponseBean<ZkClusterInfo>  getZkClusterByParam(PageRequestBean requestBean){
        System.out.println("入参"+requestBean);
        requestBean.setStart((requestBean.getPage()-1)*requestBean.getRows());
        PageResponseBean<ZkClusterInfo> zkClusterInfoByParam = zkClusterService.getZkClusterInfoByParam(requestBean);
        return zkClusterInfoByParam;
    }

    @RequestMapping("/toZkClusterView")
    public String toZkClusterView() throws InterruptedException {
        return "zkcluster/zkClusterInfo";
    }

    @RequestMapping("/saveZkCluster")
    @ResponseBody
    public PageResponseBean<ZkClusterInfo> saveZkCluster(ZkClusterInfo zkClusterInfo){
        try {
            validateService.checkZkClusterInfo(zkClusterInfo);
            zkClusterInfo.setClusterId(IdGenerator.getUUID());
            PageResponseBean<ZkClusterInfo> responseBean = new PageResponseBean<ZkClusterInfo>();
            zkClusterService.saveZkClusterInfo(zkClusterInfo);
            return responseBean;
        }catch (BusinessException e){
            return new PageResponseBean<ZkClusterInfo>(ResponseCode.PARAM);
        }
    }


    @RequestMapping("/saveOrUpdateZkCluster")
    @ResponseBody
    public PageResponseBean<ZkClusterInfo> saveOrUpdateZkCluster(ZkClusterInfo zkClusterInfo){
        try {
            validateService.checkZkClusterInfo(zkClusterInfo);
            PageResponseBean<ZkClusterInfo> responseBean = new PageResponseBean<ZkClusterInfo>();
            zkClusterService.saveOrUpdateZkClusterInfo(zkClusterInfo);
            return responseBean;
        }catch (BusinessException e){
            return new PageResponseBean<ZkClusterInfo>(ResponseCode.PARAM);
        }
    }
}
