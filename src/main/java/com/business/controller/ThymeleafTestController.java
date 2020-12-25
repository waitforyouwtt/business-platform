package com.business.controller;

import com.business.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-25
 */
@Controller
@Slf4j
public class ThymeleafTestController {

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model){

        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo(1,"张三","zhangsan",0));
        users.add(new UserInfo(2,"李四","lisi",0));
        users.add(new UserInfo(3,"王五","wangwu",0));
        users.add(new UserInfo(4,"小红","xiaohong",1));
        users.add(new UserInfo(4,"吴雨桐","wuyutong",1));

        Map<String,String> map = new HashMap<>();
        map.put("No","1");
        map.put("address","河南");


        model.addAttribute("message","春水初生，春林初盛，春风十里不如你");
        model.addAttribute("users",users);
        model.addAttribute("map",map);
        model.addAttribute("now",new Date());
        return "/thymeleaf";
    }

    @PostMapping("/login-form")
    public String login(@RequestParam("username") String username){
      log.info("得到的数据：{}",username);
        return "/success";
    }
}
