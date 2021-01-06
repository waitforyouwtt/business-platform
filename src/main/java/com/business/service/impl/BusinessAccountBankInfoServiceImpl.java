package com.business.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.business.dao.BusinessAccountBankInfoMapper;
import com.business.entity.BusinessAccountBankInfo;
import com.business.request.EmailParam;
import com.business.request.TransactionAndMail;
import com.business.service.BusinessAccountBankInfoService;
import com.business.service.EmailService;
import com.business.service.SingleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@Slf4j
@Service
public class BusinessAccountBankInfoServiceImpl implements BusinessAccountBankInfoService {

    @Value("${spring.mail.username}")
    private String systemSender;

    @Resource
    BusinessAccountBankInfoMapper businessAccountBankInfoMapper;

    @Autowired
    SingleService singleService;

    @Autowired
    EmailService emailService;

    @Override
    public BusinessAccountBankInfo queryByUserId(String userId) {
        QueryWrapper<BusinessAccountBankInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return businessAccountBankInfoMapper.selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionMail(TransactionAndMail transactionAndMail) {
        Map<String,String> senderUserMap = businessAccountBankInfoMapper.queryTransactionAccouontUserId(transactionAndMail.getTransactionSender());
        Map<String,String> recipientUserMap = businessAccountBankInfoMapper.queryTransactionAccouontUserId(transactionAndMail.getTransactionRecipient());
        if (MapUtil.isEmpty(senderUserMap) || MapUtil.isEmpty(recipientUserMap)) {
            return;
        }
        BusinessAccountBankInfo sender = singleService.queryBusinessAccountBankInfo(senderUserMap.get("user_id"));
        BusinessAccountBankInfo recipient = singleService.queryBusinessAccountBankInfo(recipientUserMap.get("user_id"));

        if (Objects.isNull(sender) || Objects.isNull(recipient)) {
            return;
        }
        if (transactionAndMail.getTransactionMoney().compareTo(sender.getAccountBalance()) > -1){
            log.info("操作金额大于等于账户余额");
            return;
        }
        if (transactionAndMail.getTransactionMoney().compareTo(sender.getAccountBalance()) == -1){
            log.info("操作金额小于账户余额");
        }
        if (transactionAndMail.getTransactionMoney().compareTo(sender.getAccountBalance()) == 0){
            log.info("操作金额等于账户余额");
        }

        if (transactionAndMail.getTransactionMoney().compareTo(sender.getAccountBalance()) == 1){
            log.info("操作金额大于账户余额");
        }
        if (transactionAndMail.getTransactionMoney().compareTo(sender.getAccountBalance()) < 1){
            log.info("操作金额小于等于账户余额");
        }
        //交易发起人扣减金额
        transactionDeduction(sender,transactionAndMail.getTransactionMoney());
        //交易接收人增加金额
        transactionAdd(recipient,transactionAndMail.getTransactionMoney());
        //给交易人发邮件
        senderEmail(sender,transactionAndMail.getTransactionMoney(),senderUserMap.get("contact_email"));
        //给接收人发邮件
        recipientEmail(recipient,transactionAndMail.getTransactionMoney(),recipientUserMap.get("contact_email"));
    }

    private void transactionDeduction(BusinessAccountBankInfo sender, BigDecimal transactionMoney){
        QueryWrapper<BusinessAccountBankInfo> senderWrapper = new QueryWrapper<>();
        senderWrapper.eq("user_id",sender.getUserId());
        BusinessAccountBankInfo params = new BusinessAccountBankInfo();

        params.setUserId(sender.getUserId());
        params.setAccountBalance(sender.getAccountBalance().subtract(transactionMoney));
        params.setTransactionTime(new Date());
        params.setCreateTime(new Date());
        businessAccountBankInfoMapper.update(params,senderWrapper);
    }

    private void transactionAdd(BusinessAccountBankInfo recipient, BigDecimal transactionMoney){
        QueryWrapper<BusinessAccountBankInfo> senderWrapper = new QueryWrapper<>();
        senderWrapper.eq("user_id",recipient.getUserId());
        BusinessAccountBankInfo params = new BusinessAccountBankInfo();

        params.setUserId(recipient.getUserId());
        params.setAccountBalance(recipient.getAccountBalance().add(transactionMoney));
        params.setTransactionTime(new Date());
        params.setCreateTime(new Date());
        businessAccountBankInfoMapper.update(params,senderWrapper);
    }

    private void senderEmail(BusinessAccountBankInfo sender,BigDecimal transactionMoney,String mail){
        EmailParam vo = new EmailParam();
        vo.setEmailSender(systemSender);
        vo.setEmailRecipients(Arrays.asList(mail));
        vo.setCustomerName(sender.getUserName());
        vo.setOpenBankName(sender.getOpenBankName());
        vo.setOperationMoney(transactionMoney);
        vo.setAccountBalance(sender.getAccountBalance().subtract(transactionMoney));
        vo.setBackgroundUrlPath(null);
        vo.setTemplate("/mail-model");
        vo.setTransactionTime(new Date());
        if (sender.getOpenBankName().equals("招商银行")){
            vo.setOpenBankAccount(sender.getOpenBankAccount().substring(12,16));
            vo.setBackgroundUrlPath("http://81.68.86.66:8080/group1/M00/00/00/rBEADl_sbG-AJvrXAABcFa3_PBQ71.jpeg");
        }else if(sender.getOpenBankName().equals("交通银行")){
            vo.setOpenBankAccount(sender.getOpenBankAccount().substring(15,19));
            vo.setBackgroundUrlPath("http://81.68.86.66:8080/group1/M00/00/00/rBEADl_0PjuAAeXuAABB0z52Z3E85.jpeg");
        }else if(sender.getOpenBankName().equals("农业银行")){
            vo.setOpenBankAccount(sender.getOpenBankAccount().substring(15,19));
            vo.setBackgroundUrlPath("http://81.68.86.66:8080/group1/M00/00/00/rBEADl_0PmiAcK0EAABAP-ye03M33.jpeg");
        }
        vo.setEmailSubject("动账通知");
        vo.setTransactionType("网银转账");

        emailService.modelSend(vo);
    }

    private void recipientEmail(BusinessAccountBankInfo sender,BigDecimal transactionMoney,String mail){
        EmailParam vo = new EmailParam();
        vo.setEmailSender(systemSender);
        vo.setEmailRecipients(Arrays.asList(mail));
        vo.setCustomerName(sender.getUserName());
        vo.setOpenBankName(sender.getOpenBankName());
        vo.setOperationMoney(transactionMoney);
        vo.setAccountBalance(sender.getAccountBalance().add(transactionMoney));
        vo.setBackgroundUrlPath(null);
        vo.setTemplate("/mail-model");
        vo.setTransactionTime(new Date());
        if (sender.getOpenBankName().equals("招商银行")){
            vo.setOpenBankAccount(sender.getOpenBankAccount().substring(12,16));
            vo.setBackgroundUrlPath("http://81.68.86.66:8080/group1/M00/00/00/rBEADl_sbG-AJvrXAABcFa3_PBQ71.jpeg");
        }else if(sender.getOpenBankName().equals("交通银行")){
            vo.setOpenBankAccount(sender.getOpenBankAccount().substring(15,19));
            vo.setBackgroundUrlPath("http://81.68.86.66:8080/group1/M00/00/00/rBEADl_0PjuAAeXuAABB0z52Z3E85.jpeg");
        }else if(sender.getOpenBankName().equals("农业银行")){
            vo.setOpenBankAccount(sender.getOpenBankAccount().substring(15,19));
            vo.setBackgroundUrlPath("http://81.68.86.66:8080/group1/M00/00/00/rBEADl_0PmiAcK0EAABAP-ye03M33.jpeg");
        }
        vo.setEmailSubject("账户余额变动");
        vo.setTransactionType("网银入账");

        emailService.modelSend(vo);
    }

}
