package designpatterns.bridge;

/**
 * @author machenggong
 * @date 2020/12/24
 * @description
 */
public class UpRightPhone extends Phone{
    public UpRightPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open(){
        super.open();
        System.out.println("直立样式手机 开机");
    }

    @Override
    public void call(){
        super.call();
        System.out.println("直立样式手机 打电话");
    }

    @Override
    public void close(){
        super.close();
        System.out.println("直立样式手机 关机");
    }
}
