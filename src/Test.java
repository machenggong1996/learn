/**
 * @author machenggong
 * @since 2022/5/21
 */
public class Test {

    public static void main(String[] args) {
//        System.out.println(shengxiao(1995));
        for (int i = 4 ;i > 0; i--){
            int j = 0;
            do{
                j++;
                if(j == 2){
                    break;
                }
            }while (j <= i);
            System.out.println(j);
        }
    }

    public static String shengxiao(int year){
        int i = year%12;
        String shengxiao = "";
        switch (i){
            case 0:
                shengxiao = "猴";
                break;
            case 1:
                shengxiao = "鸡";
                break;
            case 2:
                shengxiao = "狗";
                break;
            case 3:
                shengxiao = "猪";
                break;
            case 4:
                shengxiao = "鼠";
                break;
            case 5:
                shengxiao = "牛";
                break;
            case 6:
                shengxiao = "虎";
                break;
            case 7:
                shengxiao = "兔";
                break;
            case 8:
                shengxiao = "龙";
                break;
            case 9:
                shengxiao = "蛇";
                break;
            case 10:
                shengxiao = "马";
                break;
            case 11:
                shengxiao = "羊";
                break;
            default:
                break;
        }
        return shengxiao;
    }
}
