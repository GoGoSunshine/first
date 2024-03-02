package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;

public class MybatisPlusConfig {
    //最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }


}
