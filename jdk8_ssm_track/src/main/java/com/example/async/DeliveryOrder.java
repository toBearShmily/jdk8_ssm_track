package com.example.async;

/**
 * 提货单
 * Created by wuxubiao on 2017/5/3.
 */
public class DeliveryOrder implements Cake{
    private CakeBaker cakeBaker = null;

    private boolean ready = false;

    public synchronized void setCakeBaker(CakeBaker cakeBaker) {
        if (ready) {
            return; // 防止setCakeBaker被调用两次以上。
        }
        this.cakeBaker = cakeBaker;
        this.ready = true;
        notifyAll();
    }

    public synchronized String getCake() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return cakeBaker.getCake();
    }
}
