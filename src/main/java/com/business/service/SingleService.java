package com.business.service;

import com.business.entity.BusinessAccountBankInfo;

import java.util.List;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
public interface SingleService {

    /**
     * 批量删除job
     * @param jobIds
     */
    void deleteBatchScheduleJob(List<Long> jobIds);

    /**
     * 批量更新
     * @param jobIds
     * @param status
     */
    int updateBatchScheduleJob(List<Long>  jobIds, int status);

    /**
     * 根据用户id 查询银行卡信息
     * @param userId
     * @return
     */
    BusinessAccountBankInfo queryBusinessAccountBankInfo(String userId);

}
