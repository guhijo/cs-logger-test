package com.cs.logger;

import com.cs.logger.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class CommandLineAppStartupRunner implements CommandLineRunner {

        @Autowired
        private EventService eventService;

        @Override
        public void run(String...args){
            eventService.initLogReading(args[0]);
        }
    }
}