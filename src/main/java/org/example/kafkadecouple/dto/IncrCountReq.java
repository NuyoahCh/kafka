package org.example.kafkadecouple.dto;

/**
 * @program: kafka
 * @author: NuyoahCh
 * @create: 2025-05-20
 * @description:
 */
public class IncrCountReq {
    private int count;

    public IncrCountReq() {}

    public IncrCountReq(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
