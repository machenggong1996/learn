package designpatterns.adapter.objectadapter;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("对象适配器");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }

}
