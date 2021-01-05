package designpatterns.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/1/4
 * @description
 */
public class Caretaker {

    private final List<Memento> mementos = new ArrayList<>();

    public void add(Memento memento) {
        mementos.add(memento);
    }

    public Memento get(int index) {
        return mementos.get(index);
    }

}
