package bishi;

/**
 * Created by machenggong on 2020/2/18.
 */
final class Person {
    String name = "zs";
    //3. 此处不赋值会报错
    //final int age;
    final int age = 10;
}

public class Final {
    public static void main(String[] args) {
        //1. 基本数组类型为常量，无法修改
        final int i = 9;
        //i = 10;

        //2. 地址不能修改，但是对象本身的属性可以修改
        Person p = new Person();
        p.name = "lisi";
        final int[] arr = {1, 2, 3, 45};
        arr[3] = 999;
        //arr = new int[]{1,4,56,78};
    }
}
