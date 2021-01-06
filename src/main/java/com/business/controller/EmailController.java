package com.business.controller;

import com.business.request.EmailParam;
import com.business.request.RequestEmail;
import com.business.request.TransactionAndMail;
import com.business.service.BusinessAccountBankInfoService;
import com.business.service.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-30
 */
@RestController
public class EmailController {

    @Value("${spring.mail.username}")
    private String systemSender;

    @Autowired
    EmailService emailService;

    @Autowired
    BusinessAccountBankInfoService businessAccountBankInfoService;

    @ApiOperation("发送纯文本邮件：支持抄送")
    @PostMapping("/simple-send")
    public String send(@ModelAttribute RequestEmail vo) {
        if (StringUtils.isEmpty(vo.getEmailSender()) || CollectionUtils.isEmpty(vo.getEmailRecipients())){
            return "fail";
        }
        emailService.simpleSend(vo);
        return "true";
    }

    @ApiOperation("发送附件邮件：支持附件和抄送")
    @PostMapping("/enclosure-send")
    public String enclosureSend(@ModelAttribute RequestEmail vo) {
        vo.setEmailSender(systemSender);
        if (StringUtils.isEmpty(vo.getEmailSender()) || CollectionUtils.isEmpty(vo.getEmailRecipients())){
            return "fail";
        }
        emailService.enclosureSend(vo);
        return "true";
    }

    @ApiOperation("发送模板邮件：支持附件和抄送")
    @PostMapping("/model-send")
    public String modelSend(@ModelAttribute EmailParam vo) {
        vo.setEmailSender(systemSender);
        if (StringUtils.isEmpty(vo.getEmailSender()) || CollectionUtils.isEmpty(vo.getEmailRecipients())){
            return "fail";
        }
        //查询得到的数据
        vo.setCustomerName("云澜");
        vo.setOpenBankName("招商银行");
        vo.setOperationMoney(new BigDecimal("1000000000"));
        vo.setAccountBalance(new BigDecimal("10000000200"));
        vo.setTemplate("/mail/mail-model");
        vo.setTransactionTime(new Date());
        emailService.modelSend(vo);
        return "true";
    }

    @ApiOperation("用户转账，触发邮件发送")
    @PostMapping("/transaction-mail")
    public String transactionMail(@ModelAttribute TransactionAndMail transactionAndMail){
        businessAccountBankInfoService.transactionMail(transactionAndMail);
        return "true";
    }


}
