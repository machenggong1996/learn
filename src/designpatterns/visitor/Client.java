package designpatterns.visitor;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());
        //成功
        Success success = new Success();
        objectStructure.display(success);
        Wait wait = new Wait();
        objectStructure.display(wait);
    }

}
