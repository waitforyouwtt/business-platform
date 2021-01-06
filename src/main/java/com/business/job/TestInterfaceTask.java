package com.business.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@Component
@Slf4j
public class TestInterfaceTask {

    public void consoleLog(){
        log.info("通过测试接口 来控制定时任务");
    }
}
