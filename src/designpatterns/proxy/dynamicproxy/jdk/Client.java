package designpatterns.proxy.dynamicproxy.jdk;

/**
 * @author machenggong
 * @date 2020/12/28
 * @description
 */
public class Client {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new TeacherDao());
        ITeacherDao teacherDao = (ITeacherDao) proxyFactory.getProxyInstance();
        teacherDao.teach();
    }

}
