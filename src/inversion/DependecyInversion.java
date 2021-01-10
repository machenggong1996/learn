package inversion;

/**
 * @author machenggong
 * @date 2021/1/10
 * @description 依赖倒转原则 接口注入
 */
public class DependecyInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
        person.receive(new QQInfo());
    }

}

interface IReceiver{
    String getInfo();
}

//完成person接收信息的功能

class Email implements IReceiver{
    @Override
    public String getInfo() {
        return "电子邮件信息:hello world";
    }
}

class QQInfo implements IReceiver{

    @Override
    public String getInfo() {
        return "QQ消息";
    }
}

class Person {

    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }

}
