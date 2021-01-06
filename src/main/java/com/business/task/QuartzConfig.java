package com.business.task;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-04
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail teatQuartzDetail(){

        return JobBuilder.newJob(TestQuartz.class).withIdentity("testQuartz").storeDurably().build();

    }

    @Bean
    public Trigger testQuartzTrigger(){

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())

                .withIdentity("testQuartz")
                .withSchedule(scheduleBuilder)
                .build();

    }

}
