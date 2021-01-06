package com.business.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-04
 */
@Data
public class ScheduleJobLog implements Serializable {

    private Long logId;

    private Long jobId;

    private String beanName;

    private String methodName;

    private String params;

    private Byte status;

    private String error;

    private Integer times;

    private Date createTime;
}
