package org.example.kafkadecouple.kafka;

import org.springframework.stereotype.Service;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
@Service
public class DecoupleCountService {
    public void incrManyTimes(int n) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            x++;
        }
    }
}

