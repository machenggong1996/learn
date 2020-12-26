package designpatterns.composite;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description
 */
public abstract class OrganizationComponent {

    private String name;

    private String description;

    protected void add(OrganizationComponent organizationComponent) {
        //默认实现 子类可以不实现
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent) {
        //默认实现 子类可以不实现
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 打印方法都需要实现
     */
    protected abstract void print();
}
