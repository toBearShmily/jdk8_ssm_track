package reflect;

import com.shmily.model.User;
import jdk.internal.org.objectweb.asm.commons.Method;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/11.
 */
public class fsTest {
    public static void main(String arg[]){
        try {
            Class<?> cls = Class.forName("com.shmily.model.User");

            /*DefaultMapEntry a = new DefaultMapEntry("aaa", "111");
            a.setValue("222");
            System.out.println(a.getValue()+"-------------------"+a.getKey());*/


            BeanMap beanMap = BeanMap.create(new User());
            User user = new User(1,"wxb","a",1,0,"asdadsa.jpg",0);
            beanMap.setBean(user);
            System.out.println(beanMap.get("id"));
            System.out.println(beanMap.keySet());
            System.out.println(beanMap.values());




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
