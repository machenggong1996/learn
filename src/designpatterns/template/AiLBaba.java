package designpatterns.template;

/**
 * Created by mache on 2019/11/5.
 */
public class AiLBaba extends Company {


    @Override
    public boolean Isreceive(Jober jober) {

        if ("Yrion".equals(jober.getName())) {
            System.out.println(jober.getName() + "通过本公司简历筛选");
            return true;
        } else return false;
    }

    @Override
    public boolean isAccess(Jober jober) {
        if (score1 + score2 > 150)
            return true;
        return false;
    }

}
