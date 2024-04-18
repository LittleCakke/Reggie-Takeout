package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author GRS
 * @since 2024/4/18 下午12:47
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.itheima.reggie.mapper")
public class MainApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MainApplication.class, args);
        log.info("项目启动成功");
    }
}