package org.example.kafkadistribute.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
@RestController
@RequestMapping("/demo")
public class DistributeDemoController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(value = "/dispatch_with_mq", consumes = "application/json; charset=utf-8")
    public ResponseEntity<String> dispatchWithMQ(@RequestBody Map<String, Object> data) {
        String msg = "to the moon!";
        kafkaTemplate.send("tp-mq-dispatch", msg);
        return ResponseEntity.ok("Message Sent: " + msg);
    }

}
