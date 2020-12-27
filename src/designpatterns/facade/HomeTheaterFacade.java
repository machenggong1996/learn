package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description 影院外观
 */
public class HomeTheaterFacade {

    //定义子系统的各个对象
    private TheaterLight theaterLight;

    private PopCorn popCorn;

    private Stereo stereo;

    private Screen screen;

    private Projector projector;

    private DVDPlayer dvdPlayer;

    public HomeTheaterFacade() {
        this.theaterLight = TheaterLight.getInstance();
        this.popCorn = PopCorn.getInstance();
        this.stereo = Stereo.getInstance();
        this.screen = Screen.getInstance();
        this.projector = Projector.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
    }

    public void ready(){
        popCorn.on();
        popCorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }

    public void play(){
        dvdPlayer.play();
    }

    public void pause(){
        dvdPlayer.pause();
    }

    public void end(){
        popCorn.off();
        theaterLight.bright();
        screen.up();
        projector.off();
        stereo.off();
        dvdPlayer.off();
    }

}
