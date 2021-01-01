package designpatterns.iterator;

import java.util.Iterator;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public interface College {

    String getName();

    void addDepartment(String name,String desc);

    public Iterator createIterator();

}
