package com.sapling;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sapling")
@ComponentScan(basePackages = {"com.core","com.sapling","org.n3r.idworker","com.brave.authority"})
public class PotalStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(PotalStartApplication.class, args);
    }

    /*解决hibernate  lazy加载启动后报错的问题   */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

}
