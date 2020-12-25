package designpatterns.bridge;

/**
 * @author machenggong
 * @date 2020/12/24
 * @description
 */
public class XiaoMiBrand implements Brand{
    @Override
    public void open() {
        System.out.println("小米手机开机");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void close() {
        System.out.println("小米手机关机");
    }
}
