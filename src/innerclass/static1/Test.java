package innerclass.static1;

class Outer {
    public static String name = "test";
    private static int age =20;

    static class Inner{
        //private String name;
        public void fun()
        {
            //name 要求外部类变量为静态
            System.out.println(name);
            System.out.println(age);
        }
    }
}
public class Test{
    public static void main(String [] args)
    {
        Outer.Inner in = new Outer.Inner();
        in.fun();
    }
}