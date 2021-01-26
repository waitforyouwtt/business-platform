package com.business.service;

import com.business.entity.ScheduleJob;
import com.business.vo.RequestScheduleJob;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-04
 */
public interface ScheduleJobService {

    /**
     * 保存定时任务
     */
    void save(ScheduleJob scheduleJob);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(List<Long> jobIds);

    /**
     * 更新定时任务
     */
    void update(ScheduleJob scheduleJob);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(List<Long> jobIds, int status);

    /**
     * 立即执行
     */
    void run(List<Long> jobIds);

    /**
     * 暂停运行
     */
    void pause(List<Long> jobIds);

    /**
     * 恢复运行
     */
    void resume(List<Long> jobIds);

    PageInfo queryScheduleJobPage(RequestScheduleJob vo);

    ScheduleJob getScheduleJobByJobId(Long jobId);

    /**
     * 获取最大的jobId
     * @return
     */
    Long maxJobId();
}
