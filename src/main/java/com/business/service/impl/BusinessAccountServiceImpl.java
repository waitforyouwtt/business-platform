package com.business.service.impl;
import com.business.dao.BusinessAccountMapper;
import com.business.entity.BusinessAccount;
import com.business.service.BusinessAccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class BusinessAccountServiceImpl implements BusinessAccountService {

    @Resource
    private BusinessAccountMapper businessAccountMapper;

    /**
     * 增加BusinessAccount
     * @param businessAccount
     */
    @Override
    public void add(BusinessAccount businessAccount){
        businessAccount.setIsDelete(0);
        businessAccount.setBusinessStatus(1);
        businessAccount.setCreateTime(new Date());
        businessAccount.setUpdateTime(new Date());
        businessAccountMapper.insert(businessAccount);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        businessAccountMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改BusinessAccount
     * @param businessAccount
     */
    @Override
    public void update(BusinessAccount businessAccount){
        businessAccountMapper.updateByPrimaryKey(businessAccount);
    }

    /**
     * 根据ID查询BusinessAccount
     * @param id
     * @return
     */
    @Override
    public BusinessAccount findById(Integer id){
        return  businessAccountMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询BusinessAccount全部数据
     * @return
     */
    @Override
    public List<BusinessAccount> findAll() {
        return businessAccountMapper.selectAll();
    }

    /**
     * BusinessAccount条件查询
     * @param businessAccount
     * @return
     */
    @Override
    public List<BusinessAccount> findList(BusinessAccount businessAccount){
        //构建查询条件
        Example example = createExample(businessAccount);
        //根据构建的条件查询数据
        return businessAccountMapper.selectByExample(example);
    }

    /**
     * BusinessAccount条件+分页查询
     * @param businessAccount 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<BusinessAccount> findPage(BusinessAccount businessAccount, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(businessAccount);
        //执行搜索
        return new PageInfo<BusinessAccount>(businessAccountMapper.selectByExample(example));
    }

    /**
     * BusinessAccount分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<BusinessAccount> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<BusinessAccount>(businessAccountMapper.selectAll());
    }

    @Override
    public BusinessAccount login(BusinessAccount account) {
        BusinessAccount params = new BusinessAccount();
        params.setLoginName(account.getLoginName());
        params.setLoginPassword(account.getLoginPassword());
        params.setIsDelete(0);
        params.setBusinessStatus(0);
        List<BusinessAccount> businessAccounts = businessAccountMapper.select(params);
        if (CollectionUtils.isEmpty(businessAccounts)){
            return null;
        }
        return businessAccounts.get(0);
    }

    @Override
    public BusinessAccount queryBusinessAccount(BusinessAccount account){
        BusinessAccount params = new BusinessAccount();
        params.setLoginName(account.getLoginName());
        params.setCompanyMobile(account.getCompanyMobile());

        List<BusinessAccount> businessAccounts = businessAccountMapper.select(account);
        if(CollectionUtils.isEmpty(businessAccounts)){
            return null;
        }
        return businessAccounts.get(0);
    }


    /**
     * BusinessAccount构建查询对象
     * @param businessAccount
     * @return
     */
    public Example createExample(BusinessAccount businessAccount){
        Example example=new Example(BusinessAccount.class);
        Example.Criteria criteria = example.createCriteria();
        if(businessAccount!=null){
            // 自增主键
            if(!StringUtils.isEmpty(businessAccount.getId())){
                criteria.andEqualTo("id",businessAccount.getId());
            }
            // 登录名
            if(!StringUtils.isEmpty(businessAccount.getLoginName())){
                criteria.andEqualTo("loginName",businessAccount.getLoginName());
            }
            // 登录密码
            if(!StringUtils.isEmpty(businessAccount.getLoginPassword())){
                criteria.andEqualTo("loginPassword",businessAccount.getLoginPassword());
            }
            // 店铺名称
            if(!StringUtils.isEmpty(businessAccount.getShopName())){
                criteria.andEqualTo("shopName",businessAccount.getShopName());
            }
            // 公司名称
            if(!StringUtils.isEmpty(businessAccount.getCompanyName())){
                criteria.andEqualTo("companyName",businessAccount.getCompanyName());
            }
            // 公司电话
            if(!StringUtils.isEmpty(businessAccount.getCompanyMobile())){
                criteria.andEqualTo("companyMobile",businessAccount.getCompanyMobile());
            }
            // 公司详细地址
            if(!StringUtils.isEmpty(businessAccount.getCompanyAddress())){
                criteria.andEqualTo("companyAddress",businessAccount.getCompanyAddress());
            }
            // 联系人名字
            if(!StringUtils.isEmpty(businessAccount.getContactName())){
                criteria.andEqualTo("contactName",businessAccount.getContactName());
            }
            // 联系人微信
            if(!StringUtils.isEmpty(businessAccount.getContactWechat())){
                criteria.andEqualTo("contactWechat",businessAccount.getContactWechat());
            }
            // 联系人电话
            if(!StringUtils.isEmpty(businessAccount.getContactMobile())){
                criteria.andEqualTo("contactMobile",businessAccount.getContactMobile());
            }
            // 联系人邮箱
            if(!StringUtils.isEmpty(businessAccount.getContactEmail())){
                criteria.andEqualTo("contactEmail",businessAccount.getContactEmail());
            }
            // 营业执照号
            if(!StringUtils.isEmpty(businessAccount.getBusinessLicenseNo())){
                criteria.andEqualTo("businessLicenseNo",businessAccount.getBusinessLicenseNo());
            }
            // 税务登记号
            if(!StringUtils.isEmpty(businessAccount.getTaxRegistrationNo())){
                criteria.andEqualTo("taxRegistrationNo",businessAccount.getTaxRegistrationNo());
            }
            // 组织机构代码证
            if(!StringUtils.isEmpty(businessAccount.getOrganizationNo())){
                criteria.andEqualTo("organizationNo",businessAccount.getOrganizationNo());
            }
            // 法定代表人
            if(!StringUtils.isEmpty(businessAccount.getLegalRepresentative())){
                criteria.andEqualTo("legalRepresentative",businessAccount.getLegalRepresentative());
            }
            // 法定代表人身份证号
            if(!StringUtils.isEmpty(businessAccount.getLegalRepresentativeIdCard())){
                criteria.andEqualTo("legalRepresentativeIdCard",businessAccount.getLegalRepresentativeIdCard());
            }
            // 开户行名称
            if(!StringUtils.isEmpty(businessAccount.getOpenBankName())){
                criteria.andEqualTo("openBankName",businessAccount.getOpenBankName());
            }
            // 开户行支行名称
            if(!StringUtils.isEmpty(businessAccount.getOpenBankBranchName())){
                criteria.andEqualTo("openBankBranchName",businessAccount.getOpenBankBranchName());
            }
            // 开户行银行账号
            if(!StringUtils.isEmpty(businessAccount.getOpenBankAccount())){
                criteria.andEqualTo("openBankAccount",businessAccount.getOpenBankAccount());
            }
            // 商家状态：0正常 1 禁用
            if(!StringUtils.isEmpty(businessAccount.getBusinessStatus())){
                criteria.andEqualTo("businessStatus",businessAccount.getBusinessStatus());
            }
            // 是否删除：0正常 1禁用
            if(!StringUtils.isEmpty(businessAccount.getIsDelete())){
                criteria.andEqualTo("isDelete",businessAccount.getIsDelete());
            }
            // 创建人
            if(!StringUtils.isEmpty(businessAccount.getCreateBy())){
                criteria.andEqualTo("createBy",businessAccount.getCreateBy());
            }
            // 修改人
            if(!StringUtils.isEmpty(businessAccount.getUpdateBy())){
                criteria.andEqualTo("updateBy",businessAccount.getUpdateBy());
            }
            // 创建时间
            if(!StringUtils.isEmpty(businessAccount.getCreateTime())){
                criteria.andEqualTo("createTime",businessAccount.getCreateTime());
            }
            // 修改时间
            if(!StringUtils.isEmpty(businessAccount.getUpdateTime())){
                criteria.andEqualTo("updateTime",businessAccount.getUpdateTime());
            }
        }
        return example;
    }
}
