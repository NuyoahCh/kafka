package org.example.kafkaclipping.controller;

import org.example.kafkaclipping.dto.ClippingIncrCountReq;
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
public class ClippingDemoController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(value = "/peak_clipping_with_mq", consumes = "application/json; charset=utf-8")
    public ResponseEntity<String> peakClippingWithMQ(@RequestBody ClippingIncrCountReq data) {
        String msg = "coming!!!!!!!!!";
        kafkaTemplate.send("tp-mq-peakclipping", msg);
        return ResponseEntity.ok("ok");
    }
}
