package designpatterns.composite;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description
 */
public class Client {

    public static void main(String[] args) {
        OrganizationComponent university = new University("清华大学","中国顶级大学");
        OrganizationComponent computerCollege = new College("计算机学院","学计算机的");
        OrganizationComponent infoEngineerCollege = new College("信息学院","信息相关");
        computerCollege.add(new Department("软件工程","软工"));
        computerCollege.add(new Department("网络工程","网工"));
        computerCollege.add(new Department("计算机科学与技术","CS"));
        infoEngineerCollege.add(new Department("通信工程","通工"));
        infoEngineerCollege.add(new Department("信息工程","信工"));
        university.add(computerCollege);
        university.add(infoEngineerCollege);

        university.print();
    }

}
