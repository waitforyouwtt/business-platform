package com.business.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.business.dao.BusinessAccountMapper;
import com.business.entity.BusinessAccount;
import com.business.service.BusinessAccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        businessAccountMapper.deleteById(id);
    }

    /**
     * 修改BusinessAccount
     * @param businessAccount
     */
    @Override
    public void update(BusinessAccount businessAccount){
        QueryWrapper<BusinessAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("id",businessAccount.getId());
        businessAccountMapper.update(businessAccount,wrapper);
    }

    /**
     * 根据ID查询BusinessAccount
     * @param id
     * @return
     */
    @Override
    public BusinessAccount findById(Integer id){
        return  businessAccountMapper.selectById(id);
    }

    /**
     * 查询BusinessAccount全部数据
     * @return
     */
    @Override
    public List<BusinessAccount> findAll() {
        QueryWrapper<BusinessAccount> wrapper = new QueryWrapper<>();
        return businessAccountMapper.selectList(wrapper);
    }

    /**
     * BusinessAccount条件查询
     * @param businessAccount
     * @return
     */
    @Override
    public List<BusinessAccount> findList(BusinessAccount businessAccount){
        //构建查询条件
        QueryWrapper<BusinessAccount> wrapper = new QueryWrapper<>();
        //根据构建的条件查询数据
        return businessAccountMapper.selectList(wrapper);
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
        QueryWrapper<BusinessAccount> wrapper = new QueryWrapper<>();
        //执行搜索
        return new PageInfo<BusinessAccount>(businessAccountMapper.selectList(wrapper));
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
        QueryWrapper<BusinessAccount> wrapper = new QueryWrapper<>();

        //分页查询
        return new PageInfo<BusinessAccount>(businessAccountMapper.selectList(wrapper));
    }

    @Override
    public BusinessAccount login(BusinessAccount account) {
        QueryWrapper<BusinessAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",account.getLoginName());
        wrapper.eq("login_password",account.getLoginPassword());
        wrapper.eq("is_delete",0);
        wrapper.eq("business_status",0);

        return businessAccountMapper.selectOne(wrapper);
    }

    @Override
    public BusinessAccount queryBusinessAccount(BusinessAccount account){
        QueryWrapper<BusinessAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",account.getLoginName());
        wrapper.eq("company_mobile",account.getCompanyMobile());

        return businessAccountMapper.selectOne(wrapper);
    }
}
