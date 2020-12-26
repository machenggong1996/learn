package designpatterns.decorator.starbucks;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public abstract class Drink {

    public String description;

    private float price = 0.0f;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float cost();
}
