package designpatterns.proxy.staticproxy;

/**
 * @author machenggong
 * @date 2020/12/28
 * @description
 */
public class Client {

    public static void main(String[] args) {
        TeacherDaoProxy proxy = new TeacherDaoProxy(new TeacherDao());
        proxy.teach();
    }

}
