package designpatterns.proxy.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author machenggong
 * @date 2020/12/28
 * @description
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // 1. loader 指定当前目标对象使用的类加载器，获取获取加载器的方法固定
        // 2. Class<?>[] interfaces 目标对象接口类型 使用泛型方式确定类型
        // 3. InvocationHandler h 事情处理 目标对象方法作为参数传入
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("jdk 代理开始");
                Object res = method.invoke(target, args);
                System.out.println("jdk 代理结束");
                return res;
            }
        });
    }
}
