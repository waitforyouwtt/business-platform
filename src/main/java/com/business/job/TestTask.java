package com.business.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-05
 */
@Component("testTask")
@Slf4j
public class TestTask {

    public void test(String params){
        log.info("我是带参数的test方法，正在被执行，参数为：" + params);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void test2(){
        log.info("我是不带参数的test2方法，正在被执行");
    }

    public void consoleLog(){
        log.info("打印日志哦，哈哈哈");
    }
}
