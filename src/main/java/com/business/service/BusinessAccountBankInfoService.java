package com.business.service;

import com.business.entity.BusinessAccountBankInfo;
import com.business.request.TransactionAndMail;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
public interface BusinessAccountBankInfoService {

    /**
     * 根据用户id查询账户信息
     * @param userId
     * @return
     */
    BusinessAccountBankInfo queryByUserId(String userId);

    /**
     * 转账 & 发送邮件
     * @param transactionAndMail
     */
    void transactionMail(TransactionAndMail transactionAndMail);
}
