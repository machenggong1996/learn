package designpatterns.proxy.staticproxy;

/**
 * @author machenggong
 * @date 2020/12/28
 * @description 代理对象
 */
public class TeacherDaoProxy implements ITeacherDao{

    private ITeacherDao target;

    @Override
    public void teach() {
        System.out.println("代理开始");
        target.teach();
        System.out.println("代理结束");
    }

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }
}
