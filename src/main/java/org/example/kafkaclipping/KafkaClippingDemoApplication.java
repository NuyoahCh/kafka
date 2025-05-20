package org.example.kafkaclipping;

import org.example.kafkadecouple.KafkaDecoupleDemoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description: 消息队列解耦
 */
//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "org.example.kafkaclipping")
public class KafkaClippingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaClippingDemoApplication.class, args);
    }
}
