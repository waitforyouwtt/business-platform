package com.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.business.constants.Constant;
import com.business.dao.ScheduleJobMapper;
import com.business.entity.ScheduleJob;
import com.business.job.ScheduleUtils;
import com.business.service.ScheduleJobService;
import com.business.service.SingleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-04
 */
@Slf4j
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    @Resource
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private SingleService singleService;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
        QueryWrapper<ScheduleJob> wrapper = new QueryWrapper<ScheduleJob>();

        List<ScheduleJob> scheduleJobList = scheduleJobMapper.selectList(wrapper);

        for(ScheduleJob scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        String beanName = (String)params.get("beanName");
        int page = Integer.parseInt(params.getOrDefault("pageNum", "1").toString());
        int pageSize = Integer.parseInt(params.getOrDefault("pageSize", "10").toString());
        PageHelper.startPage(page,pageSize);

        QueryWrapper<ScheduleJob> wrapper = new QueryWrapper<ScheduleJob>();
        wrapper.eq("bean_name",beanName);

        List<ScheduleJob> scheduleJobs = scheduleJobMapper.selectList(wrapper);
        PageInfo pageInfo = new PageInfo<>(scheduleJobs);
        return pageInfo;
    }

    @Override
    public ScheduleJob getScheduleJobByJobId(Long jobId) {
        return scheduleJobMapper.selectById(jobId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(Byte.parseByte(Constant.NORMAL+""));
        scheduleJobMapper.insert(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

        QueryWrapper<ScheduleJob> wrapper = new QueryWrapper<>();
        wrapper.eq("job_id",scheduleJob.getJobId());
        scheduleJobMapper.update(scheduleJob,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }

        //删除数据
        singleService.deleteBatchScheduleJob(jobIds);
    }

    @Override
    public int updateBatch(List<Long>  jobIds, int status){
        return singleService.updateBatchScheduleJob(jobIds,status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(List<Long> jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.run(scheduler, scheduleJobMapper.selectById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(List<Long> jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, Constant.PAUSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(List<Long> jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, Constant.NORMAL);
    }
}
