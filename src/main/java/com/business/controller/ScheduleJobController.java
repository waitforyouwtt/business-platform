package com.business.controller;

import com.business.entity.ScheduleJob;
import com.business.result.Result;
import com.business.service.ScheduleJobLogService;
import com.business.service.ScheduleJobService;
import com.business.utils.CronUtils;
import com.business.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/job")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;


    /**
     * 添加定时任务
     *
     * @param scheduleJob
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.save(scheduleJob);
        return Result.success();
    }


    /**
     * 列表
     *
     * @param params
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        return Result.success(scheduleJobService.queryPage(params));
    }

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @PostMapping("/log/list")
    public Result logList(@RequestParam Map<String, Object> params) {
        return Result.success(scheduleJobLogService.queryPage(params));
    }


    /**
     * 更新
     *
     * @param scheduleJob
     * @return
     */

    @PostMapping("/update")
    public Result update(ScheduleJob scheduleJob) {
        scheduleJobService.update(scheduleJob);
        return Result.success(null);
    }

    /**
     * 更新
     *
     * @param scheduleJob
     * @return
     */

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(ScheduleJob scheduleJob) {
        if (Objects.nonNull(scheduleJob.getJobId())) {
            scheduleJobService.update(scheduleJob);
        } else {
            scheduleJobService.save(scheduleJob);
        }
        return Result.success(null);
    }

    /**
     * 批量更新
     *
     * @param ids
     * @param status
     * @return
     */

    @PostMapping("/updateBatch")
    public Result updateBatch(String ids, int status) {
        String[] idsString = ids.split(",");
        scheduleJobService.updateBatch(Stream.of(idsString).map(Long::parseLong).collect(Collectors.toList()), status);
        return Result.success(null);
    }


    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch")
    public Result deleteBatch(String ids) {
        String[] idsString = ids.split(",");
        scheduleJobService.deleteBatch(Stream.of(idsString).map(Long::parseLong).collect(Collectors.toList()));
        return Result.success(null);
    }


    /**
     * 启动定时器
     *
     * @param ids
     * @return
     */
    @PostMapping("/run")
    public Result run(String ids) {
        String[] idsString = ids.split(",");
        scheduleJobService.run(Stream.of(idsString).map(Long::parseLong).collect(Collectors.toList()));
        return Result.success(null);
    }

    /**
     * 暂停定时器
     *
     * @param ids
     * @return
     */

    @PostMapping("/pause")
    public Result pause(String ids) {
        String[] idsString = ids.split(",");
        scheduleJobService.pause(Stream.of(idsString).map(Long::parseLong).collect(Collectors.toList()));
        return Result.success(null);
    }

    /**
     * 激活定时器
     *
     * @param ids
     * @return
     */

    @PostMapping("/resume")
    public Result resume(String ids) {
        String[] idsString = ids.split(",");
        scheduleJobService.resume(Stream.of(idsString).map(Long::parseLong).collect(Collectors.toList()));
        return Result.success(null);
    }


    @PostMapping("/preview")
    public Result preview(Long id, String cron, String dateStr) {
        ArrayList<Object> list = new ArrayList<>();
        if (StringUtils.isNotBlank(cron)) {
            String time = "";
            for (int i = 0; i < 10; i++) {
                if (StringUtils.isNotBlank(time)) {
                    dateStr = time;
                }
                time = addTimeList(dateStr, cron);
                list.add(time);
            }
        } else if (Objects.nonNull(id) && StringUtils.isBlank(cron)) {
            ScheduleJob scheduleJob = scheduleJobService.getScheduleJobByJobId(id);
            if (Objects.nonNull(scheduleJob)) {
                String cronExpression = scheduleJob.getCronExpression();
                String time = "";
                for (int i = 0; i < 10; i++) {
                    if (StringUtils.isNotBlank(time)) {
                        dateStr = time;
                    }
                    time = addTimeList(dateStr, cronExpression);
                    list.add(time);
                }
            }

        }
        return Result.success(list);

    }

    private String addTimeList(String dateStr, String cronExpression) {
        Date date = DateUtils.toDate(dateStr);
        Date next = CronUtils.next(cronExpression, date);
        String str = DateUtils.toStr(next);
        return str;

    }
}
