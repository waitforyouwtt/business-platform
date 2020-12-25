package com.business;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class skuTests extends BusinessPlatformApplicationTests{

    @Test
    public void test(){
        log.info("hello");
    }
}
