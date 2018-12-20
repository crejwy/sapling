package com.brave.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.core","org.n3r.idworker","com.brave.authority"})
public class AuthorityStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityStartApplication.class, args);
    }
}
