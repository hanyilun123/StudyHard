package com.study.hard.mapper;

import com.study.hard.config.DoubleDataSourceConfig.DataSource;
import com.study.hard.model.NetConfig;
import com.study.hard.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public  interface ConfigMapper {

    @DataSource("secondary")
    List<NetConfig> getConfig();
}
