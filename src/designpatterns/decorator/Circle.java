package designpatterns.decorator;

/**
 * Created by machenggong on 2020/3/5.
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
