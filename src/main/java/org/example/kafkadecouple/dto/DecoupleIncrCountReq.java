package org.example.kafkadecouple.dto;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
public class DecoupleIncrCountReq {
    private int count;

    public DecoupleIncrCountReq() {}

    public DecoupleIncrCountReq(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
