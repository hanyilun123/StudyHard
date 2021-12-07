package com.study.hard.controller;

import com.study.hard.config.RedisConfig.RedisUtils;
import com.study.hard.model.User;
import com.study.hard.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Autowired
    private MediaService testService;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/saveToRedis")
    public Object saveTest() {
        if(redisUtils.exists("hanyilun")){
            System.out.println("从缓存查询");
            Object hanyilun = redisUtils.get("hanyilun");
            return hanyilun;
        }
        List<User> user = testService.getList();
        if (user.size() > 0) {
            System.out.println("从数据库查询");
            User user1 = user.get(0);
            redisUtils.set(user1.getName(), user1);
        }
        return user;
    }
}
