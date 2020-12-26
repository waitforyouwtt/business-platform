package com.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
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
@Table(name="business_account")
public class BusinessAccount implements Serializable{

	@ApiModelProperty(value = "自增主键",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

	@ApiModelProperty(value = "登录名",required = false)
    @Column(name = "login_name")
	private String loginName;

	@ApiModelProperty(value = "登录密码",required = false)
    @Column(name = "login_password")
	private String loginPassword;

	@ApiModelProperty(value = "店铺名称",required = false)
    @Column(name = "shop_name")
	private String shopName;

	@ApiModelProperty(value = "公司名称",required = false)
    @Column(name = "company_name")
	private String companyName;

	@ApiModelProperty(value = "公司电话",required = false)
    @Column(name = "company_mobile")
	private String companyMobile;

	@ApiModelProperty(value = "公司详细地址",required = false)
    @Column(name = "company_address")
	private String companyAddress;

	@ApiModelProperty(value = "联系人名字",required = false)
    @Column(name = "contact_name")
	private String contactName;

	@ApiModelProperty(value = "联系人微信",required = false)
    @Column(name = "contact_wechat")
	private String contactWechat;

	@ApiModelProperty(value = "联系人电话",required = false)
    @Column(name = "contact_mobile")
	private String contactMobile;

	@ApiModelProperty(value = "联系人邮箱",required = false)
    @Column(name = "contact_email")
	private String contactEmail;

	@ApiModelProperty(value = "营业执照号",required = false)
    @Column(name = "business_license_no")
	private String businessLicenseNo;

	@ApiModelProperty(value = "税务登记号",required = false)
    @Column(name = "tax_registration_no")
	private String taxRegistrationNo;

	@ApiModelProperty(value = "组织机构代码证",required = false)
    @Column(name = "organization_no")
	private String organizationNo;

	@ApiModelProperty(value = "法定代表人",required = false)
    @Column(name = "legal_representative")
	private String legalRepresentative;

	@ApiModelProperty(value = "法定代表人身份证号",required = false)
    @Column(name = "legal_representative_id_card")
	private String legalRepresentativeIdCard;

	@ApiModelProperty(value = "开户行名称",required = false)
    @Column(name = "open_bank_name")
	private String openBankName;

	@ApiModelProperty(value = "开户行支行名称",required = false)
    @Column(name = "open_bank_branch_name")
	private String openBankBranchName;

	@ApiModelProperty(value = "开户行银行账号",required = false)
    @Column(name = "open_bank_account")
	private String openBankAccount;

	@ApiModelProperty(value = "商家状态：0正常 1 禁用",required = false)
    @Column(name = "business_status")
	private Integer businessStatus;

	@ApiModelProperty(value = "是否删除：0正常 1禁用",required = false)
    @Column(name = "is_delete")
	private Integer isDelete;

	@ApiModelProperty(value = "创建人",required = false)
    @Column(name = "create_by")
	private String createBy;

	@ApiModelProperty(value = "修改人",required = false)
    @Column(name = "update_by")
	private String updateBy;

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "修改时间",required = false)
    @Column(name = "update_time")
	private Date updateTime;

}
