package designpatterns.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class ObjectStructure {

    private List<Person> personList = new LinkedList<>();

    public void attach(Person person){
        personList.add(person);
    }

    public void detach(Person person){
        personList.remove(person);
    }

    public void display(Action action){
        for (Person p : personList){
            p.accept(action);
        }
    }
}
