package redis_test.strategyAndFactory.factory;

/**
 * 抽象工厂类，，判定数据类型
 * Created by Administrator on 2017/3/22.
 */
public abstract class dataType {
    private String typeName;

    //public abstract void isTYpe();

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
