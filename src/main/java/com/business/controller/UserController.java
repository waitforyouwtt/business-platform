package com.business.controller;

import cn.hutool.json.JSONUtil;
import com.business.entity.BusinessAccount;
import com.business.result.Result;
import com.business.service.BusinessAccountService;
import com.business.utils.MapStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-25
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    /**
     * 当前账号常量
     */
    private static final String ACCOUNT = "account";


    @Autowired
    BusinessAccountService businessAccountService;

    @PostMapping("/login")
    public Result login(@RequestParam("data") String data) throws UnsupportedEncodingException {
        String formData = URLDecoder.decode(data, "utf-8");
        BusinessAccount account = JSONUtil.toBean(JSONUtil.toJsonStr(MapStringUtils.json2map(formData)), BusinessAccount.class);
        BusinessAccount result = businessAccountService.login(account);
        if (Objects.isNull(result)){
            return Result.error(404,"用户名或密码错误");
        }
        setAccount(result);
        return Result.success(result);
    }
}
