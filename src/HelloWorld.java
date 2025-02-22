import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class HelloWorld {
  public static void main(String[] args) {
//    while (true) {
//      example();
//    }
    char ch = 'b';
    char ch1 = '我';
    switch (ch) {
      case 'a':
        System.out.print("a");
        break;
      case 'b':
        System.out.print("ab");
      case 'c':
        System.out.print("c");
        break;
      default:
        System.out.print("d");
    }
    List<Integer> intList = new ArrayList<>();
    intList.add(5);
    intList.add(3);
    intList.add(1);
    intList.add(6);
    intList.add(0, 4);
    intList.remove(1);
  }

  public static void example(){
    ExecutorService executorPool = Executors.newFixedThreadPool(3);
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            2,
            2,
            0,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );
    executorService.getLargestPoolSize();
    List<CompletableFuture<Void>> fatherCFList = new ArrayList<>();
    // 任务1 -> 执行两次
    for (int i = 0; i < 2; i++) {
      int finalI = i;
      System.out.println("外层开始执行:" + i + Thread.currentThread().getName());
      CompletableFuture<Void> fatherCF = CompletableFuture.runAsync(() -> {
        List<CompletableFuture<Void>> childCFList = new ArrayList<>();
        System.out.println(Thread.currentThread().getName());
        // 任务2 -> 执行十次
        for (int j = 0; j < 10; j++) {
          int finalJ = j;
          CompletableFuture<Void> childCF = CompletableFuture.runAsync(() -> {
            try {
              System.out.println(String.format(Thread.currentThread().getName() + "外层执行第 %s 次, 内层执行第 %s 次, 执行的线程: %s, at : %s", finalI, finalJ, Thread.currentThread().getName(), LocalDateTime.now()));
              Thread.sleep(500L);
            } catch (InterruptedException e) {

            }
          }, executorService);
          //childCF.join();
          childCFList.add(childCF);
        }
        System.out.println("执行里层join");
        childCFList.forEach(CompletableFuture::join);
      }, executorService);
      fatherCFList.add(fatherCF);
    }
    System.out.println("执行外层join");
    fatherCFList.forEach(CompletableFuture::join);
    System.out.println("执行结束");
    executorService.shutdown();
  }
}