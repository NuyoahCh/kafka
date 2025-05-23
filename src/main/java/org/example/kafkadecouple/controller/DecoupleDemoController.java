package org.example.kafkadecouple.controller;

import org.example.kafkadecouple.dto.DecoupleIncrCountReq;
import org.example.kafkadecouple.kafka.DecoupleCountService;
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
public class DecoupleDemoController {

    @Autowired
    private DecoupleCountService countService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/decoupling")
    public ResponseEntity<?> directInvoke(@RequestBody DecoupleIncrCountReq data) {
        countService.incrManyTimes(data.getCount());
        return ResponseEntity.ok(createResponse(0, "ok"));
    }

    @PostMapping(value = "/decoupling_with_mq", consumes = "application/json; charset=utf-8")
    public ResponseEntity<?> decouplingWithMQ(@RequestBody DecoupleIncrCountReq data) {
        String msg = "trigger";  // 这里的消息可以根据业务改
        kafkaTemplate.send("tp-mq-decoupling", msg);
        return ResponseEntity.ok(createResponse(0, "ok"));
    }

    private static java.util.Map<String, Object> createResponse(int code, String message) {
        java.util.Map<String, Object> map = new java.util.HashMap<>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }
}