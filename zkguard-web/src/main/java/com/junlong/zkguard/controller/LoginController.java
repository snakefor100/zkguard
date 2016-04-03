package com.junlong.zkguard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by niuniu on 2016/3/26.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/userlogin")
    public String login() throws InterruptedException {
        String result = "main/main";
        return result;
    }
}
