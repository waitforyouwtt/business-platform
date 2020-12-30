package com.business.service;

import com.business.request.EmailParam;
import com.business.request.RequestEmail;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-30
 */
public interface EmailService {

    /**
     * 发送纯文本邮件：支持抄送
     */
    void simpleSend(RequestEmail vo);

    /**
     *发送附件邮件：支持附件和抄送
     * @param vo
     */
    void enclosureSend(RequestEmail vo);

    /**
     * 发送模板邮件：支持抄送
     * @param vo
     */
    void modelSend(EmailParam vo);
}
