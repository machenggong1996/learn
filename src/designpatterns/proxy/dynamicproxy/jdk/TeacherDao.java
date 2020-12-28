package designpatterns.proxy.dynamicproxy.jdk;

/**
 * @author machenggong
 * @date 2020/12/28
 * @description
 */
public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("老师授课中");
    }
}
