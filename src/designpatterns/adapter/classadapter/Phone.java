package designpatterns.adapter.classadapter;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description 手机
 */
public class Phone {

    public void charging(IVoltage5V iVoltage5V){
        if(iVoltage5V.output5V()==5){
            System.out.println("可以充电");
        }
    }

}
