package designpatterns.builder.improve;

/**
 * @author machenggong
 * @date 2020/12/22
 * @description 抽象建造者
 */
public abstract class HouseBuilder {

    protected House house = new House();
    //将建造的流程写好
    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void roofed();

    //房子建好 返回
    public House buildHouse(){
        return house;
    }

}
