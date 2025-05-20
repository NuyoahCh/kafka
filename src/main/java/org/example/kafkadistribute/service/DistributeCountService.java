package org.example.kafkadistribute.service;

import org.springframework.stereotype.Service;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
@Service
public class DistributeCountService {

    public void incrManyTimes(int n) {
        for (int i = 0; i < n; i++) {
            // 模拟一些耗时处理
        }
        System.out.println("incrManyTimes 执行完成");
    }
}
