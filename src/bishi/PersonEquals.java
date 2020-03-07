package bishi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machenggong on 2020/3/6.
 */
public class PersonEquals {

    private String name;

    public PersonEquals(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PersonEquals) {
            PersonEquals person = (PersonEquals) obj;
            return name.equalsIgnoreCase(person.getName().trim());
        }
        return false;
    }

    public static void main(String[] args) {
        PersonEquals p1 = new PersonEquals("张三");
        PersonEquals p2 = new PersonEquals("张三    ");
        List<PersonEquals> list = new ArrayList<PersonEquals>();
        list.add(p1);
        list.add(p2);
        System.out.println("是否包含张三：" + list.contains(p1));
        System.out.println("是否包含张三：" + list.contains(p2));
    }

}
