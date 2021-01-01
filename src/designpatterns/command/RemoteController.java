package designpatterns.command;

/**
 * @author machenggong
 * @date 2020/12/31
 * @description
 */
public class RemoteController {

    //开按钮的命令数组
    Command[] onCommands;
    Command[] offCommands;

    //撤销命令
    Command undoCommand;

    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setCommand(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    //按下打开命令
    public void onButtonWasPushed(int no) {
        onCommands[no].execute();
        undoCommand = onCommands[no];
    }

    //按下关闭命令
    public void offButtonWasPushed(int no) {
        offCommands[no].execute();
        undoCommand = offCommands[no];
    }

    //按下撤销按钮
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }

}
