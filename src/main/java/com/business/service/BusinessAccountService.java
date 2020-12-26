package com.business.service;
import com.business.entity.BusinessAccount;
import com.github.pagehelper.PageInfo;
import java.util.List;
/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface BusinessAccountService {

    /***
     * 新增BusinessAccount
     * @param businessAccount
     */
    void add(BusinessAccount businessAccount);

    /***
     * 删除BusinessAccount
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改BusinessAccount数据
     * @param businessAccount
     */
    void update(BusinessAccount businessAccount);
    /**
     * 根据ID查询BusinessAccount
     * @param id
     * @return
     */
    BusinessAccount findById(Integer id);

    /***
     * 查询所有BusinessAccount
     * @return
     */
    List<BusinessAccount> findAll();

    /***
     * BusinessAccount多条件搜索方法
     * @param businessAccount
     * @return
     */
    List<BusinessAccount> findList(BusinessAccount businessAccount);

    /***
     * BusinessAccount多条件分页查询
     * @param businessAccount
     * @param page
     * @param size
     * @return
     */
    PageInfo<BusinessAccount> findPage(BusinessAccount businessAccount, int page, int size);

    /***
     * BusinessAccount分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<BusinessAccount> findPage(int page, int size);
}
