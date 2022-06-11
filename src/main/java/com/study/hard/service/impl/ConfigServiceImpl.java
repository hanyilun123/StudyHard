package com.study.hard.service.impl;

import com.study.hard.mapper.ConfigMapper;
import com.study.hard.model.NetConfig;
import com.study.hard.model.User;
import com.study.hard.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<NetConfig> getConfig() {
        return configMapper.getConfig();
    }
}
