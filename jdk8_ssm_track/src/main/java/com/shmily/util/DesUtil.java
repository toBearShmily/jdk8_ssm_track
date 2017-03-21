package com.shmily.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;



@SuppressWarnings("restriction")
public class DesUtil {
    private final static String encoding = "utf-8";

    private final static String key = "cnmobile";

    private final static String secretKey = "cmcc_cnmobile_asiainfo_ocs";

    /**
     * 加密
     * @param plainText
     * @return
     */
    public static String encode(String plainText) {
        Key deskey = null;
        byte[] encryptData = null;
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(key.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            encryptData = cipher.doFinal(plainText.getBytes(encoding));
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return str2Hex(base64Encoder.encode(encryptData));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String str2Hex(String theStr) {
        int tmp;
        String tmpStr;
        byte[] bytes = theStr.getBytes();
        StringBuffer result = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            tmp = bytes[i];
            if (tmp < 0) {
                tmp += 256;
            }
            tmpStr = Integer.toHexString(tmp);
            if (tmpStr.length() == 1) {
                result.append('0');
            }
            result.append(tmpStr);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        //加密后存在特殊字符，如url传输需urlencode编码
        try {
            //http://211.138.20.171:8086/ngmmgw/channelapi/web/index/5a56394d6d446661433550426f7a477574786d495a55577a584d6e6c5a5a38716f69463738486c3953796776586678416d4358396d76627130696a6968744547587a475852584d3745592b740d0a546e506a4f6a4664326f506a684f2f756a6575354b3875655265353439367a5a4d626b727376746141786c47744e6145704b3562446b37536769464d594c4b584b317964726e464a476d39530d0a6b41762b5031316a5653635a4b36664e437945654931664f474f7035726a38697536316c68323472336958564b676736344b456a6a366d432b4a716b34664e754736696b7957454935644a680d0a66787a72476b513d
            //String address="phone=15257917152&source=571579030007&cc=371&pc=371&isshow=0&clientId=00055&fromOrgId=571&panelHeight=800&versionCode=21&serviceId=01&skipRobot=1&ext={\"order\":\"订单信息\"}";
            //String address="phone=15257917152&source=571579030007&cc=579&pc=571&isshow=0&clientId=00055&fromOrgId=571&serviceId=01";
            String aa= "phone=18264131101&source=571579030007&cc=579&pc=571&clientId=00055&fromOrgId=571";
            System.out.println(DesUtil.encode(aa));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
