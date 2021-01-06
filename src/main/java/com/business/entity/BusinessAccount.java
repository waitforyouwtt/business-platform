package com.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ApiModel(description = "BusinessAccount",value = "BusinessAccount")
@Data
@TableName("business_account")
public class BusinessAccount implements Serializable{

	@ApiModelProperty(value = "自增主键",required = false)
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value = "登录名",required = false)
    @TableField(value = "login_name")
	private String loginName;

	@ApiModelProperty(value = "登录密码",required = false)
	@TableField(value = "login_password")
	private String loginPassword;

	@ApiModelProperty(value = "店铺名称",required = false)
	@TableField(value = "shop_name")
	private String shopName;

	@ApiModelProperty(value = "公司名称",required = false)
	@TableField(value = "company_name")
	private String companyName;

	@ApiModelProperty(value = "公司电话",required = false)
	@TableField(value = "company_mobile")
	private String companyMobile;

	@ApiModelProperty(value = "公司详细地址",required = false)
	@TableField(value = "company_address")
	private String companyAddress;

	@ApiModelProperty(value = "联系人名字",required = false)
	@TableField(value = "contact_name")
	private String contactName;

	@ApiModelProperty(value = "联系人微信",required = false)
	@TableField(value = "contact_wechat")
	private String contactWechat;

	@ApiModelProperty(value = "联系人电话",required = false)
	@TableField(value = "contact_mobile")
	private String contactMobile;

	@ApiModelProperty(value = "联系人邮箱",required = false)
	@TableField(value = "contact_email")
	private String contactEmail;

	@ApiModelProperty(value = "营业执照号",required = false)
	@TableField(value = "business_license_no")
	private String businessLicenseNo;

	@ApiModelProperty(value = "税务登记号",required = false)
	@TableField(value = "tax_registration_no")
	private String taxRegistrationNo;

	@ApiModelProperty(value = "组织机构代码证",required = false)
	@TableField(value = "organization_no")
	private String organizationNo;

	@ApiModelProperty(value = "法定代表人",required = false)
	@TableField(value = "legal_representative")
	private String legalRepresentative;

	@ApiModelProperty(value = "法定代表人身份证号",required = false)
	@TableField(value = "legal_representative_id_card")
	private String legalRepresentativeIdCard;

	@ApiModelProperty(value = "开户行名称",required = false)
	@TableField(value = "open_bank_name")
	private String openBankName;

	@ApiModelProperty(value = "开户行支行名称",required = false)
	@TableField(value = "open_bank_branch_name")
	private String openBankBranchName;

	@ApiModelProperty(value = "开户行银行账号",required = false)
	@TableField(value = "open_bank_account")
	private String openBankAccount;

	@ApiModelProperty(value = "商家状态：0正常 1 禁用",required = false)
	@TableField(value = "business_status")
	private Integer businessStatus;

	@ApiModelProperty(value = "是否删除：0正常 1禁用",required = false)
	@TableField(value = "is_delete")
	private Integer isDelete;

	@ApiModelProperty(value = "创建人",required = false)
	@TableField(value = "create_by")
	private String createBy;

	@ApiModelProperty(value = "修改人",required = false)
	@TableField(value = "update_by")
	private String updateBy;

	@ApiModelProperty(value = "创建时间",required = false)
	@TableField(value = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "修改时间",required = false)
	@TableField(value = "update_time")
	private Date updateTime;

}
