package designpatterns.observer.v20210102;

/**
 * 观察者接口
 */
public interface Observer {

    void update(float temperature,float pressure,float humidity);

}
