package designpatterns.observer;

/**
 * Created by machenggong on 2019/6/12.
 */
public interface Observerable {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObserver();
}
