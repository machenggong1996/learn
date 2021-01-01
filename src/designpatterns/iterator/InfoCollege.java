package designpatterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class InfoCollege implements College{

    private final List<Department> departmentList;

    public InfoCollege(){
        departmentList = new ArrayList<>();
        departmentList.add(new Department("信息安全专业","信息安全专业"));
        departmentList.add(new Department("网络安全专业","网络安全专业"));
    }

    @Override
    public String getName() {
        return "信息工程";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departmentList.add(new Department(name,desc));
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
