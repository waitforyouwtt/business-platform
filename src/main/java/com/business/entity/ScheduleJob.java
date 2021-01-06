package com.business.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-04
 */
@Data
public class ScheduleJob implements Serializable {

    private Long jobId;

    private String beanName;

    private String methodName;

    private String params;

    private String cronExpression;

    private Byte status;

    private String remark;

    private Date createTime;
}
