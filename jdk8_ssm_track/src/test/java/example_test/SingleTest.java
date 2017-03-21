package example_test;

/**
 * Created by Administrator on 2017/2/17.
 */
public class SingleTest {
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton == singleton1);
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
