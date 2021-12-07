package com.study.hard.config.DoubleDataSourceConfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * 两种AOP配置方式
 * 一、通过切入点来动态配置数据库信息
 * 二、通过自定义注解 @DataSource 动态配置
 * */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    //1.通过切入点来动态配置数据库信息

    //在primary方法前执行
    /*@Before("execution(* com.study.hard.controller.DataSourceChangeController.primary(..))")
    public void setDataSource1() {
        log.info("切换media数据库");
        System.err.println("Primary业务");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Primary);
    }

    //在secondary方法前执行
    @Before("execution(* com.study.hard.controller.DataSourceChangeController.secondary(..))")
    public void setDataSource2() {
        log.info("切换config数据库");
        System.err.println("Secondary业务");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Secondary);
    }*/


    //分割线------------------------------------------------------------------------------------

    //2.通过自定义注解 @DataSource 动态配置
    /*通过使用aop拦截，获取注解的属性value的值。
    如果value的值并没有在我们DataBaseType里面，
    则使用我们默认的数据源，如果有的话，则切换为相应的数据源*/

    @Before("@annotation(dataSource)")//拦截我们的注解 @DataSource
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
        String value = dataSource.value();
        if (value.equals("primary")) {
            System.err.println("切换到----->业务数据");
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Primary);
        } else if (value.equals("secondary")) {
            System.err.println("切换到----->业务配置");
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Secondary);
        } else {
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Primary);//默认使用主数据库
        }
    }

    @After("@annotation(dataSource)") //清除数据源的配置
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        DataSourceType.clearDataBaseType();
    }

    //使用：修改我们的mapper，添加我们的自定义的@DataSouse注解
}
