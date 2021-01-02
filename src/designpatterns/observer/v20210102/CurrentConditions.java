package designpatterns.observer.v20210102;

/**
 * @author machenggong
 * @date 2021/1/2
 * @description
 */
public class CurrentConditions implements Observer {

    private float temperature;

    private float pressure;

    private float humidity;

    @Override
    public void update(float temperature, float pressure, float humidity) {
        System.out.println(this.getClass().getName() + "气温:" + temperature + "," + "气压:" + pressure + "," + "湿度:" + humidity);
        this.humidity = humidity;
        this.temperature = temperature;
        this.pressure = humidity;
    }


}
