package designpatterns.factory.simplefactory.pizza;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description
 */
public abstract class Pizza {

    protected String name;

    /**
     * 准备
     */
    public abstract void prepare();

    /**
     * 烘焙
     */
    public void bake() {
        System.out.println(name + " baking;");
    }

    /**
     * 切
     */
    public void cut() {
        System.out.println(name + " cutting;");
    }

    /**
     * 装盒打包
     */
    public void box() {
        System.out.println(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }
}
