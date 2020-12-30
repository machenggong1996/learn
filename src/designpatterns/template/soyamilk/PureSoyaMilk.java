package designpatterns.template.soyamilk;

/**
 * @author machenggong (chenggong@shanshu.ai)
 * @date 2020/12/30
 * @description
 */
public class PureSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        //空实现
    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
