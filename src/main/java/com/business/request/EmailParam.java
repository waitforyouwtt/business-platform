package com.business.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-30
 */
@Data
public class EmailParam extends RequestEmail implements Serializable {

    @ApiModelProperty("客户名称")
    private String customerName;

    @ApiModelProperty(value = "用户开户银行账号",required = false)
    private String openBankAccount;

    @ApiModelProperty("开户银行")
    private String openBankName;

    @ApiModelProperty("转账金额")
    private BigDecimal operationMoney;

    @ApiModelProperty("账号余额")
    private BigDecimal accountBalance;

    @ApiModelProperty("交易时间")
    private Date transactionTime;

    @ApiModelProperty("交易类型")
    private String transactionType;

    @ApiModelProperty("背景图片的绝对路径")
    private String backgroundUrlPath;
}
