package com.business.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@Data
public class TransactionAndMail implements Serializable {

    @ApiModelProperty("交易发起人")
    private String transactionSender;

    @ApiModelProperty("交易接收人")
    private String transactionRecipient;

    @ApiModelProperty("交易金额")
    private BigDecimal transactionMoney;
}
