package designpatterns.template;

/**
 * Created by mache on 2019/11/5.
 */
public class TemplateTest {

    public static void main(String[] args) {

        Company aLi =new AiLBaba();

        Jober jober=new Jober();

        jober.setName("Yrion");

        jober.getJob(aLi);

    }

}
