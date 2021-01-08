package innerclass.member;

class Outer {
    private String name = "test";
    public static int age = 20;

    class Inner {
        //成员内部类变量不允许是static的
        public int num = 10;

        public void fun() {
            System.out.println(name);
            System.out.println(age);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer.Inner in = new Outer().new Inner();
        in.fun();
    }
}