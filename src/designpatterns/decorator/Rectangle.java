package designpatterns.decorator;

/**
 * Created by machenggong on 2020/3/5.
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
