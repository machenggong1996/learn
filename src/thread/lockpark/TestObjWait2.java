package thread.lockpark;

import java.util.concurrent.locks.LockSupport;

public class TestObjWait2 {

    public static void main(String[] args)throws Exception {
    	
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=0;i<10;i++){
                    sum+=i;
                }
                System.out.println("------");
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+ " sum = "+sum);
            }
        },"ThreadA");
        
        threadA.start();
        
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+ " sleep over ");
        
        LockSupport.unpark(threadA);
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+ " over ");
    }
}