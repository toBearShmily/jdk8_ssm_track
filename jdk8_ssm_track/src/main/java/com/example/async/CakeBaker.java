package com.example.async;

/**
 * 蛋糕师傅
 * Created by wuxubiao on 2017/5/3.
 */
public class CakeBaker implements Cake {

    private final String cake;

    public CakeBaker(int count, char c) {
        System.out.println("making cake(" + count + ", " + c + ") BEGIN");
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("making cake(" + count + ", " + c + ") END");
        this.cake = new String(buffer);
    }

    @Override
    public String getCake() {
        return cake;
    }
}
