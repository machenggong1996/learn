package designpatterns.flyweight;

/**
 * @author machenggong
 * @date 2020/12/27
 * @description
 */
public class ConcreteWebSite extends WebSite {

    private String type = "";

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + " 使用者：" + user.getName());
    }

    public ConcreteWebSite(String type) {
        this.type = type;
    }
}
