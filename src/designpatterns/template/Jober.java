package designpatterns.template;

/**
 * Created by mache on 2019/11/5.
 */
public class Jober {

    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }


    public int exam1() {

        System.out.println(name + "正在笔试...");

        return 88;
    }

    public int exam2() {

        System.out.println(name + "正在面试...");

        return 90;
    }


    public  void getJob(Company company){

        company.TemplateMethod(this);

    }

}
