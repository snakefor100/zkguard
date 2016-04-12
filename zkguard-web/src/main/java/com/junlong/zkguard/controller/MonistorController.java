package com.junlong.zkguard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by niuniu on 2016/4/11.
 */
@Controller
@RequestMapping("/monitor")
public class MonistorController {
    @RequestMapping("/tozkCluster")
    public ModelAndView tozkCluster() throws InterruptedException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("zkcluster/monitorZkClustor");
        modelAndView.addObject("test","ff");
        return modelAndView;
    }
    @RequestMapping("/toPortal")
    public ModelAndView toPortal() throws InterruptedException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("zkcluster/portal");
        modelAndView.addObject("test","ff");
        return modelAndView;
    }


}
