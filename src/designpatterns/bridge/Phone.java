package designpatterns.bridge;

/**
 * @author machenggong
 * @date 2020/12/24
 * @description 桥接类
 */
public abstract class Phone {

    /**
     * 品牌
     */
    private final Brand brand;

    public Phone(Brand brand){
        this.brand = brand;
    }

    protected void open(){
        this.brand.open();
    }

    protected void call(){
        this.brand.call();
    }

    protected void close(){
        this.brand.close();
    }
}
