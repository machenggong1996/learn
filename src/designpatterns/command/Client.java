package designpatterns.command;

/**
 * @author machenggong
 * @date 2020/12/31
 * @description
 */
public class Client {

    public static void main(String[] args) {
        LightReceiver lightReceiver = new LightReceiver();
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        //需要一个遥控器
        RemoteController remoteController = new RemoteController();
        remoteController.setCommand(0, lightOnCommand, lightOffCommand);
        System.out.println("----------"+"按下灯开按钮"+"-------");
        remoteController.onButtonWasPushed(0);
        System.out.println("----------"+"按下灯关按钮"+"-------");
        remoteController.offButtonWasPushed(0);
        System.out.println("----------"+"按下撤销按钮"+"-------");
        remoteController.undoButtonWasPushed();
    }

}
