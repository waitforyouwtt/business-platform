package com.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.business.entity.BusinessAccountBankInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@Mapper
public interface BusinessAccountBankInfoMapper extends BaseMapper<BusinessAccountBankInfo> {

    /**
     * 查询交易人信息
     * @param transactioner
     * @return
     */
    Map<String,String> queryTransactionAccouontUserId(@Param("transactioner") String transactioner);
}
