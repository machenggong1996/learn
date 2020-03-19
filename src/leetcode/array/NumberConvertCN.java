package leetcode.array;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by machenggong on 2020/3/18.
 */
public class NumberConvertCN {

    /**
     * 数字与中文大写转换
     *
     * @param args
     */

    public static void main(String[] args) {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        ConvertChinese convertChinese = new ConvertChinese();
        System.out.println("输入金额：");
        BigDecimal number = sc.nextBigDecimal();
        System.out.println(convertChinese.getChinese(number) + "整");
    }
}

class ConvertChinese {
    private String chinese = "";
    private String decimalsChinese = "";
    private String intNumber = "";
    private String decimals = "";
    String chineseUnit = "元=十=百=千=万=十=百=千=亿=十=百=千=万";
    String chineseValue = "零壹贰叁肆伍陆柒捌玖";

    // 实现金钱的数值转换
    public String getChinese(BigDecimal number) {
        String srcNumber = number + "";// 把数值换为String 型
        // 分开整数与小数
        if (srcNumber.contains(".")) {
            intNumber = srcNumber.substring(0, srcNumber.indexOf("."));
            decimals = srcNumber.substring(srcNumber.indexOf(".") + 1, srcNumber.length());
            System.out.println("整数部分：" + intNumber);
            System.out.println("小数部分：" + decimals);
        } else {
            intNumber = srcNumber;
        }
        String chineseUnit1[] = chineseUnit.split("=");
        // 转换整数部分
        for (int i = 0; i < intNumber.length(); i++) {
            chinese += chineseValue.charAt(Integer.parseInt(srcNumber.charAt(i) + ""))
                    + chineseUnit1[intNumber.length() - 1 - i];
        }
        // 转换小数部分
        for (int i = 0; i < decimals.length(); i++) {
            if (i == 0) {
                decimalsChinese += chineseValue.charAt(Integer.parseInt(decimals.substring(0, 1))) + "角";
            }
            if (i == 1) {
                decimalsChinese += chineseValue.charAt(Integer.parseInt(decimals.substring(1, 2))) + "分";
            }
            if (i == 2) {
                decimalsChinese += chineseValue.charAt(Integer.parseInt(decimals.substring(2, 3))) + "厘";
            }
        }
        chinese += decimalsChinese;
        return chinese;
    }

}
