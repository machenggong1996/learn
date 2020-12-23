package designpatterns.builder.improve;

/**
 * @author machenggong
 * @date 2020/12/22
 * @description
 */
public class HighBuilding extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println("高楼打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼砌墙10米");
    }

    @Override
    public void roofed() {
        System.out.println("高楼透明屋顶");
    }
}
