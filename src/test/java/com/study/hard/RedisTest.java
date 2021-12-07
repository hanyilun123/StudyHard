package com.study.hard;

import com.study.hard.config.RedisConfig.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    private final static String key = "myKey";

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void setRedisTemplate(){
        redisUtils.set(key, "test");
        boolean exists = redisUtils.exists(key);
//        boolean exists = new RedisUtils().exists(key);
        System.out.println(exists);
    }
}
