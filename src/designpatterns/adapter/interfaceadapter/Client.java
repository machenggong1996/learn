package designpatterns.adapter.interfaceadapter;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description
 */
public class Client {

    public static void main(String[] args) {
        AbsAdapter adapter = new AbsAdapter() {
            @Override
            public void m1() {
                System.out.println("使用m1方法");
            }
        };
        adapter.m1();
    }

}
