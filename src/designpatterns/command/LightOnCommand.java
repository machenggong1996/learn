package designpatterns.command;

/**
 * @author machenggong
 * @date 2020/12/31
 * @description
 */
public class LightOnCommand implements Command{

    LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
