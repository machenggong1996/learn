package designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description 大学
 */
public class University extends OrganizationComponent {

    private final List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String description) {
        super(name, description);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
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
        System.out.println("---------" + getName() + "------------");
        for (OrganizationComponent component : organizationComponents) {
            component.print();
        }
    }
}
