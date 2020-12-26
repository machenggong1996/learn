package designpatterns.composite;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description 系
 */
public class Department extends OrganizationComponent {

    public Department(String name, String description) {
        super(name, description);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    protected void print() {
        System.out.println("--------" + getName() + "--------");
    }

}
