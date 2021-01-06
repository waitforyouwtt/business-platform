package com.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.business.dao.BusinessAccountBankInfoMapper;
import com.business.dao.ScheduleJobMapper;
import com.business.entity.BusinessAccountBankInfo;
import com.business.entity.ScheduleJob;
import com.business.service.SingleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@Slf4j
@Service
public class SingleServiceImpl implements SingleService {

    @Resource
    private ScheduleJobMapper scheduleJobMapper;

    @Resource
    BusinessAccountBankInfoMapper businessAccountBankInfoMapper;


    @Override
    public void deleteBatchScheduleJob(List<Long> jobIds) {
        scheduleJobMapper.deleteBatchIds(jobIds);
    }

    @Override
    public int updateBatchScheduleJob(List<Long> jobIds, int status) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setStatus((byte) status);

        QueryWrapper<ScheduleJob> wrapper = new QueryWrapper<>();
        wrapper.in("job_id",jobIds);
        return scheduleJobMapper.update(scheduleJob,wrapper);
    }

    @Override
    public BusinessAccountBankInfo queryBusinessAccountBankInfo(String userId){
        QueryWrapper<BusinessAccountBankInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return businessAccountBankInfoMapper.selectOne(wrapper);
    }
}
