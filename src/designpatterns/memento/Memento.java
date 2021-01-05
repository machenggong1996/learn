package designpatterns.memento;

/**
 * @author machenggong
 * @date 2021/1/4
 * @description
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
