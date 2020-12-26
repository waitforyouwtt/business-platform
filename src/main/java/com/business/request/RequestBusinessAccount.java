package com.business.request;

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
@Data
public class RequestBusinessAccount implements Serializable{

	@ApiModelProperty(value = "登录名",required = false)
	private String loginName;

	@ApiModelProperty(value = "登录密码",required = false)
	private String loginPassword;

	@ApiModelProperty(value = "店铺名称",required = false)
	private String shopName;

	@ApiModelProperty(value = "公司名称",required = false)
	private String companyName;

	@ApiModelProperty(value = "公司电话",required = false)
	private String companyMobile;

	@ApiModelProperty(value = "公司详细地址",required = false)
	private String companyAddress;

	@ApiModelProperty(value = "联系人名字",required = false)
	private String contactName;

	@ApiModelProperty(value = "联系人微信",required = false)
	private String contactWechat;

	@ApiModelProperty(value = "联系人电话",required = false)
	private String contactMobile;

	@ApiModelProperty(value = "联系人邮箱",required = false)
	private String contactEmail;

	@ApiModelProperty(value = "营业执照号",required = false)
	private String businessLicenseNo;

	@ApiModelProperty(value = "税务登记号",required = false)
	private String taxRegistrationNo;

	@ApiModelProperty(value = "组织机构代码证",required = false)
	private String organizationNo;

	@ApiModelProperty(value = "法定代表人",required = false)
	private String legalRepresentative;

	@ApiModelProperty(value = "法定代表人身份证号",required = false)
	private String legalRepresentativeIdCard;

	@ApiModelProperty(value = "开户行名称",required = false)
	private String openBankName;

	@ApiModelProperty(value = "开户行支行名称",required = false)
	private String openBankBranchName;

	@ApiModelProperty(value = "开户行银行账号",required = false)
	private String openBankAccount;

	/*@ApiModelProperty(value = "商家状态：0正常 1 禁用",required = false)
	private Integer businessStatus;*/

/*	@ApiModelProperty(value = "是否删除：0正常 1禁用",required = false)
    @Column(name = "is_delete")
	private Integer isDelete;*/

	/*@ApiModelProperty(value = "创建人",required = false)
    @Column(name = "create_by")
	private String createBy;

	@ApiModelProperty(value = "修改人",required = false)
    @Column(name = "update_by")
	private String updateBy;*/

	/*@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "修改时间",required = false)
    @Column(name = "update_time")
	private Date updateTime;
*/

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyMobile() {
		return companyMobile;
	}

	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactWechat() {
		return contactWechat;
	}

	public void setContactWechat(String contactWechat) {
		this.contactWechat = contactWechat;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getTaxRegistrationNo() {
		return taxRegistrationNo;
	}

	public void setTaxRegistrationNo(String taxRegistrationNo) {
		this.taxRegistrationNo = taxRegistrationNo;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getLegalRepresentativeIdCard() {
		return legalRepresentativeIdCard;
	}

	public void setLegalRepresentativeIdCard(String legalRepresentativeIdCard) {
		this.legalRepresentativeIdCard = legalRepresentativeIdCard;
	}

	public String getOpenBankName() {
		return openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}

	public String getOpenBankBranchName() {
		return openBankBranchName;
	}

	public void setOpenBankBranchName(String openBankBranchName) {
		this.openBankBranchName = openBankBranchName;
	}

	public String getOpenBankAccount() {
		return openBankAccount;
	}

	public void setOpenBankAccount(String openBankAccount) {
		this.openBankAccount = openBankAccount;
	}
}
