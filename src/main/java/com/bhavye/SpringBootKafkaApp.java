package com.bhavye;

import com.bhavye.models.MyMessage;
import com.bhavye.services.MyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaApp implements CommandLineRunner
{
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaApp.class, args);
    }

    @Autowired
    private MyProducer myProducer;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 5; i++) {
            MyMessage msg = new MyMessage("Message-->" + i, i * 10);
            myProducer.send(msg);
        }
    }
}
