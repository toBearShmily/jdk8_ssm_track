package com.shmily.support.weixin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密
 * Created by Administrator on 2017/3/25.
 */

public class SecurityKit {
    public static String sha1(String str){
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            for(byte b : digest){
                //转换为16进制(字节数组转换为 十六进制 数)
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
