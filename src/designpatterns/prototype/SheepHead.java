package designpatterns.prototype;

import java.io.Serializable;

/**
 * @author machenggong
 * @date 2020/12/21
 * @description
 */
public class SheepHead implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SheepHead(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "SheepHead{" +
                "color='" + color + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
