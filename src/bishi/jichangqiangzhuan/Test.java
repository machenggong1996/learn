package bishi.jichangqiangzhuan;

/**
 * @author machenggong
 * @date 2020/11/05
 */
public class Test {

    /**
     * 类型转化必须是有继承关系的 不然编译报错
     * 如果引用的类型与要转换的类型不一样 运行报错
     * @param args
     */
    public static void main(String[] args) {
        Anamial anamial = new Lion();
        Brid brid = new Brid();
        Lion lion = new Lion();
//        Object obj = new Integer(100);
//        String strVal = (String)obj;
        if(brid instanceof Anamial){
            lion = (Lion) anamial;
        }
    }

}
