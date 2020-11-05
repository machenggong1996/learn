package bishi.jichangqiangzhuan;

/**
 * @author machenggong
 * @date 2020/11/05
 */
public class Test {

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
