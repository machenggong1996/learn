package designpatterns.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class InfoCollegeIterator implements Iterator {

    private final List<Department> departmentList;
    private int index = -1;

    public InfoCollegeIterator(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public boolean hasNext() {
        if(index>=departmentList.size()-1){
            return false;
        }
        index++;
        return true;
    }

    @Override
    public Object next() {

        return departmentList.get(index);
    }
}
