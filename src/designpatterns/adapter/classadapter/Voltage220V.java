package designpatterns.adapter.classadapter;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description 被适配的类
 */
public class Voltage220V {

    public int output220V(){
        int src = 220;
        System.out.println("电压="+src+"伏");
        return src;
    }

}
