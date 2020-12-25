package designpatterns.bridge;

/**
 * @author machenggong
 * @date 2020/12/24
 * @description
 */
public class Client {

    public static void main(String[] args) {
        new FoldedPhone(new XiaoMiBrand()).call();
        new FoldedPhone(new VivoBrand()).call();
        new UpRightPhone(new XiaoMiBrand()).call();
        new UpRightPhone(new VivoBrand()).call();
    }

}
