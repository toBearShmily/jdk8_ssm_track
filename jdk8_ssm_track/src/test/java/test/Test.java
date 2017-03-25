package test;

import com.shmily.support.weixin.WeiXin;
import com.shmily.support.weixin.WeiXin_httpUtil;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Test {

    /*public static void main(String[] args) {
        int count = 0;

        for (int i =0 ; i< 10 ; i++){
            count = count++;
        }
        System.out.println(count);
    }*/

    @org.junit.Test
    public static void main(String[] args) {
        String result = WeiXin_httpUtil.getAccessToken(WeiXin.ACCESS_URL);
        System.out.println(result);

    }
}
