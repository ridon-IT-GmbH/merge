package com.ridonit.alm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.ridonit.alm"})
@EnableSwagger2
public class AlmMapperApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlmMapperApplication.class, args);
//
//        C2AWatcher watcher = new C2AWatcher();
//        try {
//            watcher.createThread(1000, "Hauke");
//        } finally {
//            if (watcher != null ) {
//                watcher.closeThreads();
//            }
//        }

    }

}
