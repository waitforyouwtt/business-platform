package com.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-25
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "/index";
    }
}
