package com.study.hard.controller;

import com.study.hard.service.ConfigService;
import com.study.hard.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MediaController {
    @Autowired
    private MediaService testService;

    @Autowired
    private ConfigService configService;

    @GetMapping("/getConfig")
    public Object testController() {
        return testService.getList();
    }

    @GetMapping("/study")
    public Object study() {
        return configService.getConfig();
    }
}
