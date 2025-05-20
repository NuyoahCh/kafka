package org.example.kafkadecouple.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.kafkadecouple.kafka.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
@Component
@Slf4j
public class KafkaConsumerService {

    @Autowired
    private CountService countService;

    @KafkaListener(topics = "tp-mq-decoupling", groupId = "TEST_GROUP")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String message = record.value();
        log.info("收到Kafka消息: {}", message);
        try {
            countService.incrManyTimes(10_000_000);
            ack.acknowledge();
            log.info("Kafka消费完成: {}", message);
        } catch (Exception e) {
            log.error("Kafka消费失败", e);
        }
    }
}

