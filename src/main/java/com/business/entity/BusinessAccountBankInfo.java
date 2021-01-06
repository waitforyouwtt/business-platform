package com.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@Data
@TableName("business_account_bank_info")
public class BusinessAccountBankInfo implements Serializable {

     @ApiModelProperty(value = "自增主键",required = false)
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;

     @ApiModelProperty(value = "用户id",required = false)
     @TableField(value = "user_id")
     private String userId;

     @ApiModelProperty(value = "用户名字",required = false)
     @TableField(value = "user_name")
     private String userName;

     @ApiModelProperty(value = "用户开户银行账号",required = false)
     @TableField(value = "open_bank_account")
     private String openBankAccount;

     @ApiModelProperty(value = "开户银行",required = false)
     @TableField(value = "open_bank_name")
     private String openBankName;

     @ApiModelProperty(value = "开户银行支行",required = false)
     @TableField(value = "open_bank_branch_name")
     private String openBankBranchName;

     @ApiModelProperty(value = "账户余额",required = false)
     @TableField(value = "account_balance")
     private BigDecimal accountBalance;

     @ApiModelProperty(value = "操作金额",required = false)
     @TableField(value = "operation_money")
     private BigDecimal operationMoney;

     @ApiModelProperty(value = "交易时间",required = false)
     @TableField(value = "transaction_time")
     private Date transactionTime;

     @ApiModelProperty(value = "创建时间",required = false)
     @TableField(value = "create_time")
     private Date createTime;
}
