package designpatterns.template.soyamilk;

/**
 * @author machenggong
 * @date 2020/12/30
 * @description
 */
public abstract class SoyaMilk {

    //模板方法 make final不允许覆盖
    public final void make(){
        select();
        if(customerWantCondiments()){
            addCondiments();
        }
        soak();
        beat();
    }

    void select(){
        System.out.println("第一步 选择好的黄豆");
    }

    //添加不同的配料 抽象方法 子类具体实现
    abstract void addCondiments();

    void soak(){
        System.out.println("第三步 黄豆开始浸泡");
    }

    void beat(){
        System.out.println("第四步 黄豆放到豆浆机打碎");
    }

    //钩子方法 决定是否需要添加配料
    boolean customerWantCondiments(){
        return true;
    }


}
