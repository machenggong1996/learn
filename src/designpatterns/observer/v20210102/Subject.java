package designpatterns.observer.v20210102;

/**
 * @author machenggong
 * @date 2021/1/2
 * @description
 */
public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();

}
