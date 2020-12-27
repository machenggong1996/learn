package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description
 */
public class Client {

    public static void main(String[] args) {
        HomeTheaterFacade facade = new HomeTheaterFacade();
        facade.ready();
        facade.play();
    }

}
