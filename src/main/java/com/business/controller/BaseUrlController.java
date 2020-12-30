package com.business.controller;

import com.business.entity.BusinessAccount;
import com.business.request.EmailParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-25
 */
@Controller
@RequestMapping("/base")
public class BaseUrlController extends BaseController{

    @Value("${spring.application.baseUrl}")
    private String baseUrl;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Value("${file.rootPath}")
    private String backgroundUrlPath;

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
    public String baseSeller(Model model){
        BusinessAccount account = getAccount();
        model.addAttribute("account",account);
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

    @GetMapping("/email")
    public String email(Model model){
        model.addAttribute("emailSender",emailSender);
        model.addAttribute("emailRecipients", Arrays.asList("17621007255@163.com"));
        model.addAttribute("emailCopyPersons",Arrays.asList("15290810931@163.com"));
        model.addAttribute("emailSubject","元旦祝福");
        model.addAttribute("emailContent","happy new year");
        return "/email";
    }
    @GetMapping("/mail-model")
    public String mailModel(Model model){
        EmailParam customer = new EmailParam();
        customer.setCustomerName("云澜");
        customer.setTransactionTime(new Date());
        customer.setTemplate("/mail-model");
        model.addAttribute("customer",customer);
        return "/mail-model";
    }

}
