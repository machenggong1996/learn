package designpatterns.template.soyamilk;

/**
 * @author machenggong
 * @date 2020/12/30
 * @description
 */
public class PeanutSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        System.out.println("第二步 加入花生");
    }
}
