package com.business.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-25
 */
@Controller
@RequestMapping("/base")
public class BaseUrlController {

    @Value("${spring.application.baseUrl}")
    private String baseUrl;

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @GetMapping("/index")
    public String index(){
        return "/admin/index";
    }

    @GetMapping("/home")
    public String home(){
        return "/admin/home";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("baseUrl",baseUrl);
        return "/shoplogin";
    }

    @GetMapping("/admin/index")
    public String adminIndex(Model model){
        model.addAttribute("baseUrl",baseUrl);
        return "/admin/index";
    }

    @GetMapping("/footer")
    public String footer(Model model){
        model.addAttribute("baseUrl",baseUrl);
        return "/footer";
    }

    @GetMapping("/seller")
    public String baseSeller(){
        return "/admin/seller";
    }

    @GetMapping("/password")
    public String basePassword(){
        return "/admin/password";
    }

    @GetMapping("/goods/edit")
    public String goodsEdit(){
        return "/admin/goods_edit";
    }

    @GetMapping("/goods")
    public String goods(){
        return "/admin/goods";
    }
}
