package designpatterns.builder.improve;

/**
 * @author machenggong
 * @date 2020/12/22
 * @description
 */
public class Client {

    public static void main(String[] args) {
        //普通盖房子
        CommonHouse house = new CommonHouse();
        //盖房子的指挥者
        HouseDirector houseDirector = new HouseDirector(house);
        //盖房子
        houseDirector.constructHouse();

        HighBuilding highBuilding = new HighBuilding();
        HouseDirector houseDirector1 = new HouseDirector(highBuilding);
        houseDirector1.constructHouse();
    }

}
