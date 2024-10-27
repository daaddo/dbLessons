package it.cascella.dbsetup.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

//questa classe è uno scheduler che parte solo dopo che gli script son finiti
@Service
public class SchedulerService {
    private final CountDownLatch latch;

    @Autowired
    public SchedulerService(CountDownLatch latch) {
        this.latch = latch;
    }
    @Scheduled(fixedDelay = 1000*3, initialDelay = 0L)
    public void scheduledTask() throws InterruptedException {
        latch.await();
        System.out.println("SchedulerService.scheduledTask");
    }
}
