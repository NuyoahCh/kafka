package org.example.kafkaclipping.kafka;

import org.springframework.stereotype.Service;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
@Service
public class ClippingCountService {
    public void flowArrived() {
        System.out.println("flow arrived!!!");
    }

    public void incrManyTimes(int count) {
        for (int i = 0; i < count; i++) {
            // 模拟某种重复逻辑
        }
        System.out.println("incrManyTimes finished, total: " + count);
    }
}
