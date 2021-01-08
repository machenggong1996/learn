package innerclass.anonymous;

//匿名内部类
//声明一个接口
interface MyInterface {
    //接口中方法没有方法体
    void test();
}

class Outer {
    private int num = 5;

    public void dispaly(int temp) {
        //匿名内部类，匿名的实现了MyInterface接口
        //隐藏的class声明
        new MyInterface() {
            @Override
            public void test() {
                System.out.println("匿名实现MyInterface接口");
                System.out.println(temp);
            }
        }.test();
    }
}

public class Test {
    public static void main(String[] args) {
        Outer out = new Outer();
        out.dispaly(3);
    }
}