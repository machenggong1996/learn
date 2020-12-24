package designpatterns.adapter.objectadapter;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description
 */
public class VoltageAdapter implements IVoltage5V {

    //关联关系的聚合关系
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        super();
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int dstV = 0;
        if (voltage220V!=null){
            System.out.println("使用对象适配器进行转换");
            int src = voltage220V.output220V();
            dstV = src/44;
            System.out.println("适配完成");
        }

        return dstV;
    }
}
