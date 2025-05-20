package org.example.kafkaclipping.consumer;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.kafkaclipping.kafka.ClippingCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
@Service
public class KafkaClippingConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaClippingConsumerService.class);

    @Autowired
    private ClippingCountService clippingCountService;

    @KafkaListener(
            topics = "tp-mq-peakclipping",
            groupId = "TEST_GROUP",
            concurrency = "1",
            containerFactory = "kafkaManualAckListenerContainerFactory"
    )
    public void peakClipping(ConsumerRecord<?, ?> record, Acknowledgment ack,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            System.out.println("收到Kafka消息! Topic:" + topic + ", Message:" + msg);
            try {
                clippingCountService.flowArrived();
                TimeUnit.SECONDS.sleep(1);  // 控制消费速率
                ack.acknowledge();
                log.info("Kafka消费成功! Topic:{}, Message:{}", topic, msg);
            } catch (Exception e) {
                log.error("Kafka消费失败！Topic:{}, Message:{}", topic, msg, e);
            }
        }
    }
}
