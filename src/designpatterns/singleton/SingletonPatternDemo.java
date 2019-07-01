package designpatterns.singleton;

/**
 * Created by machenggong on 2019/6/13.
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {

        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingleObject();

        //获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();
        SingleObject object1 = SingleObject.getInstance();
        //显示消息
        object.showMessage();
        object1.showMessage();
        System.out.println(object.equals(object1));//true
    }

}
