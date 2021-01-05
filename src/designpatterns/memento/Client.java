package designpatterns.memento;

/**
 * @author machenggong
 * @date 2021/1/4
 * @description
 */
public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("状态#1 攻击力100");
        caretaker.add(originator.saveStateMemento());
        originator.setState("状态#2 攻击力80");
        caretaker.add(originator.saveStateMemento());
        originator.setState("状态#3 攻击力50");
        caretaker.add(originator.saveStateMemento());

        //恢复状态1
        System.out.println("当前状态="+originator.getState());
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("恢复到状态1="+originator.getState());
    }

}
