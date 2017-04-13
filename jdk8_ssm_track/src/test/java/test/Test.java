package test;

import com.shmily.support.weixin.WeiXin;
import com.shmily.support.weixin.WeiXin_httpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Test {
    private static Logger log = LoggerFactory.getLogger(Test.class);

    /*public static void main(String[] args) {
        int count = 0;

        for (int i =0 ; i< 10 ; i++){
            count = count++;
        }
        System.out.println(count);
    }*/

    /*public static void main(String[] args) {
        String result = WeiXin_httpUtil.getAccessToken(WeiXin.ACCESS_URL);
        System.out.println(result);

    }*/

    public static void main(String[] args) {

        /*int result = (int) (1111 * 1079 * 0.68);
        System.out.println(1111 * 1079 * 0.68 +"--------------------"+result);*/
        int result = 0;
        try{
            result = 1/0;
        }catch(ArithmeticException e){
            log.error("hahaha:{}",result,e);
        }
    }

    public void test(){
            int begin = 0;
            int end = 100;
            int count = (end - begin) / 10;
            int current=begin;
            for (int i = 1; i <= count; i++) {
                System.out.println(current +"~"+(begin+(count*i)));
                current = begin+(count*i)+1;
            }
    }
}
