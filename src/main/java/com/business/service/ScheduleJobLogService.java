package com.business.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
public interface ScheduleJobLogService {

    PageInfo queryPage(Map<String, Object> params);
}
