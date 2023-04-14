package com.example.sample.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;

// fixedDelay parameter to @Scheduled : time btw executions
// fixedRate : how many execution its to happen

// @Component
@Slf4j
@EnableAsync
public class SampleScheduler {

    @Async
    @Scheduled( cron = "* * * * * *" )
    // initialDelay = 1000
    // fixedDelay = 1000
    // fixedRateString = "PT02S"
    // cron stars : seconds, minutes, hours, day of month, month, day of week
    public void scheduler() throws InterruptedException {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = current.format(format);
        log.info("Scheduler time : " + formattedDateTime);

        // this will cause log print to delay 1 more second, new time 2 seconds
        Thread.sleep(1000);
    }

}
