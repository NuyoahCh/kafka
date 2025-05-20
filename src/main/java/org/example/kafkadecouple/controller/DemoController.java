package org.example.kafkadecouple.controller;

import java.util.HashMap;
import java.util.Map;
import org.example.kafkadecouple.kafka.CountService;
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
public class DemoController {

    @Autowired
    private CountService countService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/decoupling")
    public ResponseEntity<?> directInvoke(@RequestBody Map<String, Integer> body) {
        countService.incrManyTimes(10_000_000);

        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("message", "ok");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/decoupling_with_mq")
    public ResponseEntity<?> mqInvoke(@RequestBody Map<String, Integer> body) {
        kafkaTemplate.send("tp-mq-decoupling", "trigger");

        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("message", "ok");

        return ResponseEntity.ok(response);
    }
}

