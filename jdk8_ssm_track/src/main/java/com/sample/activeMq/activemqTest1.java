package com.sample.activeMq;

/**
 * Created by Administrator on 2017/2/14.
 */
public class activemqTest1 {
    public static void main(String[] arg){
        Receiver receiver = new Receiver();
        try {
            receiver.receiveMessage();
            //receiver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
