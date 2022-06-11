package com.study.hard.controller;

import com.study.hard.service.ConfigService;
import com.study.hard.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 双数据源切换测试
 * */

@RestController
@RequestMapping("/dataSource")
public class DataSourceChangeController {
    @Autowired
    private MediaService testService;

    @Autowired
    private ConfigService configService;

    @GetMapping("/secondary")
    public Object secondary() {
        return configService.getConfig();
    }

    @GetMapping("/primary")
    public Object primary() {
        return testService.getList();
    }
}
