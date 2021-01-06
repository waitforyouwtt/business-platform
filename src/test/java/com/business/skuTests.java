package com.business;

import com.business.utils.MapStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
class skuTests extends BusinessPlatformApplicationTests{

    @Test
    public void stringToMapTest(){
        Map<String,String> map = new HashMap<>();
        map.put("No","121");
        map.put("address","河南");

        log.info("得到的结果:{}", MapStringUtils.mapToString(map));

        String no = "No=121&address=河南";
        log.info("得到的结果:{}",MapStringUtils.json2map(no));
    }

    @Test
    public void subTest(){
        //交通银行
        String bcm = "6222620110037200632";
        //招商银行
        String cmb1 = "6214832167974372";
        String cmb2 = "6214832192352149";
        log.info("交通:{}",bcm.substring(15,19));
        log.info("招商：{}",cmb1.substring(12,16));
    }
}
