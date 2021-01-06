package com.business.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-04
 */
@EnableScheduling
@Component
public class TestQuartz2 {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

/*    @Scheduled(fixedRate = 1000)
    public void taskOne(){
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }*/

}
