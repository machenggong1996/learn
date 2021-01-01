package designpatterns.command;

/**
 * @author machenggong
 * @date 2020/12/31
 * @description 空命令 没有任何命令执行 用于初始化每个按钮 可以省掉空判断
 */
public class NoCommand implements Command{

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
