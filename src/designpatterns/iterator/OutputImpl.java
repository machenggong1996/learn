package designpatterns.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class OutputImpl {


    private final List<College> collegeList;

    public OutputImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    public void printCollege() {
        Iterator<College> iterator = collegeList.iterator();
        while (iterator.hasNext()) {
            College college = iterator.next();
            System.out.println("-----------" + college.getName() + "----------");
            printDepartment(college.createIterator());
        }

    }

    public void printDepartment(Iterator iterator) {
        while (iterator.hasNext()) {
            Department d = (Department) iterator.next();
            System.out.println(d.getName());
        }
    }
}
