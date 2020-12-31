package designpatterns.template.soyamilk;

/**
 * @author machenggong
 * @date 2020/12/30
 * @description
 */
public class Client {

    static {
        int x = 5;
    }

    static String x,y;

    public static void main(String[] args) {
        System.out.println("制作红豆豆浆");
        RedBeanSoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();
        System.out.println("制作花生豆浆");
        PeanutSoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();
        System.out.println("制作纯豆浆");
        PureSoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
        int x = 20;
        int y = 5;
        System.out.println(x+y+""+(x+y)+y);

        String a = "11";
        String b = "11";
        System.out.println(-12%-5);
    }

}
