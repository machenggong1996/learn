package innerclass.method;

class Outer {
    private int num = 5;

    public void dispaly(final int temp) {
        //方法内部类即嵌套在方法里面 不允许public、private、protected
        class Inner {
        }
    }
}

public class Test {
    public static void main(String[] args) {
    }
}