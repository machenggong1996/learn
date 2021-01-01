package designpatterns.iterator;

import java.util.Iterator;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class ComputerCollege implements College {

    private final Department[] departments;
    int num = 0;

    public ComputerCollege() {
        departments = new Department[5];
        addDepartment("java专业", "java专业");
        addDepartment("PHP专业", "PHP专业");
        addDepartment("大数据专业", "大数据专业");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departments[num] = department;
        num++;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}
