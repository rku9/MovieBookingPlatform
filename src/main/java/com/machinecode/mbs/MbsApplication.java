package com.machinecode.mbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbsApplication.class, args);
    }

}
