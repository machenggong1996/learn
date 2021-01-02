package designpatterns.observer.v20210102;

/**
 * @author machenggong
 * @date 2021/1/2
 * @description
 */
public class Client {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditions currentConditions = new CurrentConditions();
        Baidu baidu = new Baidu();
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(baidu);
        System.out.println("通知各个注册者");
        weatherData.setData(10.0f,22.1f,12.5f);
    }

}
