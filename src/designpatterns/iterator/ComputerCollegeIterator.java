package designpatterns.iterator;

import java.util.Iterator;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class ComputerCollegeIterator implements Iterator {

    private Department[] departments;

    private int position = 0;

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if (position > departments.length || departments[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position += 1;
        return department;
    }

    @Override
    public void remove() {

    }
}
