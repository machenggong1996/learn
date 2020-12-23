package designpatterns.builder.improve;

/**
 * @author machenggong
 * @date 2020/12/22
 * @description
 */
public class CommonHouse extends HouseBuilder{


    @Override
    public void buildBasic() {
        System.out.println("普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通房子砌墙10米");
    }

    @Override
    public void roofed() {
        System.out.println("普通房子盖房顶");
    }
}
