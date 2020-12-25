package com.business.entity;


import lombok.Data;

import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2020-10-15
 */
@Data
public class UserInfo {

    private Integer id;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String userNick;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户性别：0 男 1 女 2保密
     */
    private Integer gender;
    private String  genderDesc;
    /**
     * 用户头像
     */
    private String headImage;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户状态：0 正常 1 禁用
     */
    private Integer status;
    private String  statusDesc;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 绑定邮箱
     */
    private String email;

    /**
     * 校验码
     */
    private String verifyCode;

    /**
     * 绑定邮箱状态：0 未绑定 1已绑定
     */
    private Integer emailStatus;

    /**
     * 绑定微信
     */
    private String wechat;
    /**
     * 家庭住址
     */
    private String familyAddress;

    public UserInfo(Integer id,String userNick, String password,int gender) {
        this.id = id;
        this.userNick = userNick;
        this.password = password;
        this.gender = gender;
    }
}
