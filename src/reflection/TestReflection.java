package reflection;

import java.lang.reflect.Constructor;

/**
 * Created by machenggong on 2020/1/2.
 */
public class TestReflection {

    public static void main(String[] args) {
//      第一、通过Class.forName方式
        Class clazz1 = null;
        try {
            clazz1 = Class.forName("reflection.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//      第二、通过对象实例方法获取对象
        Class clazz2 = User.class;

//      第三、通过Object类的getClass方法
        User user = new User();
        Class clazz3 = user.getClass();

        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz3);

        User user1 = null;
        try {
            user1 =(User) clazz1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        user1.setName("终结者");
        user1.setAge(1500);
        System.out.println("user1:"+user1.toString());


        User user2 = null;
        try {
            // 获取构造函数
            Constructor constroctor = clazz2.getConstructor(String.class,Integer.class);
            // 通过构造器对象的newInstance方法进行对象的初始化
            user2 = (User) constroctor.newInstance("龙哥",29);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("user2:"+user2.toString());
    }

}
