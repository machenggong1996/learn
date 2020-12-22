package designpatterns.prototype;

/**
 * @author machenggong
 * @date 2020/12/21
 * @description
 */
public class Client extends Object {

    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 11, "白色");
        sheep.head = new SheepHead("black");
        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        System.out.println(sheep1);
        System.out.println(sheep2);
        //反序列化深拷贝
        Sheep sheep3 = (Sheep) sheep.deepClone();
        System.out.println(sheep3);
    }

}
