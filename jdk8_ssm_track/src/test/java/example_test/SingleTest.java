package example_test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/2/17.
 */
public class SingleTest {
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        /*Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton == singleton1);*/

        Class<?> classType = Singleton.class;
        try {

            Constructor constructor = classType.getDeclaredConstructor(new Class[]{});
            constructor.setAccessible(true);
            Singleton obj = (Singleton)constructor.newInstance(new Object[]{});

            System.out.println(singleton == obj);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

class Singleton{
    private static Singleton singleton;
    private Singleton(){}

    public static Singleton getInstance(){
        if(null == singleton){
            singleton = new Singleton();
        }
        return singleton;
    }
}
