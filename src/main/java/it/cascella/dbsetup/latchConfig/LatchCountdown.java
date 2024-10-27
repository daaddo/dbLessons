package it.cascella.dbsetup.latchConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.CountDownLatch;

@Configuration
public class LatchCountdown {

    @Profile("production")
    @Bean
       public CountDownLatch latch(){
           return new CountDownLatch(2);
    }

    @Bean
    @Profile("test")
    public CountDownLatch testLatch(){
        return new CountDownLatch(3);
    }
}
