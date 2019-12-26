package designpatterns.singleton;

/**
 * Created by machenggong on 2019/12/26.
 */
public class SingleTonDoblueCheck {

    private SingleTonDoblueCheck() {
    }             //私有化构造方法

    private static volatile SingleTonDoblueCheck singleTon = null;

    public static SingleTonDoblueCheck getInstance() {
        //第一次校验
        if (singleTon == null) {
            synchronized (SingleTonDoblueCheck.class) {
                //第二次校验
                if (singleTon == null) {
                    singleTon = new SingleTonDoblueCheck();
                }
            }
        }
        return singleTon;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + SingleTonDoblueCheck.getInstance().hashCode());
                }
            }).start();
        }
    }

}
