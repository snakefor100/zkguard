package com.junlong.zkguard.controller;

import com.junlong.common.generator.IdGenerator;
import com.junlong.zkguard.constants.MenuEnum;
import com.junlong.zkguard.domain.Function;
import com.junlong.zkguard.service.FunctionService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuniu on 2016/3/29.
 */
@Controller
@RequestMapping("/function")
public class FunctionController {
    @Resource(name="functionService")
    private FunctionService functionService;


    @RequestMapping(value = "/getFunctionByType")
    @ResponseBody
    public String  getFunctionByType(){
        List<Function> functionByType = functionService.getFunctionByType(1);
        System.out.println(functionByType);
        String s = "[\n" +
                "            {\"id\": \"11\", \"pId\": \"0\", \"name\": \"基础档案\", \"click\": false},\n" +
                "            {\"id\": \"111\", \"pId\": \"11\", \"name\": \"基础档案设置\", \"t\": \"基础档案设置\", \"page\": \"page_base_jichudangan.action\"},\n" +
                "            {\"id\": \"112\", \"pId\": \"11\", \"name\": \"收派标准\", \"page\": \"page_base_standard.action\"}\n" +
                "        ]";
        return s;
    }

    @RequestMapping("/saveFunctionBatch")
    @ResponseBody
    public String saveFunctionBatch() throws InterruptedException {
        System.out.println("AAA");
        List<Function> list = new ArrayList<Function>();
//        Function f1 = new Function();
//        f1.setFunctionId(IdGenerator.getUUID());
//        f1.setFunctionName("Zookeeper基础监控");
//        f1.setFunctionType(MenuEnum.Monitor.getId());
//
//
//        Function f2 = new Function();
//        f2.setFunctionId(IdGenerator.getUUID());
//        f2.setFunctionName("Portal展示");
//        f2.setParentId(f1.getFunctionId());
//        f2.setFunctionType(MenuEnum.Monitor.getId());
//
//        Function f3 = new Function();
//        f3.setFunctionId(IdGenerator.getUUID());
//        f3.setFunctionName("集群监控");
//        f3.setParentId(f1.getFunctionId());
//        f3.setFunctionType(MenuEnum.Monitor.getId());
//
//        Function f4 = new Function();
//        f4.setFunctionId(IdGenerator.getUUID());
//        f4.setFunctionName("机器监控");
//        f4.setParentId(f1.getFunctionId());
//        f4.setFunctionType(MenuEnum.Monitor.getId());
//
//        Function f5 = new Function();
//        f5.setFunctionId(IdGenerator.getUUID());
//        f5.setFunctionName("报警设置");
//        f5.setParentId(f1.getFunctionId());
//        f5.setFunctionType(MenuEnum.Monitor.getId());
//
//        Function f6 = new Function();
//        f6.setFunctionId(IdGenerator.getUUID());
//        f6.setFunctionName("应用管理");
//        f6.setFunctionType(MenuEnum.Monitor.getId());
//
//
//        Function f7 = new Function();
//        f7.setFunctionId(IdGenerator.getUUID());
//        f7.setFunctionName("应用数据");
//        f7.setParentId(f6.getFunctionId());
//        f7.setFunctionType(MenuEnum.Monitor.getId());
//
//        list.add(f1);
//        list.add(f2);
//        list.add(f3);
//        list.add(f4);
//        list.add(f5);
//        list.add(f6);
//        list.add(f7);

        Function f1 = new Function();
        f1.setFunctionId(IdGenerator.getUUID());
        f1.setFunctionName("报警开关");
        f1.setFunctionType(MenuEnum.SETTING.getId());
        Function f2 = new Function();
        f2.setFunctionId(IdGenerator.getUUID());
        f2.setFunctionName("集群设置");
        f2.setFunctionType(MenuEnum.SETTING.getId());
        Function f3 = new Function();
        f3.setFunctionId(IdGenerator.getUUID());
        f3.setFunctionName("系统设置");
        f3.setFunctionType(MenuEnum.SETTING.getId());
        list.add(f1);
        list.add(f2);
        list.add(f3);

        functionService.saveFunctionBatch(list);
        return "AA";
    }
}
