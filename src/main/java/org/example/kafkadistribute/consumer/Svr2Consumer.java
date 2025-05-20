package org.example.kafkadistribute.consumer;

import java.util.Optional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.kafkadistribute.service.DistributeCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
@Component
public class Svr2Consumer {

    @Autowired
    private DistributeCountService distributeCountService;

    @KafkaListener(topics = "tp-mq-dispatch", groupId = "svr2", concurrency = "1",
            containerFactory = "kafkaManualAckListenerContainerFactory")
    public void consume(ConsumerRecord<?, ?> record, Acknowledgment ack,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional.ofNullable(record.value()).ifPresent(msg -> {
            System.out.println("svr2 收到Kafka消息! Topic:" + topic + ",Message:" + msg);
            try {
                distributeCountService.incrManyTimes(10000);
                ack.acknowledge();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
