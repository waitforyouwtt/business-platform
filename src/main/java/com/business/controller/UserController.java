package com.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login(){
        return "/admin/index";
    }
}
