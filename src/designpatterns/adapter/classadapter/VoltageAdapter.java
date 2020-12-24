package designpatterns.adapter.classadapter;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V{

    @Override
    public int output5V() {
        int src = output220V();
        return src/44;
    }
}
