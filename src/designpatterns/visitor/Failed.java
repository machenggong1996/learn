package designpatterns.visitor;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class Failed extends Action{


    @Override
    public void getManResult(Man man) {
        System.out.println("男人说这个歌手很失败");
    }

    @Override
    public void getWomanResult(Woman man) {
        System.out.println("女人说这个歌手很失败");
    }
}
