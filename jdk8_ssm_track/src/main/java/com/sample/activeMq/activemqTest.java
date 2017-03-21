package com.sample.activeMq;

/**
 * Created by Administrator on 2017/2/14.
 */
public class activemqTest {
    public static void main(String[] arg){
        Producer producer = new Producer();
        try {
            producer.sendMessage("text");
            //producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* public static void main(String[] arg){

        *//*String str = "www.yiibai.com";
        String bb = ".yiibai";
        String aa = ".com";
        boolean end = str.endsWith(bb);
        boolean star = str.endsWith(aa);
        System.out.println(end+"-------------------------"+star);*//*
        M m = new N();
        m.test();
    }*/
}

class M {
    public static void test(){
        System.out.println("M");
    }
}


class N extends M{
    public static void test(){
        System.out.println("N");
    }
}