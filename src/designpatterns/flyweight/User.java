package designpatterns.flyweight;

/**
 * @author machenggong
 * @date 2020/12/27
 * @description
 */
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
