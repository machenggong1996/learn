package designpatterns.bridge;

/**
 * @author machenggong
 * @date 2020/12/24
 * @description
 */
public class VivoBrand implements Brand{
    @Override
    public void open() {
        System.out.println("vivo手机开机");
    }

    @Override
    public void call() {
        System.out.println("vivo手机打电话");
    }

    @Override
    public void close() {
        System.out.println("vivo手机关机");
    }
}
