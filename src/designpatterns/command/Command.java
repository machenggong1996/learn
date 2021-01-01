package designpatterns.command;

/**
 * @author machenggong
 * @date 2020/12/31
 * @description
 */
public interface Command {

    /**
     * 执行
     */
    void execute();

    /**
     * 撤销
     */
    void undo();

}
