package designpatterns.builder.improve;

/**
 * @author machenggong
 * @date 2020/12/22
 * @description
 */
public class House {

    private String basic;

    private String wall;

    private String roofed;

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getRoofed() {
        return roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }
}
