package designpatterns.template;

/**
 * Created by mache on 2019/11/5.
 */
public abstract class Company {

    public static int score1 = 0;
    public static int score2 = 0;

    public void TemplateMethod(Jober jober) {

        if (Isreceive(jober)) {

            viewJober(jober);

            examination1(jober);

            examination2(jober);

            if (isAccess(jober)) {
                offer(jober);
            } else {
                System.out.println("很遗憾" + jober.getName() + "尚未通过本公司面试");
            }

        } else {
            System.out.println(jober.getName() + "的简历不合格");
        }

    }

    /**
     * 是否接受简历
     *
     * @return
     */
    public abstract boolean Isreceive(Jober jober);

    /**
     * 会见求职者
     *
     * @param jober
     */
    public void viewJober(Jober jober) {

        System.out.println("面试者会见求职者" + jober.getName());
    }

    /**
     * 笔试
     *
     * @param jober
     * @return
     */

    public int examination1(Jober jober) {

        score1 = jober.exam1();

        return score1;
    }

    /**
     * 面试
     *
     * @param jober
     * @return
     */
    public int examination2(Jober jober) {
        score2 = jober.exam2();
        return score2;
    }

    /**
     * 考虑求职者是否能进入公司
     *
     * @param jober
     * @return
     */
    public abstract boolean isAccess(Jober jober);

    /**
     * 发放offer
     *
     * @param jober
     */
    public void offer(Jober jober) {

        System.out.println("恭喜" + jober.getName() + "通过本公司的面试，现在发布offer");

    }

}